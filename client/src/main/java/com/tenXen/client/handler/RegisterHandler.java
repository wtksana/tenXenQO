package com.tenXen.client.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

/**
 * Created by wt on 2016/9/11.
 */
public class RegisterHandler extends ChannelHandlerAdapter {

//    private String userName;
//    private String pwd;
//
//    public RegisterHandler() {
//    }
//
//    public RegisterHandler(String userName, String pwd) {
//        this.userName = userName;
//        this.pwd = pwd;
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        User u = new User(userName,pwd);
//        ctx.writeAndFlush(u);
        System.out.println("channelActive...ok");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((String)msg);
        System.out.println("channelRead...ok");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof IOException) {
            ctx.close();
        } else {
            ctx.fireExceptionCaught(cause);
        }
    }
}
