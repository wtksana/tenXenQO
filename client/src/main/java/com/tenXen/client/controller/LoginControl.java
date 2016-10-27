package com.tenXen.client.controller;

import com.tenXen.client.common.Connect;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.handler.ChildChannelHandler;
import com.tenXen.client.util.ConnectUtil;
import com.tenXen.client.util.LayoutLoader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by wt on 2016/9/4.
 */
public class LoginControl {

    @FXML
    private TextField userName;
    @FXML
    private TextField pwd;
    @FXML
    private TextArea output;

    private EventLoopGroup group;

    @FXML
    public void doLogin() throws Exception {
        LayoutContainer.LOGIN_OUTPUT = this.output;
        this.output.setText("登入中...");
        connect();
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
    }

    @FXML
    public void register() throws Exception {
        initRegisterLayout();
    }

    @FXML
    public void close() throws Exception {
        LayoutContainer.LOGINSTAGE.close();
        group.shutdownGracefully();
    }

    public void initRegisterLayout() {
        Parent registerLayout = LayoutLoader.load(LayoutLoader.REGISTER);
        Stage registerStage = new Stage();
        registerStage.setTitle("tenxenQO");
        registerStage.initModality(Modality.WINDOW_MODAL);
        registerStage.initOwner(LayoutContainer.LOGINSTAGE);
        registerStage.setScene(new Scene(registerLayout));
        registerStage.initStyle(StageStyle.UNIFIED);
        registerStage.show();
        LayoutContainer.REGISTERSTAGE = registerStage;
    }

    private void connect() throws Exception {
        new Thread() {
            public void run() {
                try {
                    group = new NioEventLoopGroup();
                    Bootstrap b = new Bootstrap();
                    b.group(group).channel(NioSocketChannel.class)
                            .handler(new ChildChannelHandler());
                    ChannelFuture f = b.connect(ConnectUtil.getRemoteAddress()).sync();
                    Connect.CHANNEL = f.channel();
                    f.channel().closeFuture().sync();
                } catch (Exception e) {
                    e.printStackTrace();
                    output.setText("连接失败...");
                } finally {
                    group.shutdownGracefully();
                    System.out.println("关闭连接-------");
                }
            }
        }.start();
    }

}
