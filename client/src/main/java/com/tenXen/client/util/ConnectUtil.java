package com.tenXen.client.util;

import com.tenXen.client.common.ConnectContainer;
import com.tenXen.client.controller.LoginControl;
import com.tenXen.client.handler.ChildChannelHandler;
import com.tenXen.common.constant.Constants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class ConnectUtil {

    public static String SERVER_HOST = "127.0.0.1";

    public static boolean isPortUsing(int port) {
        boolean flag = false;
        try {
            InetAddress theAddress = InetAddress.getByName("127.0.0.1");

            Socket socket = new Socket(theAddress, port);
            flag = true;
            socket.close();
        } catch (IOException e) {
        }
        return flag;
    }

    public static int getUsableProt() {
        Random random = new Random();
        int port = random.nextInt(2000) + 4000;
        while (isPortUsing(port)) {
            port = random.nextInt(2000) + 4000;
        }
        System.out.println("localPort:" + port);
        return port;
    }

    public static InetSocketAddress getRemoteAddress() {
        return new InetSocketAddress(ConnectUtil.SERVER_HOST, Constants.SERVER_PORT);
    }

    public static InetSocketAddress getLocalAddress() {
        return new InetSocketAddress(Constants.LOCAL_HOST, getUsableProt());
    }

    public static void connect() throws Exception {
        if (ConnectContainer.CHANNEL != null) {
            return;
        }
        new Thread(() -> {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(group).channel(NioSocketChannel.class)
                        .handler(new ChildChannelHandler());
                ChannelFuture f = b.connect(getRemoteAddress()).sync();
                ConnectContainer.CHANNEL = f.channel();
                ConnectContainer.USER_GROUP = group;
                f.channel().closeFuture().sync();
            } catch (Exception e) {
                LoginControl.getInstance().setOutput("连接失败...");
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
                System.out.println("关闭连接-------");
            }
        }).start();
    }
}
