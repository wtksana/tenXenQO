package com.tenXen.server.handler;

import com.tenXen.core.domain.User;
import com.tenXen.core.model.UserModel;
import com.tenXen.core.service.UserService;
import com.tenXen.server.util.SpringContainer;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;

import java.io.IOException;

/**
 * Created by wt on 2016/9/11.
 */
public class RegisterHandler extends ChannelHandlerAdapter {

    private UserService userService;

    public RegisterHandler() {
        this.userService = SpringContainer.getBean(UserService.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UserModel) {
            UserModel model = (UserModel) msg;
            switch (model.getHandlerCode()) {
//                case UserModel.REGISTER_CODE:

            }
            User u = new User(model.getUserName(), model.getPwd());
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
