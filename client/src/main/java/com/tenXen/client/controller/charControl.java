package com.tenXen.client.controller;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.common.LayoutContainer;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.MessageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wt on 2016/10/28.
 */
public class charControl {

    @FXML
    private TextArea sendBox;
    @FXML
    private VBox charBox;
    @FXML
    private ListView userBox;
    @FXML
    private Button send;

    @FXML
    private void initialize() {
        LayoutContainer.SEND_BOX = this.sendBox;

        List<User> userList = ConnectContainer.USER_LIST;
        if (userList != null && userList.size() > 0) {
            List<String> userNames = new ArrayList();
            for (User user : userList) {
                String userName = user.getUserName();
                String name = user.getName();
                if (!StringUtils.isBlank(name)) {
                    userNames.add(name);
                } else {
                    userNames.add(userName);
                }
            }
            ObservableList<String> users = FXCollections.observableArrayList(userNames);
            userBox.setItems(users);
        }
    }

    @FXML
    private void doSend() {
        String sms = this.sendBox.getText();
        MessageModel model = new MessageModel();
        model.setContent(sms);
        ConnectContainer.CHANNEL.writeAndFlush(model);
        this.sendBox.setText("");
    }

}
