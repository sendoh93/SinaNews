package com.tcs.sinanews.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by pingfan.yang on 2017/1/5.
 */
public class ToastUtil {
    public static void showToastLong(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
    public static void showToastOnUi(final Context context, final String str)
    {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,str,Toast.LENGTH_LONG).show();
            }
        });
    }
}
