package com.tcs.sinanews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.tcs.sinanews.Constant;
import com.tcs.sinanews.R;
import com.tcs.sinanews.ui.fragment.HotFragment;
import com.tcs.sinanews.ui.fragment.MainFragment;
import com.tcs.sinanews.ui.fragment.MyFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;
    @Bind(R.id.vp_all)
    ViewPager mVpAll;
    @Bind(R.id.tab_all)
    TabLayout mTabAll;


    private SparseArray<Fragment> mMainFragments;
    private EaseConversationListFragment conversationListFragment;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initMain();
    }

    private void initMain() {
        conversationListFragment = new EaseConversationListFragment();
        setTab(mTabAll,getLayoutInflater(), Constant.main_fragements,Constant.main_img);
        mMainFragments = new SparseArray<>();
        mMainFragments.put(0,new MainFragment());
        mMainFragments.put(1,conversationListFragment);
        mMainFragments.put(2,new HotFragment());
        mMainFragments.put(3, new MyFragment());
        mVpAll.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mMainFragments.get(position);
            }

            @Override
            public int getCount() {
                return mMainFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Constant.main_fragements[position];
            }
        });
        mVpAll.setOffscreenPageLimit(Constant.main_fragements.length);
     /*   mTabAll.setupWithViewPager(mVpAll);*/
        mVpAll.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabAll));
        mTabAll.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mVpAll));
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId())
                .putExtra(EaseConstant.EXTRA_CHAT_TYPE,conversation.getType()));
            }
        });
    }

    private void setTab(TabLayout tablayout, LayoutInflater inflater,String[] title
    ,int[] imgs){
        for (int i=0; i<imgs.length;i++)
        {
            TabLayout.Tab tab =tablayout.newTab();
            View view = inflater.inflate(R.layout.tab_bottom,null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.tv_bottom);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_bottom);
            tvTitle.setText(title[i]);
            imgTab.setImageResource(imgs[i]);
            tablayout.addTab(tab);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
                mVpAll.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_CANCEL:
                mVpAll.requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.onTouchEvent(event);
    }



    @Override
    protected boolean applySystemBarDrawable() {
        return false;
    }

    @Override
    protected int getTitleResId() {
        return Constant.Titles[0];
    }

    @Override
    protected boolean needToolBarButton() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
