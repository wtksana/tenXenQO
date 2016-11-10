package com.tenXen.client.util;

import javafx.fxml.FXMLLoader;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by wt on 2016/9/11.
 */
public class LayoutLoader {

    /**
     * 登入界面
     */
    public static URL LOGIN = LayoutLoader.class.getResource("/views/loginLayout.fxml");
    /**
     * 注册界面
     */
    public static URL REGISTER = LayoutLoader.class.getResource("/views/registerLayout.fxml");
    /**
     * 主界面
     */
    public static URL MAIN = LayoutLoader.class.getResource("/views/mainLayout.fxml");
    /**
     * 聊天界面
     */
    public static URL CHAT = LayoutLoader.class.getResource("/views/chatLayout.fxml");
    public static URL GROUP = LayoutLoader.class.getResource("/views/groupLayout.fxml");
    /**
     * 聊天记录
     */
    public static URL CHAT_ITEM = LayoutLoader.class.getResource("/views/chatItem.fxml");
    /**
     * 发送表情
     */
    public static URL CHAT_EMOTION = LayoutLoader.class.getResource("/views/chatEmotion.fxml");
    /**
     * 好友
     */
    public static URL FRIEND_ITEM = LayoutLoader.class.getResource("/views/friendItem.fxml");
    /**
     * 系统托盘图标
     */
    public static URL TRAY_IMAGE = LayoutLoader.class.getResource("/image/qoTray_16X16.jpg");

    public static InputStream STAG_IMAGE = LayoutLoader.class.getResourceAsStream("/image/qo_48X48.jpg");

    public static FXMLLoader load(URL layout) {
        try {
            return new FXMLLoader(layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
