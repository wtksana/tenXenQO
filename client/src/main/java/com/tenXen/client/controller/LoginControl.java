package com.tenXen.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by wt on 2016/9/4.
 */
public class LoginControl {

    public static Stage STAGE;
    @FXML
    private TextField userName;
    @FXML
    private TextField pwd;
    @FXML
    private TextArea output;

    @FXML
    public void doLogin() throws Exception {
        output.setText("登入中...");
        String userName = this.userName.getText();
        String pwd = this.pwd.getText();
//        connect(userName, pwd);
    }

    @FXML
    public void register() throws Exception {
        Main.initRegisterLayout();
    }

    @FXML
    public void close() throws Exception{
        STAGE.close();
    }

    /*private void connect(String userName, String pwd) throws Exception {
        new Thread() {
            public void run() {
                EventLoopGroup group = new NioEventLoopGroup();
                try {
                    Bootstrap b = new Bootstrap();
                    b.group(group).channel(NioSocketChannel.class)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    socketChannel.pipeline()
                                            .addLast(new LineBasedFrameDecoder(1024))
                                            .addLast(new StringDecoder())
                                            .addLast(new LoginHandler(userName, pwd));
                                }
                            });

                    ChannelFuture f = b.connect(ConnectUtil.getRemoteAddress(), ConnectUtil.getLocalAddress()).sync();
                    Channel ch = f.channel();
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                } finally {
                    System.out.println("关闭连接-------");
                    group.shutdownGracefully();
                }
            }
        }.start();
    }*/

}
