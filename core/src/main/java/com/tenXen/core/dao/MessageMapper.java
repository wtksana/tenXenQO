package com.tenXen.core.dao;

import com.tenXen.core.domain.Message;
import com.tenXen.core.model.MessageModel;

import java.util.List;

public interface MessageMapper extends BaseMapper<Message> {

    List<MessageModel> getUserUnreadMsgList(int userId);

    void setUserAllMsgRead(int userId);

}
