package com.tenXen.server.util;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wt on 2016/11/8.
 */
public class ConnectContainer {

    private static Map<Integer, Channel> ONLINE_USER = new HashMap<>();

    public static void userLogin(Integer userId, Channel channel) {
        ONLINE_USER.put(userId, channel);
    }

    public static void userLogout(Integer userId) {
        ONLINE_USER.remove(userId).close();
    }

    public static boolean isOnline(Integer userId) {
        return ONLINE_USER.containsKey(userId);
    }

    public static Channel getChannel(Integer userId) {
        return ONLINE_USER.get(userId);
    }
}
