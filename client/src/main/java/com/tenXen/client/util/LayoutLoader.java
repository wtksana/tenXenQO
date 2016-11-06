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
    public static URL MAIN = LayoutLoader.class.getResource("/views/mainLayout.fxml");
    /**
     * 聊天界面
     */
    public static URL CHAR = LayoutLoader.class.getResource("/views/charLayout.fxml");
    /**
     * 聊天记录
     */
    public static URL CHAR_ITEM = LayoutLoader.class.getResource("/views/charItem.fxml");

    public static URL CHAR_EMOTION = LayoutLoader.class.getResource("/views/charEmotion.fxml");

    public static URL TRAY_IMAGE = LayoutLoader.class.getResource("/image/qoTray.jpg");

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
