package com.tenXen.core.service;

import com.tenXen.core.domain.Message;
import com.tenXen.core.model.MessageModel;

public interface MessageService extends BaseService<Message> {

    MessageModel getUserUnreadMsgList(MessageModel model);
}
