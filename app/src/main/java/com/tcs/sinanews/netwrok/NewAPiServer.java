package com.tcs.sinanews.netwrok;

import com.tcs.sinanews.bean.NewsCode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/29.
 */

public interface NewAPiServer {
    @GET("social/")
    Call<NewsCode> GetSocialNew(@Query("key") String key, @Query("num") String Number);

    @GET("it/")
    Call<NewsCode> GetItNew(@Query("key") String key, @Query("num") String Number);

    @GET("nba/")
    Call<NewsCode> GetNbaNew(@Query("key") String key, @Query("num") String Number);

    @GET("mobile/")
    Call<NewsCode> GetmobileNew(@Query("key") String key, @Query("num") String Number);

    @GET("keji/")
    Call<NewsCode> GetkejiNew(@Query("key") String key, @Query("num") String Number);

    @GET("huabian/")
    Call<NewsCode> GethuabianNew(@Query("key") String key, @Query("num") String Number);

    @GET("travel/")
    Call<NewsCode> GetTravel(@Query("key") String key, @Query("num") String Number);

    @GET("meinv/")
    Call<NewsCode> GetMeinv(@Query("key") String key, @Query("num") String Number);
}
