package com.tcs.sinanews.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcs.sinanews.Constant;
import com.tcs.sinanews.R;
import com.tcs.sinanews.ui.fragment.HotFragment;
import com.tcs.sinanews.ui.fragment.MainFragment;
import com.tcs.sinanews.ui.fragment.MyFragment;
import com.tcs.sinanews.ui.fragment.SocialityFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;
    @Bind(R.id.vp_all)
    ViewPager mVpAll;
    @Bind(R.id.tab_all)
    TabLayout mTabAll;


    private SparseArray<Fragment> mMainFragments;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initMain();
    }

    private void initMain() {
        setTab(mTabAll,getLayoutInflater(), Constant.main_fragements,Constant.main_img);
        mMainFragments = new SparseArray<>();
        mMainFragments.put(0,new MainFragment());
        mMainFragments.put(1,new SocialityFragment());
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
        return true;
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
