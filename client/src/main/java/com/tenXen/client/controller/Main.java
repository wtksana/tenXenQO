package com.tenXen.client.controller;/**
 * Created by wt on 2016/9/4.
 */

import com.tenXen.client.common.Connect;
import com.tenXen.client.handler.ChildChannelHandler;
import com.tenXen.client.util.ConnectUtil;
import com.tenXen.client.util.LayoutLoader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static Stage loginStage;
    private static Parent loginLayout;
//    private Light.Point point = new Light.Point();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.loginStage = primaryStage;
        initLoginLayout();
        connect();
    }

    public static void initLoginLayout() {
        loginStage.setTitle("tenxenQO");
        loginLayout = LayoutLoader.load(LayoutLoader.LOGIN);
        Scene loginScene = new Scene(loginLayout);
        loginStage.setScene(loginScene);
        loginStage.initStyle(StageStyle.UNIFIED);
        loginStage.show();
        LoginControl.STAGE = loginStage;
//        EventUtil.setCommonEvent(loginStage,loginLayout,point);
    }

    public static void initRegisterLayout(){
        Parent registerLayout = LayoutLoader.load(LayoutLoader.REGISTER);
        Stage registerStage = new Stage();
        registerStage.setTitle("tenxenQO");
        registerStage.initModality(Modality.WINDOW_MODAL);
        registerStage.initOwner(loginStage);
        registerStage.setScene(new Scene(registerLayout));
        registerStage.initStyle(StageStyle.UNIFIED);
        registerStage.show();
        RegisterControl.STAGE = registerStage;
    }

    private void connect() throws Exception {
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
    }
}
