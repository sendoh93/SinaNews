package com.tcs.sinanews.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.widget.LinearLayout;

import com.tcs.sinanews.Constant;
import com.tcs.sinanews.R;
import com.tcs.sinanews.ui.fragment.NewsFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab_news)
    TabLayout mTabNews;
    @Bind(R.id.vp_news)
    ViewPager mVpNews;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;

    private SparseArray<Fragment> mSparseArray = new SparseArray<>();
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        for (int i = 0 ;i< Constant.framgets.length;i++)
        {
            mSparseArray.put(i,NewsFragment.newInstance(i));
        }
        mVpNews.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mSparseArray.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Constant.framgets[position].toString();
            }

            @Override
            public int getCount() {
                return mSparseArray.size();
            }
        });
        mVpNews.setOffscreenPageLimit(Constant.framgets.length);
        mTabNews.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabNews.setupWithViewPager(mVpNews);
    }

    @Override
    protected boolean applySystemBarDrawable() {
        return true;
    }

    @Override
    protected int getTitleResId() {
        return Constant.Titles[0];
    }

}
