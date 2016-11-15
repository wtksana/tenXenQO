package com.tenXen.client.controller;

import com.tenXen.client.common.ChatTabBoxModel;
import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.controller.component.ChatEmotionControl;
import com.tenXen.client.controller.component.ChatItemControl;
import com.tenXen.client.util.LayoutLoader;
import com.tenXen.client.worker.EmotionWorker;
import com.tenXen.common.constant.Constants;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.MessageModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Map;

/**
 * Created by wt on 2016/10/28.
 */
public class ChatControl extends BaseControl {

    private ChatControl() {
    }

    private static ChatControl instance = new ChatControl();

    public static ChatControl getInstance() {
        return instance;
    }

    @FXML
    private TextArea sendBox;
    @FXML
    private ListView userBox;
    @FXML
    private Button send;
    @FXML
    private ScrollPane userScroll;
    @FXML
    private ScrollPane emotionPane;
    @FXML
    private Button emotion;
    @FXML
    private ImageView minImage;
    @FXML
    private ImageView closeImage;
    @FXML
    private Label title;
    @FXML
    private TabPane chatTabPane;

    private Stage chatStage;
    private Parent chatLayout;
    private VBox chatingBox;
    private String chatingFriendUserName;
    private int chatingFriendId;

    @Override
    protected Stage getStage() {
        return chatStage;
    }

    @Override
    protected Parent getRoot() {
        return chatLayout;
    }

    @Override
    protected ImageView getMinImage() {
        return minImage;
    }

    @Override
    protected ImageView getCloseImage() {
        return closeImage;
    }

    @Override
    protected void onClose() {
        Platform.runLater(() -> {
            Log.info("监听到聊天窗口关闭" + title.getText());
            chatStage.close();
            if (!LayoutContainer.CHAT_TAB_BOX.isEmpty()) {
                LayoutContainer.CHAT_TAB_BOX.clear();
            }
        });
    }

