package com.tenXen.server.handler;

import com.tenXen.common.constant.Constants;
import com.tenXen.core.domain.User;
import com.tenXen.core.model.UserModel;
import com.tenXen.core.service.UserService;
import com.tenXen.server.util.SpringContainer;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by wt on 2016/9/11.
 */
public class RegisterHandler extends ChannelHandlerAdapter {

    private final Logger Log = LoggerFactory.getLogger(getClass());

    private UserService userService;

    public RegisterHandler() {
        this.userService = SpringContainer.getBean(UserService.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UserModel) {
            UserModel model = (UserModel) msg;
            String result = "error";
            if (model.getHandlerCode() == Constants.REGISTER_CODE) {
                User u = new User(model.getUserName(), model.getPwd());
                try {
                    int count = userService.save(u);
                    if (count > 0) {
                        result = "register success";
                    } else {
                        result = "register fail";
                    }
                } catch (Exception e) {
                    result = e.getMessage();
                }
            }
            model.setMsg(result);
            Log.info(result);
            ctx.writeAndFlush(model);
        } else {
            ctx.fireChannelRead(msg);
            Log.info("channelRead...error");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Log.info("channelRead...Complete");
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
