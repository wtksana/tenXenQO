package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.util.LayoutLoader;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private Button cancel;
    @FXML
    private Button register;

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
        this.cancel.setOnMouseReleased(event -> close());
        this.register.setOnMouseReleased(event -> doRegister());
    }

    @FXML
    private void doRegister() {
        Platform.runLater(() -> {
            this.userName.setDisable(true);
            this.pwd.setDisable(true);
            setOutput("注册中...");
            String userName = this.userName.getText();
            String pwd = this.pwd.getText();
            UserModel model = new UserModel();
            model.setUserName(userName);
            model.setPwd(pwd);
            model.setHandlerCode(Constants.REGISTER_CODE);

            if (ConnectContainer.CHANNEL != null) {
                ConnectContainer.CHANNEL.writeAndFlush(model);
            } else {
                setOutput("连接失败...请检查连接设置...");
            }
        });
    }

    @FXML
    private void close() {
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
