package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.util.ConnectUtil;
import com.tenXen.client.util.LayoutLoader;
import com.tenXen.client.worker.EmotionWorker;
import com.tenXen.common.constant.Constants;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.model.UserModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class LoginControl {

    private LoginControl() {
    }

    private static LoginControl instance = new LoginControl();

    public static LoginControl getInstance() {
        return instance;
    }

    @FXML
    private TextField userName;
    @FXML
    private PasswordField pwd;
    @FXML
    private TextArea output;
    @FXML
    private TextField config;
    @FXML
    private Button modify;
    @FXML
    private Button showConfig;
    @FXML
    private Button login;
    @FXML
    private Button register;

    private Stage loginStage;

    public void initLoginLayout(Stage primaryStage) {
        try {
            this.loginStage = primaryStage;
            loginStage.setTitle("tenXenQO");

            FXMLLoader loader = LayoutLoader.load(LayoutLoader.LOGIN);
            loader.setController(LoginControl.getInstance());
            Parent loginLayout = (Parent) loader.load();

            Scene loginScene = new Scene(loginLayout);
            loginStage.setScene(loginScene);
            loginStage.initStyle(StageStyle.UNIFIED);
            loginStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        System.out.print("监听到窗口关闭");
                        if (ConnectContainer.USER_GROUP != null) {
                            ConnectContainer.USER_GROUP.shutdownGracefully();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        System.exit(0);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        this.login.setOnMouseReleased(event -> doLogin());
        this.register.setOnMouseReleased(event -> register());
        this.showConfig.setOnMouseReleased(event -> showConfig());
        this.modify.setOnMouseReleased(event -> modifyConfig());
    }

    @FXML
    private void doLogin() {
        setOutput("登入中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
        UserModel model = new UserModel();
        model.setUserName(userName);
        model.setPwd(pwd);
        model.setHandlerCode(Constants.LOGIN_CODE);
        model.setResultCode(Constants.RESULT_FAIL);
        if (ConnectContainer.CHANNEL != null) {
            ConnectContainer.CHANNEL.writeAndFlush(model);
        } else {
            setOutput("连接失败...请检查连接设置...");
        }
//        new Thread(() -> ConnectContainer.CHANNEL.writeAndFlush(EmotionWorker.getInstance().updateEmotionRequest())).start();
    }

    @FXML
    private void register() {
        RegisterControl.getInstance().initRegisterLayout(this.loginStage);
    }

    @FXML
    private void close() throws Exception {
        this.loginStage.close();
    }

    @FXML
    private void showConfig() {
        this.config.setText(ConnectUtil.SERVER_HOST);
        this.config.setVisible(true);
        this.modify.setVisible(true);
    }

    @FXML
    private void modifyConfig() {
        this.config.setVisible(false);
        this.modify.setVisible(false);
        this.setOutput("重新连接中...");
        String config = this.config.getText();
        if (StringUtil.isNotBlank(config)) {
            ConnectUtil.SERVER_HOST = config;
        }
        try {
            ConnectUtil.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOutput(String msg) {
        this.output.setText(msg);
    }

    public void handleLogin(UserModel model) {
        if (model.getResultCode() == Constants.RESULT_SUC) {
            this.loginStage.close();
            ConnectContainer.SELF = model.getSelf();
            MainControl.getInstance().initMainLayout();
        } else {
            setOutput(model.getMsg());
        }
    }

}
