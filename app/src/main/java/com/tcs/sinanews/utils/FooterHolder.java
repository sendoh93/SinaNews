package com.tcs.sinanews.utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.tcs.sinanews.R;

/**
 * Created by hzypf on 2017/3/13.
 */

public class FooterHolder extends RecyclerView.ViewHolder {
    View mLoadingViewstubstub;
    View mEndViewstub;
    View mNetworkErrorViewstub;

    public FooterHolder(View itemView) {
        super(itemView);
        mLoadingViewstubstub = itemView.findViewById(R.id.loading_viewstub);
        mEndViewstub = itemView.findViewById(R.id.end_viewstub);
        mNetworkErrorViewstub = itemView.findViewById(R.id.network_error_viewstub);
    }

    //根据传过来的status控制哪个状态可见
    public void setData(LoadingFooter.FooterState status) {
        Log.d("TAG", "reduAdapter" + status + "");
        switch (status) {
            case Normal:
                setAllGone();
                break;
            case Loading:
                setAllGone();
                mLoadingViewstubstub.setVisibility(View.VISIBLE);
                break;
            case TheEnd:
                setAllGone();
                mEndViewstub.setVisibility(View.VISIBLE);
                break;
            case NetWorkError:
                setAllGone();
                mNetworkErrorViewstub.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

    }

    //全部不可见
           void setAllGone() {
                    if (mLoadingViewstubstub != null) {
                            mLoadingViewstubstub.setVisibility(View.GONE);
                         }
                   if (mEndViewstub != null) {
                            mEndViewstub.setVisibility(View.GONE);
                        }
                   if (mNetworkErrorViewstub != null) {
                             mNetworkErrorViewstub.setVisibility(View.GONE);
                        }
               }
}
