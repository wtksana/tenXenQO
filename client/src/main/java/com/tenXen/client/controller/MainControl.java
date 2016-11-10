package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.controller.component.FriendItemControl;
import com.tenXen.client.util.LayoutLoader;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.List;

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
            createTrayIcon(mainStage);
            FXMLLoader loader = LayoutLoader.load(LayoutLoader.MAIN);
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

//    @FXML
//    private void initialize() {
//    }

    private void initFriends() {
        Platform.runLater(() -> {
            try {
                List<UserFriendModel> friends = ConnectContainer.FRIENDS;
                if (friends != null && friends.size() > 0) {
                    for (UserFriendModel model : friends) {
                        FXMLLoader loader = LayoutLoader.load(LayoutLoader.FRIEND_ITEM);
                        FriendItemControl friendItem = new FriendItemControl(model);
                        loader.setController(friendItem);
                        Parent friend = loader.load();
                        this.friendsBox.getChildren().add(friend);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initGroups() {

    }

    private void createTrayIcon(Stage stage) {
        Platform.runLater(() -> {
            try {
                Toolkit.getDefaultToolkit();
                if (!SystemTray.isSupported()) {
                    Platform.exit();
                    return;
                }
                SystemTray tray = SystemTray.getSystemTray();
                Image image = ImageIO.read(LayoutLoader.TRAY_IMAGE);
                TrayIcon trayIcon = new TrayIcon(image);
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
                e.printStackTrace();
            }
        });
    }

    private void createNotify(MessageModel model) {
        Platform.runLater(() -> {
            if (model.getIsEmotion() == Constants.YES) {
                Notifications.create().title(model.getNickName()).text("【图片】").show();
            } else {
                Notifications.create().title(model.getNickName()).text(model.getContent()).show();
            }
        });
    }

}
