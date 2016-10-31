package com.tenXen.client.common;

import com.tenXen.client.util.LayoutLoader;
import com.tenXen.core.model.MessageModel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by wt on 2016/10/27.
 */
public class LayoutContainer {

    public static TextArea LOGIN_OUTPUT;

    public static TextArea REGISTER_OUTPUT;

    public static VBox CHAR_BOX;

    public static ScrollPane CHAR_SCROLL;

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
            LayoutContainer.CHAR_STAGE = charStage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveMessage(MessageModel message) {
        try {
            ConnectContainer.MESSAGE = message;
            Pane charItem = (Pane) LayoutLoader.load(LayoutLoader.CHAR_ITEM);
            LayoutContainer.CHAR_BOX.getChildren().add(charItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
