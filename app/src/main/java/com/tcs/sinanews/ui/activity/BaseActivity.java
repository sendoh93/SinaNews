package com.tcs.sinanews.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tcs.sinanews.R;
import com.tcs.sinanews.ui.activity.interfaces.ActivityInterface;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/29.
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityInterface{
    protected Toolbar mToolbar;

    protected Context mContext;

    protected abstract int getLayoutResId();
    protected abstract void init(Bundle savedInstanceState);
    protected abstract boolean applySystemBarDrawable();
    protected abstract int getTitleResId();
    protected abstract boolean needToolBarButton();

    @Override
    public void initBefore() {

    }

    protected boolean applyTranslucentStatus() {
        return true;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this ;
        setTranslucentStatus(applyTranslucentStatus());
        if (applySystemBarDrawable()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.drawable_primary));
        }
        Fresco.initialize(this);
        initBefore();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        if (null != mToolbar) {
            if (getTitleResId() != 0) {
                mToolbar.setTitle(getTitleResId());
            }
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(needToolBarButton());
            getSupportActionBar().setDisplayHomeAsUpEnabled(needToolBarButton());
        }
        init(savedInstanceState);
    }

    private void setSystemBarTintDrawable(Drawable tintDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            if (tintDrawable != null) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }
    }

    private void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    /**
     * 启动新的Activity
     * @param newActivty  新的Actiivty
     * @param bundle        需要传递的参数
     */
    public void startActivity(Class<? extends Activity> newActivty,Bundle bundle){
        Intent intent = new Intent(mContext,newActivty);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> newAcivity)
    {
        startActivity(newAcivity,null);
    }
}
