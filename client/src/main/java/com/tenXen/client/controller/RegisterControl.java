package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.util.ConnectUtil;
import com.tenXen.client.util.LayoutLoader;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Created by wt on 2016/9/4.
 */
public class RegisterControl {

    private RegisterControl() {
    }

    private static RegisterControl instance = new RegisterControl();

    public static RegisterControl getInstance() {
        return instance;
    }

    @FXML
    private TextField userName;
    @FXML
    private TextField pwd;
    @FXML
    private TextArea output;

    private Stage registerStage;

    public void initRegisterLayout(Stage owner) {
        try {
            FXMLLoader loader = LayoutLoader.load(LayoutLoader.REGISTER);
            loader.setController(RegisterControl.getInstance());
            Parent registerLayout = loader.load();

            this.registerStage = new Stage();
            registerStage.setTitle("tenXenQO");
            registerStage.initModality(Modality.WINDOW_MODAL);
            registerStage.initOwner(owner);
            registerStage.setScene(new Scene(registerLayout));
            registerStage.initStyle(StageStyle.UNIFIED);
            registerStage.show();
            registerStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.print("监听到窗口关闭");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void doRegister() throws Exception {
        this.userName.setDisable(true);
        this.pwd.setDisable(true);
        this.output.setText("注册中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
        UserModel model = new UserModel();
        model.setUserName(userName);
        model.setPwd(pwd);
        model.setHandlerCode(Constants.REGISTER_CODE);

        ConnectUtil.connect();//连接netty

        if (ConnectContainer.CHANNEL != null) {
            ConnectContainer.CHANNEL.writeAndFlush(model);
        } else {
            setOutput("连接失败...");
        }
    }

    @FXML
    private void close() throws Exception {
        this.registerStage.close();
    }

    public void setOutput(String msg) {
        this.output.setText(msg);
    }

    public void handleRegister(UserModel model) {
        if (model.getResultCode() == Constants.RESULT_SUC) {
            this.registerStage.close();
            LoginControl.getInstance().setOutput(model.getMsg());
        } else {
            this.userName.setDisable(false);
            this.pwd.setDisable(false);
            setOutput(model.getMsg());
        }
    }
}
