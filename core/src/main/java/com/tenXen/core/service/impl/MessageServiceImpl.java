package com.tenXen.core.service.impl;

import com.tenXen.core.dao.MessageMapper;
import com.tenXen.core.domain.Message;
import com.tenXen.core.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author createUser
 * @ClassName: MessageServiceImpl
 * @date 10月31日 10:20
 * wt
 */
@Service()
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

}
