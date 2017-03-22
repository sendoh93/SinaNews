package com.tcs.sinanews.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.tcs.sinanews.MyApplication;
import com.tcs.sinanews.R;
import com.tcs.sinanews.ui.activity.LoginActivity;
import com.tcs.sinanews.ui.dialog.DialogClick;
import com.tcs.sinanews.ui.dialog.normalDialog;
import com.tcs.sinanews.utils.DataCleanManager;
import com.tcs.sinanews.utils.SnackbarUtil;
import com.tcs.sinanews.utils.StringUtils;

import org.simple.eventbus.Subscriber;

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
    @Bind(R.id.tv_login)
    TextView mTvLogin;
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
        if (!StringUtils.isEmpty(MyApplication.getUserName()))
            mTvLogin.setText(MyApplication.getUserName());
    }

    @Override
    protected void onFragmentFirstVisible() {

    }

    @Subscriber(tag = "update")
    public void onMessageEvent(String update){
        mTvLogin.setText(MyApplication.getUserName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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


    @OnClick({R.id.sv_head, R.id.tv_login, R.id.ll_clean_cache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sv_head:
                if (judgeLogin())
                    startActivity(LoginActivity.class);
                else
                    mDialog = new normalDialog(mContext, "确定登出账号么?", "取消", "确定", false, new DialogClick() {
                        @Override
                        public void clickYes() {
                            EMClient.getInstance().logout(true, new EMCallBack() {

                                @Override
                                public void onSuccess() {
                                    // TODO Auto-generated method stub
                                    SnackbarUtil.LongSnackbar(mLlMy, "登出成功", SnackbarUtil.Info).show();
                                    MyApplication.setUserName("");
                                }

                                @Override
                                public void onProgress(int progress, String status) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onError(int code, String message) {
                                    // TODO Auto-generated method stub
                                    SnackbarUtil.LongSnackbar(mLlMy, "登出失败", SnackbarUtil.Alert).show();
                                }
                            });
                        }

                        @Override
                        public void clickNo() {
                            dismiss();
                        }
                    });
                break;
            case R.id.tv_login:
                if (judgeLogin())
                    startActivity(LoginActivity.class);
                else
                    break;
            case R.id.ll_clean_cache:
                mDialog = new normalDialog(mContext, "确定要清楚缓存吗?", "取消", "确定", false, new DialogClick() {
                    @Override
                    public void clickYes() {
                        DataCleanManager.cleanInternalCache(getActivity());
                        Fresco.getImagePipeline().clearCaches();
                        dismiss();
                        mTvCacheSize.setText(getCacheSize());
                        SnackbarUtil.ShortSnackbar(mLlMy, "清楚缓存成功", SnackbarUtil.Info).show();
                    }

                    @Override
                    public void clickNo() {
                        dismiss();
                    }
                });
                mDialog.show();
                break;
        }
    }

    private void dismiss() {
        if (null != mDialog && mDialog.isShowing())
            mDialog.dismiss();
    }

    private boolean judgeLogin() {
        return StringUtils.equals(mTvLogin.getText().toString(), "登录账号");
    }
}
