package com.tcs.sinanews.netwrok;

import android.content.Context;
import android.util.Log;

import com.tcs.sinanews.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by pingfan.yang on 2017/3/21.
 */
public class ResponseInfoInterceptor implements Interceptor {
    private Context mContext;
    public ResponseInfoInterceptor(Context context) {
        mContext  = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkReachable(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.e("network:----------","no network");
        }
        Response response = chain.proceed(request);
        if (NetworkUtils.isNetworkReachable(mContext)){
            int maxTime = 0* 60 ; //有网络时， 缓存时间为0
            Log.e("network-time:----",maxTime+"");
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxTime)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Cache-Control")
                    .build();
        }else {
            int cacheTime = 60 * 60 * 24 * 7; //设置缓存保存时间为7天
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, cacheTime=" + cacheTime)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}

