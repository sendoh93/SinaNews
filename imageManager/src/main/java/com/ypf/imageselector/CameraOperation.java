package com.ypf.imageselector;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by pingfan.yang on 2017/3/6.
 */
public final class CameraOperation {
    public static final int REQUEST_CODE_IMAGE_CAPTURE = 0*01;//相机拍照
    public static final int REQUEST_PREMISSIONS = 0*02; //权限申请
    /**
     * 通过Activity启动相机
     *
     * @param context              activity
     * @param savePath             相片保存路径文件夹
     * @param photoName            相片名
     */
    public static void startCamera(Activity context, String savePath, String photoName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (savePhotoInfo(savePath, photoName, intent, context)) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, REQUEST_PREMISSIONS);
                return;
            } else {
                context.startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE);
            }
        }
    }

    /**
     * 通过Fragment启动相机
     *
     * @param fragment             fragment
     * @param savePath             相片保存路径文件夹
     * @param photoName            相片名
     */
    public static void startCamera(Fragment fragment,  String savePath, String photoName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (savePhotoInfo(savePath, photoName, intent, fragment.getContext())) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(fragment.getActivity(), Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(fragment.getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_PREMISSIONS);
                return;
            } else {
                fragment.startActivityForResult(intent, REQUEST_CODE_IMAGE_CAPTURE);
            }
        }
    }

    public static boolean savePhotoInfo(String savePath, String photoName,
                                        Intent intent, Context context) {
        boolean mkdirs = FileUtils.createMkdirs(savePath);
        if (mkdirs) {
            File file = new File(savePath, photoName);
            FileUtils.createFile(file);
            try {
                Runtime.getRuntime().exec("chmod 777 " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                Uri mOutputFileUri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputFileUri);
            } else {
                ContentValues values = new ContentValues(1);
                values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }

        } else Toast.makeText(context,"创建相片保存路径报错",Toast.LENGTH_LONG).show();
        return mkdirs;
    }

//    public static SurveyImageDetail setPhotoInfo(String photoName, String photoPath) {
//        SurveyImageDetail info = new SurveyImageDetail();
//        info.setImageName(photoName);
//        info.setImage_Path(photoPath);
//        info.setCreateDate(String.valueOf(System.currentTimeMillis()));
//        return info;
//    }
//
//    public static SurveyImageDetail updatePhotoInfo(SurveyImageDetail surveyImageDetail, String photoPath) {
//        ImageUtils.LatLng latLng = ImageUtils.getPhotoLocation(photoPath);
//        String thumbPhotoName = surveyImageDetail.getImageName().substring(0, surveyImageDetail.getImageName().indexOf(".")) + "_thumb.jpg";
//        String thumbPhotoPath = ImageUtils.compressImage(photoPath, Constant.Path.THUMB_PHOTO
//                + "/" + thumbPhotoName, 320, 240, ImageUtils.getDegree(ImageUtils.getOrientation(surveyImageDetail.getImage_Path())));
//        surveyImageDetail.setThumbPhotoPath(thumbPhotoPath);
//        return surveyImageDetail;
//    }
}
