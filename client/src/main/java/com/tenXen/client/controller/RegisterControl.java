package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.util.LayoutUtil;
import com.tenXen.common.constant.Constants;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.model.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by wt on 2016/9/4.
 */
public class RegisterControl extends BaseControl {

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
    private TextField nickname;
    @FXML
    private TextArea output;
    @FXML
    private Button cancel;
    @FXML
    private Button register;
    @FXML
    private ImageView minImage;
    @FXML
    private ImageView closeImage;

    private Stage registerStage;
    private Parent registerLayout;

    @Override
    protected Stage getStage() {
        return registerStage;
    }

    @Override
    protected Parent getRoot() {
        return registerLayout;
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
        Log.info("监听到注册窗口关闭");
        registerStage.close();
    }

    public void initRegisterLayout(Stage owner) {
        try {
            FXMLLoader loader = LayoutUtil.load(LayoutUtil.REGISTER);
            loader.setController(RegisterControl.getInstance());
            registerLayout = loader.load();

            this.registerStage = new Stage();
            registerStage.setTitle("tenXenQO");
            registerStage.initModality(Modality.WINDOW_MODAL);
            registerStage.initOwner(owner);
            super.init();
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        this.cancel.setOnMouseReleased(event -> onClose());
        this.register.setOnMouseReleased(event -> doRegister());
    }

    @FXML
    private void doRegister() {
        setOutput("注册中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
        String nickname = this.nickname.getText();
        if (!checkInput(userName, pwd, nickname)) {
            return;
        }
        this.userName.setDisable(true);
        this.pwd.setDisable(true);
        this.nickname.setDisable(true);
        UserModel model = new UserModel();
        model.setUserName(userName);
        model.setPwd(pwd);
        model.setNickname(nickname);
        model.setHandlerCode(Constants.REGISTER_CODE);
        if (ConnectContainer.CHANNEL != null) {
            ConnectContainer.CHANNEL.writeAndFlush(model);
        } else {
            setOutput("连接失败...请检查连接设置...");
        }
    }

    private void setOutput(String msg) {
        this.output.setText(msg);
    }

    public void handleRegister(UserModel model) {
        if (model.getResultCode() == Constants.RESULT_SUC) {
            this.registerStage.close();
            LoginControl.getInstance().setOutput(model.getMsg());
        } else {
            this.userName.setDisable(false);
            this.pwd.setDisable(false);
            this.nickname.setDisable(false);
            setOutput(model.getMsg());
        }
    }

    private boolean checkInput(String userName, String pwd, String nickname) {
        if (StringUtil.checkUserName(userName) && StringUtil.checkUserName(pwd) && StringUtil.isNotBlank(nickname)) {
            return true;
        } else {
            setOutput("用户名、密码必须在6-20位之间\n由数字或字母组成！");
            return false;
        }
    }
}
