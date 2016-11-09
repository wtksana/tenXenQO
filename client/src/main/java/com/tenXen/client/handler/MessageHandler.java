package com.tenXen.client.handler;

import com.tenXen.client.common.LayoutContainer;
import com.tenXen.client.controller.CharControl;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.MessageModel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by wt on 2016/10/30.
 */
public class MessageHandler extends ChannelHandlerAdapter {
    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MessageModel) {
            MessageModel model = (MessageModel) msg;
            if (model.getResultCode() == Constants.RESULT_SUC) {
//                Platform.runLater(() -> CharControl.getInstance().receiveMessage(model));
            } else {
                Log.info("clientMessageHandler...fail");
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
