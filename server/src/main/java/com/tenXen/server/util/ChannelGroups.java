package com.tenXen.server.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.ChannelMatcher;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by wt on 2016/10/31.
 */
public class ChannelGroups {

    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup("ChannelGroups", GlobalEventExecutor.INSTANCE);

    public static void add(Channel channel) {
        CHANNEL_GROUP.add(channel);
    }

    public static ChannelGroupFuture broadcast(Object msg) {
        return CHANNEL_GROUP.writeAndFlush(msg);
    }

    public static ChannelGroupFuture broadcast(Object msg, ChannelMatcher matcher) {
        return CHANNEL_GROUP.writeAndFlush(msg, matcher);
    }

    public static ChannelGroup flush() {
        return CHANNEL_GROUP.flush();
    }

    public static boolean remove(Channel channel) {
        return CHANNEL_GROUP.remove(channel);
    }

    public static ChannelGroupFuture disconnect() {
        return CHANNEL_GROUP.disconnect();
    }

    public static ChannelGroupFuture disconnect(ChannelMatcher matcher) {
        return CHANNEL_GROUP.disconnect(matcher);
    }

    public static boolean contains(Channel channel) {
        return CHANNEL_GROUP.contains(channel);
    }

    public static int size() {
        return CHANNEL_GROUP.size();
    }
}
