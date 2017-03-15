package com.tcs.sinanews.ui.adapter;

import com.bigkoo.convenientbanner.ConvenientBanner;

/**
 * Created by hzypf on 2017/3/15.
 */

public interface IBannerClick {
    void onBannerClick(int position);
    void getBanner(ConvenientBanner banner);
}
