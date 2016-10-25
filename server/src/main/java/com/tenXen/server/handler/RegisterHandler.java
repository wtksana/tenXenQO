package com.tenXen.server.handler;

import com.tenXen.core.domain.User;
import com.tenXen.core.service.UserService;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by wt on 2016/9/11.
 */
@Service()
public class RegisterHandler extends ChannelHandlerAdapter {

    @Resource
    private UserService userService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead...start");
        if (msg instanceof User) {
            User u = (User) msg;
            int count = userService.save(u);
            String result;
            if (count > 0) {
                result = "register success";
            } else {
                result = "register fail";
            }
            System.out.println(result);
            ctx.writeAndFlush(result);
        } else {
            ctx.fireChannelRead(msg);
            System.out.println("channelRead...error");
        }
        System.out.println("channelRead...end");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRead...Complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException || cause instanceof IOException) {
            ctx.close();
        } else {
            ctx.fireExceptionCaught(cause);
        }
    }
}
