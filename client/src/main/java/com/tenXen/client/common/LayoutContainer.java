package com.tenXen.client.common;

import com.tenXen.client.util.LayoutLoader;
import com.tenXen.common.constant.Constants;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.MessageModel;
import com.tenXen.core.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt on 2016/10/27.
 */
public class LayoutContainer {

    public static TextArea LOGIN_OUTPUT;

    public static TextArea REGISTER_OUTPUT;

    public static VBox CHAR_BOX;

    public static ListView USER_BOX;

    public static Stage LOGIN_STAGE;

    public static Stage REGISTER_STAGE;

    public static Stage CHAR_STAGE;


    public static void initLoginLayout(Stage primaryStage) {
        try {
            Stage loginStage = primaryStage;
            loginStage.setTitle("tenXenQO");
            Parent loginLayout = LayoutLoader.load(LayoutLoader.LOGIN);
            Scene loginScene = new Scene(loginLayout);
            loginStage.setScene(loginScene);
            loginStage.initStyle(StageStyle.DECORATED);
            loginStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        System.out.print("监听到窗口关闭");
                        ConnectContainer.USER_GROUP.shutdownGracefully();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.exit(0);
                    }
                }
            });
            LayoutContainer.LOGIN_STAGE = loginStage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initRegisterLayout() {
        try {
            Parent registerLayout = LayoutLoader.load(LayoutLoader.REGISTER);
            Stage registerStage = new Stage();
            registerStage.setTitle("tenXenQO");
            registerStage.initModality(Modality.WINDOW_MODAL);
            registerStage.initOwner(LayoutContainer.LOGIN_STAGE);
            registerStage.setScene(new Scene(registerLayout));
            registerStage.initStyle(StageStyle.DECORATED);
            registerStage.show();
            registerStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.print("监听到窗口关闭");
                }
            });
            LayoutContainer.REGISTER_STAGE = registerStage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initCharLayout() {
        try {
            Parent charLayout = LayoutLoader.load(LayoutLoader.CHAR);
            Stage charStage = new Stage();
            charStage.setTitle("tenXenQO");
            charStage.initModality(Modality.NONE);
            charStage.setScene(new Scene(charLayout));
            charStage.initStyle(StageStyle.DECORATED);
            charStage.show();
            charStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
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
                }
            });
            LayoutContainer.CHAR_STAGE = charStage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveMessage(MessageModel model) {
        try {
            ConnectContainer.MESSAGE = model;
            Pane charItem = (Pane) LayoutLoader.load(LayoutLoader.CHAR_ITEM);
            LayoutContainer.CHAR_BOX.getChildren().add(charItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateOnlineUser(UserModel model) {
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
        LayoutContainer.USER_BOX.setItems(users);
    }

}
