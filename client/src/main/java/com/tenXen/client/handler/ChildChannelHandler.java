package com.tenXen.client.handler;

import com.tenXen.common.util.MarshallingCodeCFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by wt on 2016/9/9.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
//                .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())), new ObjectEncoder())
                .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                .addLast(new UserHandler())
                .addLast(new MessageHandler());
        System.out.println("ChildChannelHandler...");
    }
}
