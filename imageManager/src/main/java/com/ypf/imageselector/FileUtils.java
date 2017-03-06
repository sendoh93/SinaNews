package com.ypf.imageselector;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



/**
 * Created by pingfan.yang on 2017/3/6.
 */
public class FileUtils {


    /**
     * 读取文件内容
     *
     * @param file    文件
     * @param charset 文件编码
     * @return 文件内容
     */
    public static String readFile(File file, String charset) {
        StringBuffer buffer = new StringBuffer();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), charset);
            BufferedReader reader = new BufferedReader(read);
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 读取文件内容
     *
     * @param file 文件
     * @return 文件内容
     */
    public static String readFile(File file) {
        return readFile(file, "UTF-8");
    }

    /**
     * 读取文件内容
     *
     * @param filePath 文件路径
     * @return 文件内容
     */
    public static String readFile(String filePath) {
        return readFile(getFile(filePath));
    }

    /**
     * 文件转输出流
     *
     * @param file 文件
     * @return 文件输出流
     */
    public static FileOutputStream fileToOutputStream(File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件转输出流
     *
     * @param filePath 文件路径
     * @return 文件输出流
     */
    public static FileOutputStream fileToOutputStream(String filePath) {
        return fileToOutputStream(getFile(filePath));
    }

    /**
     * 文件转输入流
     *
     * @param file 文件
     * @return 文件输入流
     */
    public static FileInputStream fileToInputStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件转输入流
     *
     * @param filePath 文件路径
     * @return 文件输入流
     */
    public static FileInputStream fileToInputStream(String filePath) {
        return fileToInputStream(new File(filePath));
    }

    /**
     * 读取大文件
     *
     * @param file
     * @return
     */
    public static String readBigFile(File file) {
        String result = "";
        int BUFFER_SIZE = 0x300000;
        try {
            MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r")
                    .getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            byte[] dst = new byte[BUFFER_SIZE];
            long start = System.currentTimeMillis();
            for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
                if (inputBuffer.capacity() - offset >= BUFFER_SIZE)
                    for (int i = 0; i < BUFFER_SIZE; i++)
                        dst[i] = inputBuffer.get(offset + i);
                else
                    for (int i = 0; i < inputBuffer.capacity() - offset; i++)
                        dst[i] = inputBuffer.get(offset + i);
                int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
                        : inputBuffer.capacity() % BUFFER_SIZE;
                result = new String(dst, 0, length);
            }
            //Logger.d("读取文件用时:" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
            //Logger.e(e.getMessage());
        }
        return result;
    }

    /**
     * 获取文件的SHA1值
     *
     * @param file 目标文件
     * @return 文件的SHA1值
     */
    public static String getSHA1ByFile(File file) {
        if (!file.exists()) return "文件不存在";
        long time = System.currentTimeMillis();
        InputStream in = null;
        String value = null;
        try {
            in = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            int numRead = 0;
            while (numRead != -1) {
                numRead = in.read(buffer);
                if (numRead > 0) digest.update(buffer, 0, numRead);
            }
            byte[] sha1Bytes = digest.digest();
            String t = new String(buffer);
            value = convertHashToString(sha1Bytes);
        } catch (Exception e) {
           // Logger.e(e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                  //  Logger.e(e);
                }
        }
        return value;
    }

    /**
     * @param hashBytes
     * @return
     */
    private static String convertHashToString(byte[] hashBytes) {
        String returnVal = "";
        for (int i = 0; i < hashBytes.length; i++) {
            returnVal += Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1);
        }
        return returnVal.toLowerCase();
    }

    /**
     * 获取文件的文件名
     *
     * @param filePath 文件路径
     */
    public static String getFileName(String filePath) {
        String filename = new File(filePath).getName();
        if (filename.length() > 80) {
            filename = filename.substring(filename.length() - 80, filename.length());
        }
        return filename;
    }

    /**
     * 获取文件的没有后缀的文件名
     *
     * @param filePath 文件路径
     */
    public static String getSuffixFileName(String filePath) {
        String[] names = getFileName(filePath).split("\\.");
        return names[0];
    }

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
     * 创建文件夹
     *
     * @param mkdirs 文件夹
     */
    public static boolean createMkdirs(String mkdirs) {
        return createMkdirs(new File(mkdirs));
    }

    /**
     * 创建文件
     *
     * @param file 文件
     */
    public static boolean createFile(File file) {
        File parent = file.getParentFile();
        if (!parent.exists())
            createMkdirs(parent);
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 创建文件
     *
     * @param filePath 文件路径
     */
    public static boolean createFile(String filePath) {
        return createFile(new File(filePath));
    }

    /**
     * 获得下载文件名
     *
     * @param url 下载url
     * @return 文件名
     */
    public static String getDownloadFileName(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        if (!fileName.contains("\\."))
            fileName += ".zip";
        return fileName;
    }

    /**
     * 将assets文件中的文件复制到外储存卡中
     *
     * @param context  context
     * @param fileName 文件名
     * @param filePath 文件路径
     * @return
     */
    public static File copyAssetsToDisk(Context context, String fileName, String filePath) {
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            File outFile = new File(filePath, fileName);
            if (!isExists(outFile)) createFile(outFile);
            OutputStream out = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
            return getFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解压assets的zip压缩文件到指定目录
     *
     * @param context   上下文对象
     * @param assetName 压缩文件名
     * @param outputDir 输出目录
     * @param isReWrite 是否覆盖
     * @throws IOException
     */
    public static void unZipInAsset(Context context, String assetName, String outputDir, boolean isReWrite) throws IOException {
        String outputDirectory = /*BasicApplication.sdCardPath + File.separator +*/ outputDir;
        // 创建解压目标目录
        File file = new File(outputDirectory);
        // 如果目标目录不存在，则创建
        if (!file.exists()) file.mkdirs();
        // 打开压缩文件
        InputStream inputStream = context.getResources().getAssets().open(assetName);
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // 读取一个进入点
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        // 使用1Mbuffer
        byte[] buffer = new byte[1024 * 1024];
        // 解压时字节计数
        int count = 0;
        // 如果进入点为空说明已经遍历完所有压缩包中文件和目录
        while (zipEntry != null) {
            // 如果是一个目录
            if (zipEntry.isDirectory()) {
                file = new File(outputDirectory + File.separator + zipEntry.getName());
                // 文件需要覆盖或者是文件不存在
                if (isReWrite || !file.exists()) file.mkdir();
            } else {
                // 如果是文件
                file = new File(outputDirectory + File.separator + zipEntry.getName());
                // 文件需要覆盖或者文件不存在，则解压文件
                if (isReWrite || !file.exists()) {
//                    file.createNewFile();
                    createFile(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while ((count = zipInputStream.read(buffer)) > 0)
                        fileOutputStream.write(buffer, 0, count);
                    fileOutputStream.close();
                }
            }
            // 定位到下一个文件入口
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
    }

    /**
     * 解压sdCard的zip压缩文件到指定目录
     *
     * @param zipPath   压缩文件路径
     * @param outputDir 输出目录
     * @param isReWrite 是否覆盖
     * @throws IOException
     */
    public static void unZipInSdCard(String zipPath, String outputDir, boolean isReWrite) throws IOException {
        String outputDirectory = /*BasicApplication.sdCardPath + File.separator +*/ outputDir;
        // 创建解压目标目录
        File file = new File(outputDirectory);
        // 如果目标目录不存在，则创建
        if (!file.exists()) file.mkdirs();
        // 打开压缩文件
        InputStream inputStream = new FileInputStream(new File(zipPath));
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        // 读取一个进入点
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        // 使用1Mbuffer
        byte[] buffer = new byte[1024 * 1024];
        // 解压时字节计数
        int count = 0;
        // 如果进入点为空说明已经遍历完所有压缩包中文件和目录
        while (zipEntry != null) {
            // 如果是一个目录
            if (zipEntry.isDirectory()) {
                file = new File(outputDirectory + File.separator + zipEntry.getName());
                // 文件需要覆盖或者是文件不存在
                if (isReWrite || !file.exists()) file.mkdir();
            } else {
                // 如果是文件
                file = new File(outputDirectory + File.separator + zipEntry.getName());
                // 文件需要覆盖或者文件不存在，则解压文件
                if (isReWrite || !file.exists()) {
//                    file.createNewFile();
                    createFile(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while ((count = zipInputStream.read(buffer)) > 0)
                        fileOutputStream.write(buffer, 0, count);
                    fileOutputStream.close();
                }
            }
            // 定位到下一个文件入口
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
    }

    /**
     * 执行压缩操作
     *
     * @param finalFile   最终压缩生成的压缩文件：目录+压缩文件名.zip
     * @param srcPathName 需要被压缩的文件/文件夹
     */
   /* public static File zip(String finalFile, String srcPathName) {
        if (isExists(finalFile)) delete(finalFile);
        File srcDir = new File(srcPathName);

        Project project = new Project();
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(new File(finalFile));
        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(srcDir);
        zip.addFileset(fileSet);
        zip.execute();
        return getFile(finalFile);
    }*/

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 是否刪除成功
     */
    public static boolean delete(String filePath) {
        File file = new File(filePath);
        return delete(file);
    }

    /**
     * 删除文件
     *
     * @param file 文件
     * @return 是否刪除成功
     */
    public static boolean delete(File file) {
        if (file == null || !file.exists()) return false;
        if (file.isFile()) {
            final File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(to);
            to.delete();
        } else {
            File[] files = file.listFiles();
            if (files != null && files.length > 0)
                for (File innerFile : files) {
                    delete(innerFile);
                }
            final File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(to);
            return to.delete();
        }
        return false;
    }

    /**
     * 删除目录下所有文件
     *
     * @param Path 路径
     */
    public static void deleteDirAllFile(String Path) {
        // 删除目录下所有文件
        File path = new File(Path);
        File files[] = path.listFiles();
        if (files != null)
            for (File tfi : files) {
                if (tfi.isDirectory())
                   Log.e("tfi-Name:",tfi.getName());
                else
                    tfi.delete();
            }
    }

    /**
     * 保存文本到文件
     *
     * @param fileName 文件名字
     * @param content  内容
     * @param append   是否累加
     * @return 是否成功
     */
    public static boolean saveTextValue(String fileName, String content, boolean append) {
        try {
            File textFile = new File(fileName);
            if (!append && textFile.exists()) textFile.delete();
            FileOutputStream os = new FileOutputStream(textFile);
            os.write(content.getBytes("UTF-8"));
            os.close();
        } catch (Exception ee) {
            return false;
        }
        return true;
    }

    /**
     * 保存文件
     *
     * @param in       文件输入流
     * @param filePath 文件保存路径
     */
    public static File saveFile(InputStream in, String filePath) {
        File file = new File(filePath);
        byte[] buffer = new byte[4096];
        int len = 0;
        FileOutputStream fos = null;
        try {
            FileUtils.createFile(file);
            fos = new FileOutputStream(file);
            while ((len = in.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
           // Logger.e(e);
        } finally {
            try {
                if (in != null) in.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
               // Logger.e(e);
            }
        }
        return file;
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

    /**
     * 文件是否存在
     *
     * @param file 文件
     * @return 文件是否存在
     */
    public static boolean isExists(File file) {
        return file.exists();
    }

    /**
     * 获取文件
     *
     * @param path 文件路径
     */
    public static File getFile(String path) {
        if (!isExists(path))
            try {
                throw new FileNotFoundException("下载的文件不存在");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return new File(path);
    }

    /**
     * 获取文件大小
     *
     * @param file 目标文件
     */
    public static long getFileLenght(File file) {
        if (!isExists(file.getAbsolutePath()))
            try {
                throw new FileNotFoundException("下载的文件不存在");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return file.length();
    }

    private static List<File> mFiles;

    static {
        mFiles = new ArrayList<>();
    }

    /**
     * 遍历文件夹获得全部文件
     *
     * @param dirPath 文件夹路径
     * @return 文件夹中的全部文件集合
     */
    public static List<File> traverseFolder(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists()) {
            File[] fs = dir.listFiles();
            if (fs != null)
                if (fs.length == 0)
                    return new ArrayList<>();
                else
                    for (File file : fs) {
                        if (file.isDirectory()) {
                            Log.d("FileUtils", "文件夹:" + file.getAbsolutePath());
                            traverseFolder(file.getAbsolutePath());
                        } else {
                            Log.d("FileUtils", "文件:" + file.getAbsolutePath());
                            mFiles.add(file);
                        }
                    }
        } else {
            Log.d("FileUtils", "文件夹不存在");
        }
        return mFiles;
    }

    /**
     * 获得文件创建时间
     *
     * @param file   文件
     * @param patten 时间表达式
     * @return 文件创建时间
     */
    public static String getFileCreateTime(File file, String patten) {
        SimpleDateFormat format = new SimpleDateFormat(patten);
        Date date = new Date(file.lastModified());
        return format.format(date);
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param target 复制到的文件
     */
    public static void copy(File source, File target) throws IOException {
        if (!isExists(source)) throw new FileNotFoundException("源文件没有找到");
        if (!isExists(target)) createFile(target);
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = new FileInputStream(source).getChannel();
            outChannel = new FileOutputStream(target).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null) inChannel.close();
            if (outChannel != null) outChannel.close();
        }
    }

    public static void copy(String source, String target) throws IOException {
        copy(new File(source), new File(target));
    }
}
