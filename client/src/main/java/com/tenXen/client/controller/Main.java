package com.tenXen.client.controller;/**
 * Created by wt on 2016/9/4.
 */

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.handler.ChildChannelHandler;
import com.tenXen.client.util.ConnectUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LayoutContainer.initLoginLayout(primaryStage);
        connect();
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
                    ConnectContainer.CHANNEL = f.channel();
                    ConnectContainer.USER_GROUP = group;
                    f.channel().closeFuture().sync();
                } catch (Exception e) {
                    LayoutContainer.LOGIN_OUTPUT.setText(e.getMessage());
                    e.printStackTrace();
                } finally {
                    group.shutdownGracefully();
                    System.out.println("关闭连接-------");
                }
            }
        }.start();
    }
}
