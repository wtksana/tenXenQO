package com.tenXen.server.handler;

import com.tenXen.common.constant.Constants;
import com.tenXen.core.model.UpdateModel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wt on 2016/11/4.
 */
public class UpdateHandler extends ChannelHandlerAdapter {

    private String emotionPath = "data/server/emotion";
    private String emotionZipPath = emotionPath + "/emotion.zip";
    private String[] includes = {"*.png", "*.jpg", "*.gif"};
    private String[] excludes = null;

    private final Logger Log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof UpdateModel) {
            UpdateModel model = (UpdateModel) msg;
            if (model.getUpdateCode() == Constants.UPDATE_CODE_EMOTION) {
                File file = new File(emotionPath);
                List<String> fileList = new ArrayList();
                if (file.exists()) {
                    fileList = Arrays.asList(file.list());
                }
                List userEmotionList = model.getUserEmotionList();
                if (!userEmotionList.containsAll(fileList)) {
                    fileList.removeAll(userEmotionList);
                    for(String name : fileList){

                    }
                }
                model.setResultCode(Constants.RESULT_SUC);
                ctx.writeAndFlush(model);
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
