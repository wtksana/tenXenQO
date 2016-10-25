package com.tenXen.client.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
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


    public static Parent load(URL layout) {
        try {
            return FXMLLoader.load(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
