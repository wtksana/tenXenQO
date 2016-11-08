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
import com.tenXen.core.model.UserModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wt on 2016/10/28.
 */
public class GroupControl {

    private GroupControl() {
    }

    private static GroupControl instance = new GroupControl();

    public static GroupControl getInstance() {
        return instance;
    }

    @FXML
    private TextArea sendBox;
    @FXML
    private VBox charBox;
    @FXML
    private ListView userBox;
    @FXML
    private Button send;
    @FXML
    private ScrollPane userScroll;
    @FXML
    private ScrollPane charScroll;
    @FXML
    private ScrollPane emotionPane;
    @FXML
    private Button emotion;

    private Stage charStage;

    public void initCharLayout() {
        try {
            this.charStage = new Stage();
//            Platform.setImplicitExit(false);
//            createTrayIcon(charStage);
            FXMLLoader loader = LayoutLoader.load(LayoutLoader.CHAR);
            loader.setController(GroupControl.getInstance());
            Parent charLayout = loader.load();
            charStage.getIcons().add(new javafx.scene.image.Image(LayoutLoader.STAG_IMAGE));
            charStage.setTitle("tenXenQO");
            charStage.initModality(Modality.NONE);
            charStage.setScene(new Scene(charLayout));
            charStage.initStyle(StageStyle.UNIFIED);
            charStage.setResizable(false);
            charStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        this.charBox.heightProperty().addListener((observable, oldvalue, newValue) ->
                charScroll.setVvalue((Double) newValue)
        );
        this.sendBox.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.ENTER) {
                doSend();
            }
        });
        this.send.setOnMouseReleased(event -> doSend());
        this.emotion.setOnMousePressed(event -> toggleEmotionPane());
        this.emotionPane.setVisible(false);
        createEmotionPane();
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
        String sms = this.sendBox.getText();
        this.sendBox.setText("");
        Platform.runLater(() -> {
            if (!StringUtil.isBlank(sms)) {
                MessageModel model = new MessageModel();
                model.setIsEmotion(Constants.NO);
                model.setContent(sms);
                User u = ConnectContainer.SELF;
                if (u != null) {
                    model.setUser(u.getId());
                    model.setToUser(0);
                    model.setCreateTime(new Date());
                    model.setUserName(u.getUserName());
                    model.setNickName(u.getNickname());
                }
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
            this.charBox.getChildren().add(charItem);
            if (this.charBox.getChildren().size() > 50) {
                this.charBox.getChildren().remove(0, 20);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOnlineUser(UserModel model) {
        List<User> list = model.getUserList();
        List<String> userNames = new ArrayList();
        for (User user : list) {
            String userName = user.getUserName();
            String name = user.getNickname();
            if (!StringUtil.isBlank(name)) {
                userNames.add(name);
            } else {
                userNames.add(userName);
            }
        }
        ObservableList<String> users = FXCollections.observableArrayList(userNames);
        this.userBox.setItems(users);
    }


    public void createEmotionPane() {
        Platform.runLater(() -> {
            try {
                this.emotionPane.focusedProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        this.emotionPane.setVisible(false);
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
                    this.emotionPane.setContent(vBox);
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
            this.emotionPane.setVisible(false);
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
}
