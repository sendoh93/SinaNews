package com.tcs.sinanews.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;

import com.tcs.sinanews.Constant;
import com.tcs.sinanews.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/2/19.
 */

public class MainFragment extends BaseFragment {
    @Bind(R.id.tab_news)
    TabLayout mTabNews;
    @Bind(R.id.vp_news)
    ViewPager mVpNews;
    private SparseArray<Fragment> mSparseArray = new SparseArray<>();
    @Override
    public void init(Bundle savedInstanceState) {
    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        initNews();
    }

    @Override
    protected void onFragmentFirstVisible() {

    }
    //初始化新闻fragment
    private void initNews() {
        for (int i = 0; i < Constant.framgets.length; i++) {
            mSparseArray.put(i, NewsFragment.newInstance(i));
        }
        mVpNews.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
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
}
