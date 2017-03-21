package com.tcs.sinanews.netwrok;

import com.tcs.sinanews.utils.FileUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by pingfan.yang on 2017/3/21.
 */
public class OkHttpManager {
    private static int CONNECT_TIMEOUT_MILLIS;//连接时间超时
    private static int WRITE_TIMEOUT_MILLIS;//写入时间超时
    private static int READ_TIMEOUT_MILLIS;//读取时间超时
    private int mCacheSize;
    private String mCacheDirectoryPath;
    private volatile List<Interceptor> mInterceptors;//应用拦截器组
    private volatile List<Interceptor> mNetworkInterceptors;//网络拦截器组

    public OkHttpManager(String cacheDirectoryPath, int cacheSize, int connect_timeout) {
        mCacheDirectoryPath = cacheDirectoryPath;
        mCacheSize = cacheSize;
        mInterceptors = new LinkedList<>();
        mNetworkInterceptors = new LinkedList<>();

        CONNECT_TIMEOUT_MILLIS = connect_timeout;
       /* WRITE_TIMEOUT_MILLIS = write_timeout;
        READ_TIMEOUT_MILLIS = read_timeout;*/

    }


    /**
     * 添加应用拦截器
     *
     * @param interceptor 拦截器
     * @return OkHttpManager
     */
    public OkHttpManager addInterceptor(Interceptor interceptor) {
        mInterceptors.add(interceptor);
        return this;
    }

    /**
     * 添加应用拦截器
     *
     * @param interceptors 拦截器组
     */
    public OkHttpManager addInterceptors(List<Interceptor> interceptors) {
        mInterceptors.addAll(interceptors);
        return this;
    }

    /**
     * 添加网络拦截器
     *
     * @param interceptor 拦截器
     * @return OkHttpManager
     */
    public OkHttpManager addNetworkInterceptor(Interceptor interceptor) {
        mNetworkInterceptors.add(interceptor);
        return this;
    }

    /**
     * 添加网络拦截器
     *
     * @param interceptors 拦截器组
     */
    public OkHttpManager addNetworkInterceptors(List<Interceptor> interceptors) {
        mNetworkInterceptors.addAll(interceptors);
        return this;
    }

    private OkHttpClient generateOkHttpClient(List<Interceptor> interceptors, List<Interceptor> networkInterceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS);
//        builder.writeTimeout(WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS);
//        builder.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.SECONDS);
        setCache(builder);
        if (interceptors != null && interceptors.size() > 0)
            builder.interceptors().addAll(interceptors);
        if (networkInterceptors != null && networkInterceptors.size() > 0)
            builder.networkInterceptors().addAll(networkInterceptors);
        return builder.build();
    }

    /**
     * 获得缓存器
     *
     * @param builder OkHttpClient建造器
     */
    private void setCache(OkHttpClient.Builder builder) {
        File cacheDirectory = new File(mCacheDirectoryPath);
        FileUtils.createMkdirs(cacheDirectory);
        builder.cache(new Cache(cacheDirectory, mCacheSize));
    }

    /**
     * 构建OkHttpClient
     *
     * @return OkHttpClient
     */
    public OkHttpClient build() {
        return generateOkHttpClient(mInterceptors, mNetworkInterceptors);
    }
}
