package com.tenXen.client.handler;

import com.tenXen.client.common.LayoutContainer;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UserModel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
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
            if(model.getHandlerCode()== Constants.REGISTER_CODE){
                if(model.getResultCode()==Constants.RESULT_SUC){
                    LayoutContainer.LOGIN_OUTPUT.setText(model.getMsg());
                }
            }
        }
        Log.info("channelRead...ok");
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
