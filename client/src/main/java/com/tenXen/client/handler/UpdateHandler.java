package com.tenXen.client.handler;

import com.tenXen.client.worker.EmotionWorker;
import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UpdateModel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by wt on 2016/11/4.
 */
public class UpdateHandler extends ChannelHandlerAdapter {

    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UpdateModel) {
            UpdateModel model = (UpdateModel) msg;
            if (model.getUpdateCode() == Constants.UPDATE_CODE_EMOTION) {
                if (model.getResultCode() == Constants.RESULT_SUC) {
                    EmotionWorker.getInstance().updateEmotion(model);
                }
            }
        } else {
            ctx.fireChannelRead(msg);
        }
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
