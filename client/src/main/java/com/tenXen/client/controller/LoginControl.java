package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
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
    private void doLogin() throws Exception {
        LayoutContainer.LOGIN_OUTPUT = this.output;
        this.output.setText("登入中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
        UserModel model = new UserModel();
        model.setUserName(userName);
        model.setPwd(pwd);
        model.setHandlerCode(Constants.LOGIN_CODE);
        ConnectContainer.CHANNEL.writeAndFlush(model);
    }

    @FXML
    private void register() throws Exception {
        LayoutContainer.initRegisterLayout();
    }

    @FXML
    private void close() throws Exception {
        LayoutContainer.LOGIN_STAGE.close();
    }

}
