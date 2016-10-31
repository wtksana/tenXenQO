package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.MessageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<User> userList = ConnectContainer.USER_LIST;
        if (userList != null && userList.size() > 0) {
            List<String> userNames = new ArrayList();
            for (User user : userList) {
                String userName = user.getUserName();
                String name = user.getNickname();
                if (!StringUtil.isBlank(name)) {
                    userNames.add(name);
                } else {
                    userNames.add(userName);
                }
            }
            ObservableList<String> users = FXCollections.observableArrayList(userNames);
            userBox.setItems(users);
        }
        LayoutContainer.CHAR_BOX = this.charBox;
        LayoutContainer.CHAR_SCROLL = this.charScroll;
    }

    @FXML
    private void doSend() {
        String sms = this.sendBox.getText();
        if(!StringUtil.isBlank(sms)) {
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
            this.sendBox.setText("");
        }
    }

}
