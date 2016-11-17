package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.util.ConnectUtil;
import com.tenXen.client.util.LayoutUtil;
import com.tenXen.client.worker.EmotionWorker;
import com.tenXen.common.constant.Constants;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.model.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginControl extends BaseControl {

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
    @FXML
    private ImageView minImage;
    @FXML
    private ImageView closeImage;

    private Stage loginStage;
    private Parent loginLayout;

    @Override
    protected Stage getStage() {
        return loginStage;
    }

    @Override
    protected Parent getRoot() {
        return loginLayout;
    }

    @Override
    protected ImageView getMinImage() {
        return minImage;
    }

    @Override
    protected ImageView getCloseImage() {
        return closeImage;
    }

    @Override
    protected void onClose() {
        try {
            Log.info("监听到登录窗口关闭");
            ConnectContainer.USER_GROUP.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void initLoginLayout(Stage primaryStage) {
        try {
            this.loginStage = primaryStage;
            loginStage.setTitle("tenXenQO");
            FXMLLoader loader = LayoutUtil.load(LayoutUtil.LOGIN);
            loader.setController(LoginControl.getInstance());
            loginLayout = loader.load();
            super.init();
            super.show();
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
    }

    @FXML
    private void register() {
        RegisterControl.getInstance().initRegisterLayout(this.loginStage);
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
            new Thread(() -> ConnectContainer.CHANNEL.writeAndFlush(EmotionWorker.getInstance().updateEmotionRequest())).start();
            this.loginStage.close();
            ConnectContainer.SELF = model.getSelf();
            ConnectContainer.FRIENDS = model.getFriends();
            ConnectContainer.GROUPS = model.getGroups();
            MainControl.getInstance().initMainLayout();
        } else {
            setOutput(model.getMsg());
        }
    }

}
