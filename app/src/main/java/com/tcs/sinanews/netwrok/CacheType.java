package com.tcs.sinanews.netwrok;

/**
 * 缓存策略
 * Created by pingfan.yang on 2017/3/21.
 */
public interface CacheType {
    /**
     * 只查询网络数据
     */
    int NETWORK = 0;
    /**
     * 只查询本地缓存
     */
    int CACHE = 1;
    /**
     * 先查询本地缓存，如果本地没有，再查询网络数据
     */
    int CACHE_ELSE_NETWORK = 2;
    /**
     * 先查询网络数据，如果网络没有，再查询本地缓存
     */
    int NETWORK_ELSE_CACHE = 3;
}
