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
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Platform.runLater(() -> {
            if (SystemTray.isSupported()) {
                mainStage.hide();
            } else {
                System.exit(0);
            }
        });
    }

    @Override
    protected void onClose() {
        Platform.runLater(() -> {
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
        });
    }

    public void initMainLayout() {
        try {
            this.mainStage = new Stage();
            Platform.setImplicitExit(false);
            createTrayIcon();
            FXMLLoader loader = LayoutUtil.load(LayoutUtil.MAIN);
            loader.setController(MainControl.getInstance());
            mainLayout = loader.load();
            mainStage.setTitle("tenXenQO");
            mainStage.initModality(Modality.NONE);
            mainStage.setResizable(false);
            super.init();
            super.show();
            initFriends();
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
        Platform.runLater(() -> {
            try {
                List<UserFriendModel> friends = ConnectContainer.FRIENDS;
                if (friends != null && friends.size() > 0) {
                    for (UserFriendModel model : friends) {
                        this.friendsBox.getChildren().add(new FriendItemControl(model).create());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initGroups() {

    }

    private void createTrayIcon() {
        Platform.runLater(() -> {
            try {
                Toolkit.getDefaultToolkit();
                if (!SystemTray.isSupported()) {
                    Platform.exit();
                    return;
                }
                SystemTray tray = SystemTray.getSystemTray();
                TrayIcon trayIcon = LayoutContainer.TRAYICON;
                trayIcon = new TrayIcon(LayoutUtil.getTrayImage(LayoutUtil.TRAY_NORMAL));
                trayIcon.addActionListener(event -> show());
                MenuItem openItem = new MenuItem("show");
                openItem.addActionListener(event -> show());
                MenuItem exitItem = new MenuItem("exit");
                exitItem.addActionListener(event -> onClose());
                final PopupMenu popup = new PopupMenu();
                popup.add(openItem);
                popup.addSeparator();
                popup.add(exitItem);
                trayIcon.setPopupMenu(popup);
                tray.add(trayIcon);
            } catch (Exception e) {
                Log.info(e.getMessage());
                System.exit(0);
            }
        });
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
        createNotify(model);
        if (LayoutContainer.TRAYICON != null) {
            LayoutContainer.TRAYICON.setImage(LayoutUtil.getTrayImage(LayoutUtil.TRAY_MSG));
        }
    }

    private void createNotify(MessageModel model) {
        Platform.runLater(() -> {
            if (model.getIsEmotion() == Constants.YES) {
                Notifications.create().graphic(new ImageView(getClass().getResource("/image/qo_48X48.jpg").toExternalForm())).title(model.getNickName()).text("【图片】").onAction(event -> notifyOnAction(model)).show();
            } else {
                Notifications.create().graphic(new ImageView(getClass().getResource("/image/qo_48X48.jpg").toExternalForm())).title(model.getNickName()).text(model.getContent()).onAction(event -> notifyOnAction(model)).show();
            }
        });
    }

    private void notifyOnAction(MessageModel model) {
        ChatControl.getInstance().receiveMessage(model);
        if (LayoutContainer.TRAYICON != null) {
            LayoutContainer.TRAYICON.setImage(LayoutUtil.getTrayImage(LayoutUtil.TRAY_NORMAL));
        }
    }
}
