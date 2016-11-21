package com.tenXen.core.service.impl;

import com.tenXen.common.constant.Constants;
import com.tenXen.core.dao.MessageMapper;
import com.tenXen.core.domain.Message;
import com.tenXen.core.model.MessageModel;
import com.tenXen.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public MessageModel getUserUnreadMsgList(MessageModel model) {
        List<MessageModel> list = messageMapper.getUserUnreadMsgList(model.getToUser());
        if (list != null && list.size() > 0) {
            model.setUnreadMsg(list);
            model.setResultCode(Constants.RESULT_SUC);
            model.setMsg("获取未读消息成功");
        }
        return model;
    }
}
