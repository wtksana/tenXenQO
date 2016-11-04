package com.tenXen.server;

import com.tenXen.common.constant.Constants;
import com.tenXen.server.handler.ChildChannelHandler;
import com.tenXen.server.util.SpringContainer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wt on 2016/9/11.
 */
public class Main {

    private final Logger Log = LoggerFactory.getLogger(getClass());

    public void start() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChildChannelHandler());

            ChannelFuture f = b.bind(Constants.SERVER_HOST, Constants.SERVER_PORT).sync();
            Log.info("服务启动成功...");
            f.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            Log.info("服务关闭了...");
        }

    }

    public static void main(String[] args) throws Exception {
        SpringContainer.init();
        System.out.println("Spring容器初始化成功...");
        new Main().start();
    }

}
