package com.tenXen.client.worker;

import com.tenXen.common.constant.Constants;
import com.tenXen.common.util.FileUtil;
import com.tenXen.core.model.UpdateModel;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

    public void updateEmotionResponse(UpdateModel model) {
        if (model.getServerEmotionMap() == null || model.getServerEmotionMap().isEmpty()) {
            return;
        } else {
            FileUtil.createFullFolder(EMOTION_PATH);
            Map<String, byte[]> emotion = model.getServerEmotionMap();
            for (Map.Entry<String, byte[]> entry : emotion.entrySet()) {
                try {
                    FileUtil.writeFile(EMOTION_PATH + "/" + entry.getKey(), entry.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        CharControl.getInstance().createEmotionPane();
    }

    public UpdateModel updateEmotionRequest() {
        UpdateModel model = new UpdateModel();
        model.setUpdateCode(Constants.UPDATE_CODE_EMOTION);
        List<String> emotionList = new ArrayList<>();
        try {
            File file = new File(EMOTION_PATH);
            if (file.exists()) {
                model.setUserEmotionList(new ArrayList<String>(Arrays.asList(file.list())));
            } else {
                model.setUserEmotionList(emotionList);
            }
        } catch (Exception e) {
            model.setUserEmotionList(emotionList);
        }
        return model;
    }

    public static void main(String[] args) throws Exception {
        EmotionWorker worker = new EmotionWorker();
        worker.getAllEmotion();
    }
}
