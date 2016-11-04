package com.tenXen.common.util;

import java.io.*;

/**
 * date 2012-9-24 18:58:42
 *
 * @author XiaHui
 */
public class FileUtil {

    public static boolean isFolderExists(String path) {
        File file = new File(path);
        return (file.exists() && file.isDirectory());
    }

    /**
     * 创建文件夹（全路径都为文件夹）
     */
    public static void createFullFolder(String folderPath) {
        File folder = new File(folderPath);
        File parent = folder.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * 创建文件夹（只创建父文件夹）
     */
    public static void createParentFolder(String path) {
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    /**
     * 创建文件夹（全路径都为文件夹）
     */
    public static void checkOrCreateFolder(String folderPath) {
        File folder = new File(folderPath);
        File parent = folder.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * 创建文件夹（全路径都为文件夹）
     */
    public static File getFolder(String folderPath) {
        File folder = new File(folderPath);
        File parent = folder.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    public static boolean isFileExists(String path) {
        File file = new File(path);
        return (file.exists() && !file.isDirectory());
    }

    public static void createFile(String path) {
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void checkOrCreateFile(String path) {
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static File getFile(String path) {
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return file;
    }

    public static byte[] readFile(String fileName) throws Exception {
        File f = new File(fileName);
        if (!f.exists())
            throw new Exception("文件不存在");
        InputStream ins = new FileInputStream(f);
        BufferedInputStream in = new BufferedInputStream(ins);
        int lenght = (int) f.length();
        byte[] buffer = new byte[lenght];
        in.read(buffer, 0, lenght);
        in.close();
        ins.close();
        f = null;
        return buffer;
    }

    public static void writeFile(String fileName, byte[] bytes) throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        } else {
            f.delete();
            f.createNewFile();
        }
        OutputStream os = new FileOutputStream(f);
        BufferedOutputStream out = new BufferedOutputStream(os);
        out.write(bytes);
        out.close();
        os.close();
        f = null;
    }

    public static void deleteFile(String name) {
        File f = new File(name);
        if (f.exists()) {
            f.delete();
            f = null;
        }
    }

}
