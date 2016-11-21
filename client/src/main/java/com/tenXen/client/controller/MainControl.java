package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.controller.component.FriendItemControl;
import com.tenXen.client.util.LayoutUtil;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.MessageModel;
import com.tenXen.core.model.UserFriendModel;
import com.tenXen.core.model.UserModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by wt on 2016/11/6.
 */
public class MainControl extends BaseControl {

    private MainControl() {
    }

    private static MainControl instance = new MainControl();

    public static MainControl getInstance() {
        return instance;
    }

    @FXML
    private ImageView headImage;
    @FXML
    private Label nickname;
    @FXML
    private TextField searchBar;
    @FXML
    private VBox friendsBox;
    @FXML
    private ImageView minImage;
    @FXML
    private ImageView closeImage;

    private Stage mainStage;
    private Parent mainLayout;

    @Override
    protected Stage getStage() {
        return mainStage;
    }

    @Override
    protected Parent getRoot() {
        return mainLayout;
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
    protected void onMin() {
        if (SystemTray.isSupported()) {
            mainStage.hide();
        } else {
            System.exit(0);
        }
    }

    @Override
    protected void onClose() {
        Log.info("监听到主窗口关闭");
        try {
            UserModel model = new UserModel();
            model.setHandlerCode(Constants.LOGOUT_CODE);
            model.setSelf(ConnectContainer.SELF);
            model.setResultCode(Constants.RESULT_FAIL);
            ConnectContainer.CHANNEL.writeAndFlush(model);
            ConnectContainer.USER_GROUP.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void initMainLayout() {
        try {
            this.mainStage = new Stage();
            Platform.setImplicitExit(false);
            Platform.runLater(() -> createTrayIcon());
            FXMLLoader loader = LayoutUtil.load(LayoutUtil.MAIN);
            loader.setController(MainControl.getInstance());
            mainLayout = loader.load();
            mainStage.setTitle("tenXenQO");
            mainStage.initModality(Modality.NONE);
            mainStage.setResizable(false);
            super.init();
            super.show();
            Platform.runLater(() -> initFriends());
            initGroups();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        nickname.setText(ConnectContainer.SELF.getNickname());
    }

    private void initFriends() {
        try {
            if (!ConnectContainer.FRIENDS.isEmpty()) {
                for (UserFriendModel model : ConnectContainer.FRIENDS.values()) {
                    this.friendsBox.getChildren().add(new FriendItemControl(model).create());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGroups() {

    }

    private void createTrayIcon() {
        try {
            Toolkit.getDefaultToolkit();
            if (!SystemTray.isSupported()) {
                Platform.exit();
                return;
            }
            SystemTray tray = SystemTray.getSystemTray();
            LayoutContainer.TRAYICON = new TrayIcon(LayoutUtil.getTrayImage(LayoutUtil.TRAY_NORMAL));
            LayoutContainer.TRAYICON.addActionListener(event -> Platform.runLater(() -> show()));
            MenuItem openItem = new MenuItem("show");
            openItem.addActionListener(event -> Platform.runLater(() -> show()));
            MenuItem exitItem = new MenuItem("exit");
            exitItem.addActionListener(event -> Platform.runLater(() -> onClose()));
            final PopupMenu popup = new PopupMenu();
            popup.add(openItem);
            popup.addSeparator();
            popup.add(exitItem);
            LayoutContainer.TRAYICON.setPopupMenu(popup);
            tray.add(LayoutContainer.TRAYICON);
            //获取未读消息
            MessageModel model = new MessageModel();
            model.setHandlerCode(Constants.MSG_UNREAD_CODE);
            model.setToUser(ConnectContainer.SELF.getId());
            model.setResultCode(Constants.RESULT_FAIL);
            ConnectContainer.CHANNEL.writeAndFlush(model);
        } catch (Exception e) {
            Log.info(e.getMessage());
            System.exit(0);
        }
    }

    public void receiveUnreadMsg(MessageModel model) {
        List<MessageModel> list = model.getUnreadMsg();
        list.stream().collect(Collectors.groupingBy(MessageModel::getUserName)).forEach((s, messageModels) -> {
            if (ConnectContainer.UNREAD_MSG.containsKey(s)) {
                ConnectContainer.UNREAD_MSG.get(s).addAll(messageModels);
            } else {
                ConnectContainer.UNREAD_MSG.put(s, messageModels);
            }
        });
        Platform.runLater(() -> setTrayIconMsg());
    }

    public void receiveMessage(MessageModel model) {
        Map msg = ConnectContainer.UNREAD_MSG;
        if (msg.containsKey(model.getUserName())) {
            List<MessageModel> list = (List) msg.get(model.getUserName());
            list.add(model);
        } else {
            List<MessageModel> list = new ArrayList<>();
            list.add(model);
            msg.put(model.getUserName(), list);
        }
//        Platform.runLater(() -> createNotify(model));
        Platform.runLater(() -> setTrayIconMsg());
    }

//    private void createNotify(MessageModel model) {
//        if (model.getIsEmotion() == Constants.YES) {
//            Notifications.create().graphic(new ImageView(getClass().getResource("/image/qo_48X48.jpg").toExternalForm())).title(model.getNickname()).text("【图片】").onAction(event -> notifyOnAction(model.getUserName())).show();
//        } else {
//            Notifications.create().graphic(new ImageView(getClass().getResource("/image/qo_48X48.jpg").toExternalForm())).title(model.getNickname()).text(model.getContent()).onAction(event -> notifyOnAction(model.getUserName())).show();
//        }
//    }
//
//    private void notifyOnAction(String userName) {
//        ChatControl.getInstance().receiveMessage(userName);
//        ConnectContainer.UNREAD_MSG.remove(userName);
//    }

    private void setTrayIconNormal() {
        if (LayoutContainer.TRAYICON != null) {
            LayoutContainer.TRAYICON.setImage(LayoutUtil.getTrayImage(LayoutUtil.TRAY_NORMAL));
            for (ActionListener actionListener : LayoutContainer.TRAYICON.getActionListeners()) {
                LayoutContainer.TRAYICON.removeActionListener(actionListener);
            }
            LayoutContainer.TRAYICON.addActionListener(e -> Platform.runLater(() -> show()));
        }
    }

    private void setTrayIconMsg() {
        if (LayoutContainer.TRAYICON != null) {
            LayoutContainer.TRAYICON.setImage(LayoutUtil.getTrayImage(LayoutUtil.TRAY_MSG));
            for (ActionListener actionListener : LayoutContainer.TRAYICON.getActionListeners()) {
                LayoutContainer.TRAYICON.removeActionListener(actionListener);
            }
            LayoutContainer.TRAYICON.addActionListener(e -> {
                Platform.runLater(() -> ChatControl.getInstance().receiveAllMessage());
                setTrayIconNormal();
            });
        }
    }
}
