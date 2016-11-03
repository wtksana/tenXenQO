package com.tenXen.client.worker;

import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wt on 2016/11/3.
 */
public class EmotionWorker {

    private EmotionWorker() {
    }

    private static EmotionWorker instance = new EmotionWorker();

    public static EmotionWorker getInstance() {
        return instance;
    }

    public static String EMOTION_PATH = "/image/emotion/";

    public Map<String, Image> getAllEmotion() throws Exception {
        Map<String, Image> emotions = new HashMap<>();
        File file = new File(this.getClass().getResource("/image/emotion").getFile().replaceAll("%20", " "));
        if (file != null) {
            File[] tempList = file.listFiles();
            if (tempList != null && tempList.length > 0) {
                for (File f : tempList) {
                    String name = f.getName();
                    Image image = new Image(this.getClass().getResourceAsStream(EMOTION_PATH + name));
                    emotions.put(name, image);
                }
            }
        }
        return emotions;
    }

    public Image getEmotion(String name) throws Exception {
        Image image = new Image(this.getClass().getResourceAsStream(EMOTION_PATH + name));
        return image;
    }

//    public static void main(String[] args) throws Exception {
//        getAllEmotion();
//    }
}
