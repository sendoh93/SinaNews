package com.tcs.sinanews.ui.activity;

import android.os.Bundle;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.tcs.sinanews.R;

/**
 * Created by hzypf on 2017/3/29.
 */

public class ChatActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_chat;
    }

    @Override
    public void initBefore() {
        super.initBefore();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//new出EaseChatFragment或其子类的实例
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }

    @Override
    protected boolean applySystemBarDrawable() {
        return true;
    }

    @Override
    protected int getTitleResId() {
        return 0;
    }

    @Override
    protected boolean needToolBarButton() {
        return false;
    }
}
