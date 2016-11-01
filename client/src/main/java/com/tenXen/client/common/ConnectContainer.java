package com.tenXen.client.common;

import com.tenXen.core.domain.User;
import com.tenXen.core.model.MessageModel;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;

import java.util.List;

/**
 * Created by wt on 2016/10/25.
 */
public class ConnectContainer {

    public static EventLoopGroup USER_GROUP;

    public static Channel CHANNEL;

    public static List USER_LIST;

    public static MessageModel MESSAGE;

    public static User SELF;
}
