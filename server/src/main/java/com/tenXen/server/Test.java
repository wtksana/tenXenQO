package com.tenXen.server;

import com.tenXen.common.util.ZipUtil;

/**
 * Created by wt on 2016/11/4.
 */
public class Test {

    public static void main(String[] args) {
//        URL url = Test.class.getResource("/data/image/emotion");
        String path = "data/server/emotion";
        String newPath = path + "/emotion.zip";
        String[] includes = {"*.png", "*.jpg", "*.gif"};
        String[] excludes = {""};
        ZipUtil.doZip(path, newPath, includes, null);
//        ZipUtil.unZip(newPath,path);
    }
}
