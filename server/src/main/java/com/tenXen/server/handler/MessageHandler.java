package com.tenXen.server.handler;

import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.MessageModel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by wt on 2016/9/11.
 */
public class MessageHandler extends ChannelHandlerAdapter {

    private final Logger Log = LoggerFactory.getLogger(getClass());

//    private UserService userService;
//
//    public MessageHandler() {
//        this.userService = SpringContainer.getBean(UserService.class);
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MessageModel) {
            MessageModel model = (MessageModel) msg;
            model.setResultCode(Constants.RESULT_SUC);
            ctx.writeAndFlush(model);
            Log.info("serverMessageHandler...suc");
        } else {
            ctx.fireChannelRead(msg);
            Log.info("serverChannelRead...error");
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Log.info("serverChannelRead...Complete");
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
