package com.tcs.sinanews.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tcs.sinanews.R;

/**
 * Created by Administrator on 2017/2/21.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView mSimpleDraweeView;
    public TextView mMTv;
    public LinearLayout mLlnews;

    public NewsViewHolder(View itemView) {
        super(itemView);
        mSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.new_pic);
        mMTv = (TextView) itemView.findViewById(R.id.tv_news_title);
        mLlnews = (LinearLayout) itemView.findViewById(R.id.ll_news);
    }
}
