package com.tenXen.client;

import com.tenXen.client.controller.LoginControl;
import com.tenXen.client.util.ConnectUtil;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        //初始化登录窗口
        LoginControl.getInstance().initLoginLayout(primaryStage);
        try {
            ConnectUtil.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
