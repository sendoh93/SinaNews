package com.tcs.sinanews.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.tcs.sinanews.R;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2017/2/2.
 */

public class WelcomeActivity extends BaseActivity {
    @Bind(R.id.ll_background)
    LinearLayout mLlBackground;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private TimerTask mTimerTaskOver;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected boolean applySystemBarDrawable() {
        return false;
    }

    @Override
    protected int getTitleResId() {
        return 0;
    }

    @Override
    protected boolean needToolBarButton() {
        return false;
    }

    @Override
    public void initBefore() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void initView() {
        String Rid = JPushInterface.getRegistrationID(this);
        Log.e("jiguang---","Rid ===="+Rid);
        JPushInterface.setAlias(this, "ypfAndroid", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Log.e("jiguang-------","Alias :" + i);
                if (i == 0)
                {
                    Log.e("jiguang------","设置别名成功咯");
                }
            }
        });
        Animation animotion = new ScaleAnimation(1f,1f,1.2f,1f,0.5f,0.5f);
        animotion.setDuration(2000);
        mLlBackground.clearAnimation();
        mLlBackground.startAnimation(animotion);
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
            }
        };
        mTimerTaskOver = new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        };

        mTimer.schedule(mTimerTask,3000);
        mTimer.schedule(mTimerTaskOver,4000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        mTimerTask.cancel();
        mTimerTaskOver.cancel();
    }
}
