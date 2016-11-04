package com.tenXen.client.worker;

import com.tenXen.common.util.FileUtil;
import com.tenXen.common.util.ZipUtil;
import com.tenXen.core.model.UpdateModel;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

    private static String EMOTION_PATH = "data/client/emotion";
    private static String EMOTION_ZIP_PATH = EMOTION_PATH + "/emotion.zip";

    public Map<String, Image> getAllEmotion() throws Exception {
        Map<String, Image> emotions = new HashMap<>();
        File file = new File(EMOTION_PATH);
        if (file.exists()) {
            File[] tempList = file.listFiles();
            if (tempList != null && tempList.length > 0) {
                for (File f : tempList) {
                    String name = f.getName();
                    InputStream is = new FileInputStream(f);
                    Image image = new Image(is);
                    emotions.put(name, image);
                }
            }
        }
        return emotions;
    }

    public Image getEmotion(String name) throws Exception {
        File file = new File(EMOTION_PATH + "/" + name);
        InputStream is = new FileInputStream(file);
        Image image = new Image(is);
        return image;
    }

    public void updateEmotionResponse(UpdateModel model) throws Exception {
        if (model.getEmotionDatas() == null) {
            return;
        }
//        FileUtil.writeFile(EMOTION_ZIP_PATH, model.getData());
//        ZipUtil.unZip(EMOTION_ZIP_PATH, EMOTION_PATH);
    }

    public void updateEmotionRequest() throws Exception{

    }

    public static void main(String[] args) throws Exception {
        EmotionWorker worker = new EmotionWorker();
        worker.getAllEmotion();
    }
}
