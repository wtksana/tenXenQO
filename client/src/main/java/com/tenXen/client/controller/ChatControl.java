package com.tenXen.client.controller;

import com.tenXen.client.common.ChatTabBoxModel;
import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.controller.component.ChatEmotionControl;
import com.tenXen.client.controller.component.ChatItemControl;
import com.tenXen.client.util.LayoutUtil;
import com.tenXen.client.worker.EmotionWorker;
import com.tenXen.common.constant.Constants;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.MessageModel;
import com.tenXen.core.model.UserFriendModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;
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
        Log.info("监听到聊天窗口关闭" + title.getText());
        chatStage.close();
        if (!LayoutContainer.CHAT_TAB_BOX.isEmpty()) {
            LayoutContainer.CHAT_TAB_BOX.clear();
        }
    }

    public void initChatLayout() {
        try {
            chatStage = new Stage();
            FXMLLoader loader = LayoutUtil.load(LayoutUtil.CHAT);
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
        sendBox.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.ENTER) {
                doSend();
            }
        });
        send.setOnMouseReleased(event -> doSend());
        emotion.setOnMousePressed(event -> toggleEmotionPane());
        emotionPane.setVisible(false);
        Platform.runLater(() -> createEmotionPane());
    }

    private void toggleEmotionPane() {
        if (!emotionPane.isVisible()) {
            emotionPane.setVisible(true);
            Platform.runLater(() -> emotionPane.requestFocus());
        }
    }

    private void doSend() {
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
            Platform.runLater(() -> locationMessageAdd(model));
        }
    }

    private void doSendEmotion(String name) {
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
            Platform.runLater(() -> locationMessageAdd(model));
        }
    }

    private void locationMessageAdd(MessageModel model) {
        AnchorPane charItem;
        if (model.getIsEmotion() == Constants.YES) {
            charItem = new ChatEmotionControl(model).create();
        } else {
            charItem = new ChatItemControl(model).create();
        }
        if (charItem != null) {
            if (model.getResultCode() == Constants.RESULT_SUC) {
                LayoutContainer.CHAT_TAB_BOX.get(model.getUserName()).getvBox().getChildren().add(charItem);
            } else {
                chatingBox.getChildren().add(charItem);
//                    if (chatingBox.getChildren().size() > 50) {
//                        chatingBox.getChildren().remove(0, 20);
//                    }
            }
        }
    }

    public void receiveMessage(String userName) {
        try {
            createChatBox(userName);
            VBox vBox = LayoutContainer.CHAT_TAB_BOX.get(userName).getvBox();
            List<MessageModel> msg = ConnectContainer.UNREAD_MSG.get(userName);
            for (MessageModel m : msg) {
                AnchorPane charItem = null;
                if (m.getIsEmotion() == Constants.YES) {
                    charItem = new ChatEmotionControl(m).create();
                } else {
                    charItem = new ChatItemControl(m).create();
                }
                if (charItem != null) {
                    vBox.getChildren().add(charItem);
                }
            }
            Platform.runLater(() -> showToFront(userName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveAllMessage() {
        try {
            Map<String, List<MessageModel>> map = ConnectContainer.UNREAD_MSG;
            if (!map.isEmpty()) {
                map.keySet().forEach(this::receiveMessage);
                map.clear();
            }
            Platform.runLater(() -> showToFront());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createEmotionPane() {
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
    }

    private ImageView createEmotion(String name, javafx.scene.image.Image image) {
        ImageView emotion = new ImageView();
        emotion.setImage(image);
        emotion.setFitHeight(80.0);
        emotion.setFitWidth(80.0);
        emotion.setOnMouseClicked(event -> doSendEmotion(name));
        return emotion;
    }

    public void addChatBox(String userName) {
        if (ConnectContainer.FRIENDS.containsKey(userName)) {
            UserFriendModel model = ConnectContainer.FRIENDS.get(userName);
            VBox vBox = new VBox();
            ScrollPane scrollPane = new ScrollPane();
            Tab tab = new Tab(model.getFriend_nickname());
            LayoutContainer.CHAT_TAB_BOX.put(userName, new ChatTabBoxModel(tab, vBox, model.getFriend_nickname(), userName));
            vBox.heightProperty().addListener((observable, oldvalue, newValue) ->
                    scrollPane.setVvalue((Double) newValue)
            );
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setContent(vBox);
            tab.setContent(scrollPane);
            tab.setOnSelectionChanged(event -> {
                if (tab.isSelected()) {
                    chatingFriendId = model.getFriendId();
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
        }
    }

    public void showToFront(String userName) {
        if (getStage() != null) {
            chatTabPane.getSelectionModel().select(LayoutContainer.CHAT_TAB_BOX.get(userName).getTab());
            chatStage.show();
            chatStage.toFront();
        } else {
            System.exit(0);
        }
    }

    public void showToFront() {
        if (getStage() != null) {
            chatStage.show();
            chatStage.toFront();
        } else {
            System.exit(0);
        }
    }

    public void createChatBox(String userName) {
        if (LayoutContainer.CHAT_TAB_BOX.isEmpty()) {
            initChatLayout();
            addChatBox(userName);
        } else if (!LayoutContainer.CHAT_TAB_BOX.containsKey(userName)) {
            addChatBox(userName);
        }
    }
}
