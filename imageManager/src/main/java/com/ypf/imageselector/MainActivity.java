package com.ypf.imageselector;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtntakePhoto;
    private Button mBtnChoosePhoto;
    private ImageView mIvImage;
    private Context mContext;
    private String savePath;
    private String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mContext = this;
    }

    private void init() {
        mBtntakePhoto = (Button) findViewById(R.id.btn_take_photo);
        mBtnChoosePhoto = (Button) findViewById(R.id.btn_choose_photo);
        mBtntakePhoto.setOnClickListener(this);
        mBtnChoosePhoto.setOnClickListener(this);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_take_photo:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageName = System.currentTimeMillis()+".png";
                savePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/" + getPackageName();
                CameraOperation.startCamera(MainActivity.this, savePath
                        , imageName);
                break;
            case R.id.btn_choose_photo:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            switch (requestCode) {
                case CameraOperation.REQUEST_CODE_IMAGE_CAPTURE:
                    ImageOperation.addWatermark(savePath+"/"+imageName, "我是一个水印", ImageOperation.BOTTOM_RIGHT,
                            new ImageOperation.OnAddWatermark() {
                                @Override
                                public void onSuccess(String photoPath) {
                                    mIvImage.setImageURI(Uri.parse("file://" + photoPath));
                                }

                                @Override
                                public void onFailure(String photoPath) {

                                }
                            });
                    break;
            }
        }

    }
}
