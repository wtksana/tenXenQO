package com.tenXen.client.controller.component;

import com.tenXen.common.util.DateUtil;
import com.tenXen.common.util.StringUtil;
import com.tenXen.core.model.MessageModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by wt on 2016/10/31.
 */
public class ChatItemControl {

    @FXML
    private Label userName;
    @FXML
    private Label createTime;
    @FXML
    private Label content;
    @FXML
    private Pane chatItem;

    private MessageModel messageModel;

    public ChatItemControl(MessageModel messageModel) {
        this.messageModel = messageModel;
    }


    @FXML
    private void initialize() {
        if (this.messageModel != null) {
            if (StringUtil.isBlank(this.messageModel.getNickName())) {
                this.userName.setText(this.messageModel.getUserName());
            } else {
                this.userName.setText(this.messageModel.getNickName());
            }
            this.createTime.setText(DateUtil.dateStr4(this.messageModel.getCreateTime()));
            this.content.setText(this.messageModel.getContent());
        }
    }

}
