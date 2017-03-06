package com.ypf.imageselector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.ExifInterface;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by pingfan.yang on 2017/3/6.
 */
public class ImageOperation {

    public final static int TOP_LEFT = 1;
    public final static int TOP_RIGHT = 2;
    public final static int BOTTOM_LEFT = 3;
    public final static int BOTTOM_RIGHT = 4;
    public final static int CENTER = 5;


    static {
        System.loadLibrary("jpegbither");
        System.loadLibrary("bitherjni");
    }

    private static String temporaryFile;

    private static native String compressBitmap(Bitmap bit, int w, int h,
                                                int quality, byte[] fileNameBytes, boolean optimize);

    public static synchronized void addWatermark(final String photoPath, final String watermarkText,  int watermarkLocation,
                                                  OnAddWatermark onAddWatermark) {
        final long startTime = System.currentTimeMillis();
        int quality = 20;
                    /*try{*/
                     /*获取原始图片信息*/
                    //临时文件
                    int index = photoPath.lastIndexOf(".");
                    temporaryFile = photoPath.substring(0, index) + "$temp.jpg";
                    //图片角度信息
                    int orientation = getOrientation(photoPath);
                    Bitmap oldBitmap = BitmapFactory.decodeFile(photoPath);
                    /*质量压缩*/
                    //旋转图片
                    int degree = getDegree(orientation);
                    oldBitmap = rotateBitmapByDegree(oldBitmap, degree);
                    Bitmap bitmap = oldBitmap.copy(oldBitmap.getConfig(), true);
                    oldBitmap.recycle();
                    //添加水印
                    Canvas canvas = new Canvas(bitmap);
                    Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    canvas.drawBitmap(bitmap, null, rect, null);
                    //图片宽高
                    int w = bitmap.getWidth(), h = bitmap.getHeight();
                    //水印笔画
                    Paint paint = new Paint(Paint.HINTING_OFF);
                    Rect bounds = new Rect();
                    paint.setColor(Color.RED);
                    paint.setTextSize(120*(((float)w)/2988));
                    paint.getTextBounds(watermarkText, 0, watermarkText.length(), bounds);
                    //水印宽高
                    int ww = bounds.width(), wh = bounds.height();
                    //水印的初始位置
                    float x = 0, y = 0;
                    switch (watermarkLocation) {
                        case TOP_LEFT:
                            x = wh;
                            y = wh;
                            break;
                        case TOP_RIGHT:
                            x = w - ww - wh;
                            y = wh;
                            break;
                        case BOTTOM_LEFT:
                            x = wh;
                            y = h - wh;
                            break;
                        case BOTTOM_RIGHT:
                            x = w - ww - wh;
                            y = h - wh;
                            break;
                        case CENTER:
                            x = w / 2 - ww / 2;
                            y = h / 2 - wh / 2;
                            break;
                    }
                    canvas.drawText(watermarkText, x, y, paint);
                    //执行压缩
                    saveBitmap(bitmap, quality, temporaryFile, false);
                    bitmap.recycle();
                /*保存图片到原路径*/
                    FileUtils.delete(photoPath);
                    new File(temporaryFile).renameTo(new File(photoPath));
                    onAddWatermark.onSuccess(photoPath);
                    System.gc();
               /* }*//*catch (Exception e)
                    {
                        onAddWatermark.onFailure(photoPath);
                        System.gc();
                    }*/
                }


    /**
     * 设置手机照片的角度
     *
     * @param orientation
     * @return
     */
    public static int getDegree(int orientation) {
        int degree = 0;
        /*设置手机照片的角度*/
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = -90;
                break;
        }
        return degree;
    }

    /**
     * 获取图片方向
     *
     * @param ImgPath 图片路径
     */
    public static int getOrientation(String ImgPath) {
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(ImgPath);
            return exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null)
            returnBm = bm;
        if (bm != returnBm)
            bm.recycle();
        return returnBm;
    }

    public static void saveBitmap(Bitmap bit, int quality, String fileName,
                                  boolean optimize) {
        compressBitmap(bit, bit.getWidth(), bit.getHeight(), quality,
                fileName.getBytes(), optimize);
    }

    /**
     * 压缩监听
     */
    public interface OnAddWatermark {
        void onSuccess(String photoPath);

        void onFailure(String photoPath);
    }
}
