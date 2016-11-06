package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.util.LayoutLoader;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by wt on 2016/11/6.
 */
public class MainControl {

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

    private Stage mainStage;

    public void initMainLayout() {
        try {
            this.mainStage = new Stage();
            Platform.setImplicitExit(false);
            createTrayIcon(mainStage);
            FXMLLoader loader = LayoutLoader.load(LayoutLoader.MAIN);
            loader.setController(MainControl.getInstance());
            Parent mainLayout = loader.load();
            mainStage.getIcons().add(new javafx.scene.image.Image(LayoutLoader.STAG_IMAGE));
            mainStage.setTitle("tenXenQO");
            mainStage.initModality(Modality.NONE);
            mainStage.setScene(new Scene(mainLayout));
            mainStage.initStyle(StageStyle.UNIFIED);
            mainStage.setResizable(false);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createTrayIcon(Stage stage) {
        Platform.runLater(() -> {
            try {
                Toolkit.getDefaultToolkit();
                if (!SystemTray.isSupported()) {
                    Platform.exit();
                    return;
                }
                stage.setOnCloseRequest(t -> hide());
                SystemTray tray = SystemTray.getSystemTray();
                Image image = ImageIO.read(LayoutLoader.TRAY_IMAGE);
                TrayIcon trayIcon = new TrayIcon(image);
                trayIcon.addActionListener(event -> show());
                MenuItem openItem = new MenuItem("show");
                openItem.addActionListener(event -> show());
                MenuItem exitItem = new MenuItem("exit");
                exitItem.addActionListener(event -> exit());
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

    private void show() {
        Platform.runLater(() -> {
            if (mainStage != null) {
                mainStage.show();
                mainStage.toFront();
            }
        });
    }

    private void hide() {
        Platform.runLater(() -> {
            if (SystemTray.isSupported()) {
                mainStage.hide();
            } else {
                System.exit(0);
            }
        });
    }

    private void exit() {
        Platform.runLater(() -> {
            System.out.print("监听到窗口关闭");
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
}
