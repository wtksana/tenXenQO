package com.tenXen.client.controller;

import com.tenXen.client.common.Connect;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.handler.ChildChannelHandler;
import com.tenXen.client.util.ConnectUtil;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    @FXML
    public void doLogin() throws Exception {
        LayoutContainer.LOGIN_OUTPUT = this.output;
        this.output.setText("登入中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
        UserModel model = new UserModel();
        model.setUserName(userName);
        model.setPwd(pwd);
        model.setHandlerCode(Constants.LOGIN_CODE);
        Connect.CHANNEL.writeAndFlush(model);
    }

    @FXML
    public void register() throws Exception {
        LayoutContainer.initRegisterLayout();
    }

    @FXML
    public void close() throws Exception {
        LayoutContainer.LOGIN_STAGE.close();
    }

}
