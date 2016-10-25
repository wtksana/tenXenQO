package com.tenXen.client.util;

import com.tenXen.common.constant.Constants;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class ConnectUtil {
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
        System.out.println("localPort:"+port);
        return port;
    }

    public static InetSocketAddress getRemoteAddress() {
        return new InetSocketAddress(Constants.SERVER_HOST, Constants.SERVER_PORT);
    }

    public static InetSocketAddress getLocalAddress() {
        return new InetSocketAddress(Constants.LOCAL_HOST, getUsableProt());
    }
}
