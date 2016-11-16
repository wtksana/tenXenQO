package com.tenXen.client.util;

import javafx.fxml.FXMLLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by wt on 2016/9/11.
 */
public class LayoutUtil {

    /**
     * 登入界面
     */
    public static URL LOGIN = LayoutUtil.class.getResource("/views/loginLayout.fxml");
    /**
     * 注册界面
     */
    public static URL REGISTER = LayoutUtil.class.getResource("/views/registerLayout.fxml");
    /**
     * 主界面
     */
    public static URL MAIN = LayoutUtil.class.getResource("/views/mainLayout.fxml");
    /**
     * 聊天界面
     */
    public static URL CHAT = LayoutUtil.class.getResource("/views/chatLayout.fxml");
    public static URL GROUP = LayoutUtil.class.getResource("/views/groupLayout.fxml");
    /**
     * 聊天记录
     */
    public static URL CHAT_ITEM = LayoutUtil.class.getResource("/views/chatItem.fxml");
    /**
     * 发送表情
     */
    public static URL CHAT_EMOTION = LayoutUtil.class.getResource("/views/chatEmotion.fxml");
    /**
     * 好友
     */
    public static URL FRIEND_ITEM = LayoutUtil.class.getResource("/views/friendItem.fxml");
    /**
     * 系统托盘图标
     */
    public static URL TRAY_IMAGE = LayoutUtil.class.getResource("/image/qo_48X48.jpg");
    public static URL TRAY_NORMAL = LayoutUtil.class.getResource("/image/tray_normal.png");
    public static URL TRAY_MSG = LayoutUtil.class.getResource("/image/tray_msg.png");

    public static InputStream STAG_IMAGE = LayoutUtil.class.getResourceAsStream("/image/qo_48X48.jpg");

    public static FXMLLoader load(URL layout) {
        try {
            return new FXMLLoader(layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image getTrayImage(URL url) {
        Image image = null;
        try {
            image = ImageIO.read(url).getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
