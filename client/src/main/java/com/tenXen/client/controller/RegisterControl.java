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
public class RegisterControl {

    @FXML
    private TextField userName;
    @FXML
    private TextField pwd;
    @FXML
    private TextArea output;

    @FXML
    private void doRegister() throws Exception {
        LayoutContainer.REGISTER_OUTPUT = this.output;
        this.userName.setDisable(true);
        this.pwd.setDisable(true);
        this.output.setText("注册中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
        UserModel model = new UserModel();
        model.setUserName(userName);
        model.setPwd(pwd);
        model.setHandlerCode(Constants.REGISTER_CODE);
        ConnectContainer.CHANNEL.writeAndFlush(model);
    }

    @FXML
    private void close() throws Exception {
        LayoutContainer.REGISTER_STAGE.close();
    }
}