    public void initChatLayout(String userName) {
        try {
            chatStage = new Stage();
            chatingFriendUserName = userName;
            FXMLLoader loader = LayoutLoader.load(LayoutLoader.CHAT);
            loader.setController(ChatControl.getInstance());
            chatLayout = loader.load();
            chatStage.setTitle("tenXenQO");
            chatStage.initModality(Modality.NONE);
            chatStage.setResizable(false);
            super.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        title.setText(chatingFriendUserName);
//        charBox.heightProperty().addListener((observable, oldvalue, newValue) ->
//                charScroll.setVvalue((Double) newValue)
//        );
        sendBox.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.ENTER) {
                doSend();
            }
        });
        send.setOnMouseReleased(event -> doSend());
        emotion.setOnMousePressed(event -> toggleEmotionPane());
        emotionPane.setVisible(false);
        createEmotionPane();
    }

    private void toggleEmotionPane() {
        if (!emotionPane.isVisible()) {
            emotionPane.setVisible(true);
            Platform.runLater(() -> emotionPane.requestFocus());
        }
    }

    private void doSend() {
        Platform.runLater(() -> {
            String sms = sendBox.getText();
            sendBox.setText("");
            User u = ConnectContainer.SELF;
            if (!StringUtil.isBlank(sms) && u != null) {
                MessageModel model = new MessageModel();
                model.setIsEmotion(Constants.NO);
                model.setUser(u.getId());
                model.setToUser(chatingFriendId);
                model.setContent(sms);
                model.setType(1);
                model.setCreateTime(new Date());
                model.setUserName(u.getUserName());
                model.setNickName(u.getNickname());
                ConnectContainer.CHANNEL.writeAndFlush(model);
                locationMessageAdd(model);
            }
        });
    }

    private void doSendEmotion(String name) {
        Platform.runLater(() -> {
            emotionPane.setVisible(false);
            User u = ConnectContainer.SELF;
            if (u != null) {
                MessageModel model = new MessageModel();
                model.setIsEmotion(Constants.YES);
                model.setUser(u.getId());
                model.setToUser(chatingFriendId);
                model.setContent(name);
                model.setType(2);
                model.setCreateTime(new Date());
                model.setUserName(u.getUserName());
                model.setNickName(u.getNickname());
                ConnectContainer.CHANNEL.writeAndFlush(model);
                locationMessageAdd(model);
            }
        });
    }

    private void locationMessageAdd(MessageModel model) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader;
                if (model.getIsEmotion() == Constants.YES) {
                    ChatEmotionControl chatEmotionControl = new ChatEmotionControl(model);
                    loader = LayoutLoader.load(LayoutLoader.CHAT_EMOTION);
                    loader.setController(chatEmotionControl);
                } else {
                    ChatItemControl chatItemControl = new ChatItemControl(model);
                    loader = LayoutLoader.load(LayoutLoader.CHAT_ITEM);
                    loader.setController(chatItemControl);
                }
                Pane charItem = loader.load();
                if (model.getResultCode() == Constants.RESULT_SUC) {
                    LayoutContainer.CHAT_TAB_BOX.get(model.getUserName()).getvBox().getChildren().add(charItem);
                } else {
                    chatingBox.getChildren().add(charItem);
//                    if (chatingBox.getChildren().size() > 50) {
//                        chatingBox.getChildren().remove(0, 20);
//                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void receiveMessage(MessageModel model) {
        Platform.runLater(() -> {
            try {
                openChatBox(model.getUserName(), model.getNickName(), model.getUser());
                locationMessageAdd(model);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void createEmotionPane() {
        Platform.runLater(() -> {
            try {
                emotionPane.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        emotionPane.setVisible(false);
                    }
                });
                Map<String, javafx.scene.image.Image> emotions = EmotionWorker.getInstance().getAllEmotion();
                if (!emotions.isEmpty()) {
                    int i = 1;
                    VBox vBox = new VBox();
                    vBox.setPrefWidth(400);
                    HBox hBox = new HBox();
                    hBox.setPrefHeight(80);
                    hBox.setPrefWidth(400);
                    for (Map.Entry<String, javafx.scene.image.Image> entry : emotions.entrySet()) {
                        hBox.getChildren().add(createEmotion(entry.getKey(), entry.getValue()));
                        if (i % 5 == 0) {
                            vBox.getChildren().add(hBox);
                            hBox = new HBox();
                            hBox.setPrefHeight(80);
                            hBox.setPrefWidth(400);
                        }
                        i++;
                    }
                    vBox.getChildren().add(hBox);
                    emotionPane.setContent(vBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private ImageView createEmotion(String name, javafx.scene.image.Image image) {
        ImageView emotion = new ImageView();
        emotion.setImage(image);
        emotion.setFitHeight(80.0);
        emotion.setFitWidth(80.0);
        emotion.setOnMouseClicked(event -> doSendEmotion(name));
        return emotion;
    }

    public void addChatBox(String userName, String nickname, int id) {
        Platform.runLater(() -> {
            VBox vBox = new VBox();
            Tab tab = new Tab(nickname);
            LayoutContainer.CHAT_TAB_BOX.put(userName, new ChatTabBoxModel(tab, vBox, nickname, userName));
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setContent(vBox);
            tab.setContent(scrollPane);
            tab.setOnSelectionChanged(event -> {
                if (tab.isSelected()) {
                    chatingFriendId = id;
                    chatingBox = vBox;
                    title.setText(userName);
                }
            });
            tab.setOnClosed(event -> {
                LayoutContainer.CHAT_TAB_BOX.remove(userName);
                if (LayoutContainer.CHAT_TAB_BOX.isEmpty()) {
                    onClose();
                }
            });
            chatTabPane.getTabs().add(tab);
        });
    }

    public void showToFront(String userName) {
        Platform.runLater(() -> {
            if (getStage() != null) {
                chatTabPane.getSelectionModel().select(LayoutContainer.CHAT_TAB_BOX.get(userName).getTab());
                chatStage.show();
                chatStage.toFront();
            } else {
                System.exit(0);
            }
        });
    }

    public void openChatBox(String userName, String nickName, int id) {
        if (LayoutContainer.CHAT_TAB_BOX.isEmpty()) {
            initChatLayout(userName);
            addChatBox(userName, nickName, id);
            showToFront(userName);
        } else {
            if (LayoutContainer.CHAT_TAB_BOX.containsKey(userName)) {
                showToFront(userName);
            } else {
                addChatBox(userName, nickName, id);
                showToFront(userName);
            }
        }
    }
}
