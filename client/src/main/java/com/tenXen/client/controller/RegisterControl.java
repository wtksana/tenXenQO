package com.tenXen.client.controller;

import com.tenXen.client.common.Connect;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by wt on 2016/9/4.
 */
public class RegisterControl {

    public static Stage STAGE;
    @FXML
    private TextField userName;
    @FXML
    private TextField pwd;
    @FXML
    private TextArea output;

    @FXML
    public void doRegister() throws Exception {
        output.setText("注册中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
        UserModel model = new UserModel();
        model.setUserName(userName);
        model.setPwd(pwd);
        Connect.CHANNEL.writeAndFlush(model);
    }

    @FXML
    public void close() throws Exception {
        STAGE.close();
    }

}
