package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.MessageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.Date;

/**
 * Created by wt on 2016/10/28.
 */
public class CharControl {

    @FXML
    private TextArea sendBox;
    @FXML
    private VBox charBox;
    @FXML
    private ListView userBox;
    @FXML
    private Button send;
    @FXML
    private ScrollPane userScroll;
    @FXML
    private ScrollPane charScroll;

    @FXML
    private void initialize() {
        this.charBox.heightProperty().addListener((observable, oldvalue, newValue) ->
                charScroll.setVvalue((Double) newValue)
        );
        this.sendBox.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.ENTER) {
                doSend();
            }
        });
        LayoutContainer.USER_BOX = this.userBox;
        LayoutContainer.CHAR_BOX = this.charBox;
    }

    @FXML
    private void doSend() {
        String sms = this.sendBox.getText();
        if (!StringUtil.isBlank(sms)) {
            MessageModel model = new MessageModel();
            model.setContent(sms);
            User u = ConnectContainer.SELF;
            if (u != null) {
                model.setUser(u.getId());
                model.setTouser(0);
                model.setCreateTime(new Date());
                model.setUserName(u.getUserName());
                model.setNickName(u.getNickname());
            }
            ConnectContainer.CHANNEL.writeAndFlush(model);
        }
        this.sendBox.setText("");
    }

}
