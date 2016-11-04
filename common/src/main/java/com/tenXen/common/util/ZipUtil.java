package com.tenXen.common.util;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * Class ZipUtil
 * 压缩
 *
 * @author wuteng
 * @version 1.0, 16/11/04
 */
public class ZipUtil {

    /**
     * Method doZip
     *
     * @param sourceFile 源文件
     * @param newFile    目标文件
     * @param includes   包括哪些文件 可以为null
     * @param excludes   不包括哪些文件 可以为null
     */
    public static void doZip(String sourceFile, String newFile, String[] includes, String[] excludes) {
        File newF = new File(newFile);
        if (newF.exists()) {
            return;
        }
        File sourceF = new File(sourceFile);
        if (!sourceF.exists()) {
            throw new RuntimeException(sourceFile + "不存在！");
        }
        Project project = new Project();
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(newF);
        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(sourceF);
        fileSet.appendIncludes(includes);    // 包括哪些文件或文件夹 eg:"*.java",".jpg";
        fileSet.appendExcludes(excludes);    // 排除哪些文件或文件夹
        zip.addFileset(fileSet);
        zip.execute();
    }

    /**
     * Method unZip
     *
     * @param sourceFile 源文件
     * @param newFile    目标文件
     */
    public static void unZip(String sourceFile, String newFile) {
        File newF = new File(newFile);
        File sourceF = new File(sourceFile);
        if (!sourceF.exists()) {
            throw new RuntimeException(sourceF + "不存在！");
        }
//      if (newF.exists()) {
//          throw new RuntimeException(newF + "已存在！");
//      }
        Project project = new Project();
        Expand expand = new Expand();
        expand.setProject(project);
        expand.setTaskType("unzip");
        expand.setTaskName("unzip");
//      expand.setEncoding(encoding);
        expand.setSrc(sourceF);
        expand.setDest(newF);
        expand.execute();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
