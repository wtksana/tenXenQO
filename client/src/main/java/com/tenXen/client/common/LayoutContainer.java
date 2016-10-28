package com.tenXen.client.common;

import com.tenXen.client.util.LayoutLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by wt on 2016/10/27.
 */
public class LayoutContainer {

    public static TextArea LOGIN_OUTPUT;

    public static TextArea REGISTER_OUTPUT;

    public static TextArea SEND_BOX;

    public static Stage LOGIN_STAGE;

    public static Stage REGISTER_STAGE;

    public static Stage CHAR_STAGE;


    public static void initLoginLayout(Stage primaryStage) {
        Stage loginStage = primaryStage;
        loginStage.setTitle("tenXenQO");
        Parent loginLayout = LayoutLoader.load(LayoutLoader.LOGIN);
        Scene loginScene = new Scene(loginLayout);
        loginStage.setScene(loginScene);
        loginStage.initStyle(StageStyle.DECORATED);
        loginStage.show();
        LayoutContainer.LOGIN_STAGE = loginStage;
    }

    public static void initRegisterLayout() {
        Parent registerLayout = LayoutLoader.load(LayoutLoader.REGISTER);
        Stage registerStage = new Stage();
        registerStage.setTitle("tenXenQO");
        registerStage.initModality(Modality.WINDOW_MODAL);
        registerStage.initOwner(LayoutContainer.LOGIN_STAGE);
        registerStage.setScene(new Scene(registerLayout));
        registerStage.initStyle(StageStyle.DECORATED);
        registerStage.show();
        LayoutContainer.REGISTER_STAGE = registerStage;
    }

    public static void initCharLayout() {
        Parent charLayout = LayoutLoader.load(LayoutLoader.CHAR);
        Stage charStage = new Stage();
        charStage.setTitle("tenXenQO");
        charStage.initModality(Modality.NONE);
        charStage.setScene(new Scene(charLayout));
        charStage.initStyle(StageStyle.DECORATED);
        charStage.show();
        LayoutContainer.CHAR_STAGE = charStage;
    }

}
