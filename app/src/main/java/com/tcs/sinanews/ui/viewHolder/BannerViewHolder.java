package com.tcs.sinanews.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.tcs.sinanews.R;

/**
 * Created by hzypf on 2017/3/15.
 */

public class BannerViewHolder extends RecyclerView.ViewHolder {
   public ConvenientBanner mBanner;
    public BannerViewHolder(View itemView) {
        super(itemView);
        mBanner = (ConvenientBanner) itemView.findViewById(R.id.banner);
    }
}
