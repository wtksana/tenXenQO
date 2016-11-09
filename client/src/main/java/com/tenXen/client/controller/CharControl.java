package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.controller.component.CharEmotionControl;
import com.tenXen.client.controller.component.CharItemControl;
import com.tenXen.client.util.LayoutLoader;
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
public class CharControl extends BaseControl {

    private CharControl() {
    }

    private static CharControl instance = new CharControl();

    public static CharControl getInstance() {
        return instance;
    }

    @FXML
    private TextArea sendBox;
//    @FXML
//    private VBox charBox;
    @FXML
    private ListView userBox;
    @FXML
    private Button send;
    @FXML
    private ScrollPane userScroll;
//    @FXML
//    private ScrollPane charScroll;
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
    private TabPane charTabPane;

    private Stage charStage;
    private Parent charLayout;
    private UserFriendModel userFriendModel;

    @Override
    protected Stage getStage() {
        return charStage;
    }

    @Override
    protected Parent getRoot() {
        return charLayout;
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
            charStage.close();
        });
    }

    public void initCharLayout(UserFriendModel model) {
        try {
            charStage = new Stage();
            userFriendModel = model;
            FXMLLoader loader = LayoutLoader.load(LayoutLoader.CHAR);
            loader.setController(CharControl.getInstance());
            charLayout = loader.load();
            charStage.setTitle("tenXenQO");
            charStage.initModality(Modality.NONE);
            charStage.setResizable(false);
            super.init();
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        title.setText(userFriendModel.getFriend_userName());
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
        addCharBox();
    }

    @FXML
    private void toggleEmotionPane() {
        if (!emotionPane.isVisible()) {
            emotionPane.setVisible(true);
            Platform.runLater(() -> emotionPane.requestFocus());
        }
    }

    @FXML
    private void doSend() {
        Platform.runLater(() -> {
            String sms = sendBox.getText();
            sendBox.setText("");
            User u = ConnectContainer.SELF;
            if (!StringUtil.isBlank(sms) && u != null) {
                MessageModel model = new MessageModel();
                model.setIsEmotion(Constants.NO);
                model.setUser(u.getId());
                model.setToUser(userFriendModel.getFriendId());
                model.setContent(sms);
                model.setType(1);
                model.setCreateTime(new Date());
                model.setUserName(u.getUserName());
                model.setNickName(u.getNickname());
                ConnectContainer.CHANNEL.writeAndFlush(model);
            }
        });
    }

    public void receiveMessage(MessageModel model) {
        try {
            FXMLLoader loader;
            if (model.getIsEmotion() == Constants.YES) {
                CharEmotionControl charEmotionControl = new CharEmotionControl(model);
                loader = LayoutLoader.load(LayoutLoader.CHAR_EMOTION);
                loader.setController(charEmotionControl);
            } else {
                CharItemControl charItemControl = new CharItemControl(model);
                loader = LayoutLoader.load(LayoutLoader.CHAR_ITEM);
                loader.setController(charItemControl);
            }
            Pane charItem = loader.load();
//            charBox.getChildren().add(charItem);
//            if (charBox.getChildren().size() > 50) {
//                charBox.getChildren().remove(0, 20);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void doSendEmotion(String name) {
        Platform.runLater(() -> {
            emotionPane.setVisible(false);
            MessageModel model = new MessageModel();
            model.setIsEmotion(Constants.YES);
            model.setContent(name);
            User u = ConnectContainer.SELF;
            if (u != null) {
                model.setUser(u.getId());
                model.setToUser(0);
                model.setCreateTime(new Date());
                model.setUserName(u.getUserName());
                model.setNickName(u.getNickname());
            }
            ConnectContainer.CHANNEL.writeAndFlush(model);
        });
    }

    public void addCharBox() {
        VBox vBox = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(vBox);
        Tab tab = new Tab(userFriendModel.getFriend_nickname());
        tab.setContent(scrollPane);
        tab.setOnSelectionChanged(event -> {
            if(tab.isSelected()){
                Log.info(tab.getText());
            }
        });
        charTabPane.getTabs().add(tab);
    }
}
