package com.tcs.sinanews.utils;

/**
 * Created by hzypf on 2017/3/13.
 */

public abstract class LoadingFooter {
    enum FooterState {
        Normal,
        Loading,
        TheEnd,
        NetWorkError
    }
}
