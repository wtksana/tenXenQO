package com.tenXen.client.controller;/**
 * Created by wt on 2016/9/4.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //初始化登录窗口
        LoginControl.getInstance().initLoginLayout(primaryStage);
    }

}
