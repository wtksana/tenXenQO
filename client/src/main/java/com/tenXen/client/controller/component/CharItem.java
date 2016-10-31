package com.tenXen.client.controller.component;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.common.util.DateUtil;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.model.MessageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by wt on 2016/10/31.
 */
public class CharItem {

    @FXML
    private Label userName;
    @FXML
    private Label createTime;
    @FXML
    private Label content;

    @FXML
    private void initialize() {
        MessageModel message = ConnectContainer.MESSAGE;
        if (message != null) {
            if (StringUtil.isBlank(message.getNickName())) {
                this.userName.setText(message.getUserName());
            } else {
                this.userName.setText(message.getNickName());
            }
            this.createTime.setText(DateUtil.dateStr4(message.getCreateTime()));
            this.content.setText(message.getContent());
        }
    }

}
