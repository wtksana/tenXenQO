package com.tenXen.client.handler;

import com.tenXen.client.controller.GroupControl;
import com.tenXen.client.controller.LoginControl;
import com.tenXen.client.controller.RegisterControl;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by wt on 2016/9/11.
 */
public class UserHandler extends ChannelHandlerAdapter {

    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UserModel) {
            UserModel model = (UserModel) msg;
            if (model.getHandlerCode() == Constants.REGISTER_CODE) {
                Platform.runLater(() -> RegisterControl.getInstance().handleRegister(model));
            }
            if (model.getHandlerCode() == Constants.LOGIN_CODE) {
                Platform.runLater(() -> LoginControl.getInstance().handleLogin(model));
            }
            if (model.getHandlerCode() == Constants.UPDATE_ONLINE_USER) {
                Platform.runLater(() -> GroupControl.getInstance().updateOnlineUser(model));
            }
        } else {
            ctx.fireChannelRead(msg);
        }
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
