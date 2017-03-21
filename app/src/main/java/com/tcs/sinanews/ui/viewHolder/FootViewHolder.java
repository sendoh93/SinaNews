package com.tcs.sinanews.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tcs.sinanews.Constant;
import com.tcs.sinanews.R;

/**
 * Created by Administrator on 2017/2/21.
 */

public class FootViewHolder extends RecyclerView.ViewHolder {
    View mLoadingViewstubstub;
    View mEndViewstub;
    View mNetworkErrorViewstub;
    public FootViewHolder(View itemView) {
        super(itemView);
        mLoadingViewstubstub = itemView.findViewById(R.id.loading_viewstub);
        mEndViewstub = itemView.findViewById(R.id.end_viewstub);
        mNetworkErrorViewstub = itemView.findViewById(R.id.network_error_viewstub);
    }
    public void setData(int states){
        switch (states){
            case Constant.Normal:
                setAllGone();
                break;
            case Constant.Loading:
                setAllGone();
                mLoadingViewstubstub.setVisibility(View.VISIBLE);
                break;
            case Constant.TheEnd:
                setAllGone();
                mEndViewstub.setVisibility(View.VISIBLE);
                break;
            case Constant.NetWorkError:
                setAllGone();
                mNetworkErrorViewstub.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    void setAllGone(){
        if (null != mLoadingViewstubstub)
            mLoadingViewstubstub.setVisibility(View.GONE);
        if (null != mEndViewstub)
            mEndViewstub.setVisibility(View.GONE);
        if (null != mNetworkErrorViewstub)
            mNetworkErrorViewstub.setVisibility(View.GONE);
    }
}
