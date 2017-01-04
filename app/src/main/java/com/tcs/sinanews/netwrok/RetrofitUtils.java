package com.tcs.sinanews.netwrok;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by pingfan.yang on 2017/1/1.
 */
public class RetrofitUtils {
    //获取实例
    public static NewAPiServer newInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                //设置OKHttpClient,如果不设置会提供一个默认的
                .client(new OkHttpClient())
                //设置baseUrl
                .baseUrl("http://api.tianapi.com/")
                //添加LoganSquare转换器
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();
        NewAPiServer newAPiServer = retrofit.create(NewAPiServer.class);
        return newAPiServer;
    }


 /*   //同步请求
//https://api.github.com/users/octocat/repos
    Call<List<Repo>> call = service.listRepos("octocat");
    try {
        Response<List<Repo>> repos  = call.execute();
    } catch (IOException e) {
        e.printStackTrace();
    }*/
}
