package com.tcs.sinanews.ui;

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

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    protected abstract int getLayoutResId();
    protected abstract void init(Bundle savedInstanceState);
    protected abstract boolean applySystemBarDrawable();
    protected abstract int getTitleResId();

    protected boolean applyTranslucentStatus() {
        return true;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus(applyTranslucentStatus());
        if (applySystemBarDrawable()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.drawable_primary));
        }
        Fresco.initialize(this);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        if (null != mToolbar) {
            if (getTitleResId() != 0) {
                mToolbar.setTitle(getTitleResId());
            }
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
}
