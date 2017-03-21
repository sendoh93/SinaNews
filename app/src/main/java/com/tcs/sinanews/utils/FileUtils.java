package com.tcs.sinanews.utils;

import java.io.File;

/**
 * Created by pingfan.yang on 2017/3/21.
 */
public class FileUtils {
    /**
     * 创建文件夹
     *
     * @param mkdirs 文件夹
     */
    public static boolean createMkdirs(File mkdirs) {
        if (!isExists(mkdirs.getAbsolutePath()))
            return mkdirs.mkdirs();
        return true;
    }
    /**
     * 文件是否存在
     *
     * @param path 文件路径
     * @return 文件是否存在
     */
    public static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }

}
