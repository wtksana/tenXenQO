package com.tenXen.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wt on 2016/11/4.
 */
public class Test {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        File file1 = new File("data/server/emotion");
        File file2 = new File("data/client/emotion");
        if (file1.exists()) {
            list1 = new ArrayList<String>(Arrays.asList(file1.list()));
        }
        if (file2.exists()) {
            list2 = new ArrayList<String>(Arrays.asList(file2.list()));
        }
        list2.removeAll(list1);
    }
}
