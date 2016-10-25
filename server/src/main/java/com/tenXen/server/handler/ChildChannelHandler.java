package com.tenXen.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by wt on 2016/9/9.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
//                .addLast("framer", new LineBasedFrameDecoder(1024))
//                .addLast("decoder", new StringDecoder())
                .addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
                .addLast(new ObjectEncoder())
                .addLast("registerHandler", new RegisterHandler());
        System.out.println("ChildChannelHandler...");
    }
}
