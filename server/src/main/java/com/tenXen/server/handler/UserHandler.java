package com.tenXen.server.handler;

import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
import com.tenXen.core.service.UserService;
import com.tenXen.server.util.ChannelGroups;
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
public class UserHandler extends ChannelHandlerAdapter {

    private final Logger Log = LoggerFactory.getLogger(getClass());

    private UserService userService;

    public UserHandler() {
        this.userService = SpringContainer.getBean(UserService.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UserModel) {
            UserModel model = (UserModel) msg;
            if (model.getHandlerCode() == Constants.REGISTER_CODE) {
                model = userService.doRegister(model);
            }
            if (model.getHandlerCode() == Constants.LOGIN_CODE) {
                model = userService.doLogin(model);
                if (model.getResultCode() == Constants.RESULT_SUC) {
                    ChannelGroups.add(ctx.channel());
                }
            }
            if (model.getHandlerCode() == Constants.LOGOUT_CODE) {
                userService.doLogout(model);
                if (ChannelGroups.contains(ctx.channel())) {
                    ChannelGroups.remove(ctx.channel());
                }
            }
            Log.info(model.getMsg());
            ctx.writeAndFlush(model);
//            if (model.getHandlerCode() == Constants.LOGOUT_CODE || model.getHandlerCode() == Constants.LOGIN_CODE && model.getResultCode() == Constants.RESULT_SUC) {
//                UserModel m = userService.getOnlineUserList();
//                if (m.getResultCode() == Constants.RESULT_SUC) {
//                    ChannelGroups.broadcast(m);
//                }
//            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof ReadTimeoutException || cause instanceof IOException) {
            ctx.close();
        } else {
            ctx.fireExceptionCaught(cause);
        }
    }
}
