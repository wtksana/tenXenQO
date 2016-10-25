package com.tenXen.client.handler;

import com.tenXen.core.domain.User;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

/**
 * Created by wt on 2016/9/11.
 */
public class LoginHandler extends ChannelHandlerAdapter {

    private String userName;
    private String pwd;

    public LoginHandler(String userName, String pwd) {
        this.userName = userName;
        this.pwd = pwd;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User u = new User(userName,pwd);
        ctx.writeAndFlush(u);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((String)msg);
        ctx.close();
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
