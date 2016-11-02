package com.tenXen.client.common;

import com.tenXen.core.domain.User;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;

/**
 * Created by wt on 2016/10/25.
 */
public class ConnectContainer {

    public static EventLoopGroup USER_GROUP;

    public static Channel CHANNEL;

    public static User SELF;

}
