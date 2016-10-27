package com.tenXen.client.controller;/**
 * Created by wt on 2016/9/4.
 */

import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.util.LayoutLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

//    private Light.Point point = new Light.Point();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initLoginLayout(primaryStage);
//        connect();
    }

    public void initLoginLayout(Stage primaryStage) {
        Stage loginStage = primaryStage;
        loginStage.setTitle("tenxenQO");
        Parent loginLayout = LayoutLoader.load(LayoutLoader.LOGIN);
        Scene loginScene = new Scene(loginLayout);
        loginStage.setScene(loginScene);
        loginStage.initStyle(StageStyle.UNIFIED);
        loginStage.show();
        LayoutContainer.LOGINSTAGE = loginStage;
//        EventUtil.setCommonEvent(loginStage,loginLayout,point);
    }

    /*private void connect() throws Exception {
        new Thread() {
            public void run() {
                EventLoopGroup group = new NioEventLoopGroup();
                try {
                    Bootstrap b = new Bootstrap();
                    b.group(group).channel(NioSocketChannel.class)
                            .handler(new ChildChannelHandler());
                    ChannelFuture f = b.connect(ConnectUtil.getRemoteAddress()).sync();
                    Connect.CHANNEL = f.channel();
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                } finally {
                    group.shutdownGracefully();
                    System.out.println("关闭连接-------");
                }
            }
        }.start();
    }*/
}
