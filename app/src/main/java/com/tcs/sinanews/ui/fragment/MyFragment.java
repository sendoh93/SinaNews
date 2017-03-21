package com.tcs.sinanews.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tcs.sinanews.R;
import com.tcs.sinanews.ui.dialog.DialogClick;
import com.tcs.sinanews.ui.dialog.normalDialog;
import com.tcs.sinanews.utils.DataCleanManager;
import com.tcs.sinanews.utils.SnackbarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/19.
 */

public class MyFragment extends BaseFragment {
    @Bind(R.id.tv_cache_size)
    TextView mTvCacheSize;
    @Bind(R.id.ll_my)
    LinearLayout mLlMy;
    private String mCacheSize;
    private normalDialog mDialog;

    @Override
    public void init(Bundle savedInstanceState) {

    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        mTvCacheSize.setText(getCacheSize());
    }

    @Override
    protected void onFragmentFirstVisible() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.ll_clean_cache)
    public void onClick() {
        mDialog = new normalDialog(mContext, "确定要清楚缓存吗?", "取消", "确定", false, new DialogClick() {
            @Override
            public void clickYes() {
                DataCleanManager.cleanInternalCache(getActivity());
                Fresco.getImagePipeline().clearCaches();
                mDialog.dismiss();
                mTvCacheSize.setText(getCacheSize());
                SnackbarUtil.ShortSnackbar(mLlMy,"清楚缓存成功",SnackbarUtil.Info).show();
            }

            @Override
            public void clickNo() {
                mDialog.dismiss();
            }
        });
        mDialog.show();

    }

    private String getCacheSize() {
        try {
            mCacheSize = DataCleanManager.getTotalCacheSize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != mCacheSize && !TextUtils.isEmpty(mCacheSize))
            return mCacheSize;
        else
            return "0kb";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
