package com.tcs.sinanews.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.tcs.sinanews.Constant;
import com.tcs.sinanews.R;
import com.tcs.sinanews.bean.NewsCode;
import com.tcs.sinanews.bean.NewsList;
import com.tcs.sinanews.netwrok.NewAPiServer;
import com.tcs.sinanews.netwrok.RetrofitUtils;
import com.tcs.sinanews.ui.activity.BaseActivity;
import com.tcs.sinanews.ui.activity.WebViewActivity;
import com.tcs.sinanews.ui.adapter.NewsAdapter;
import com.tcs.sinanews.widget.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/29.
 */

public class NewsFragment extends BaseFragment {
    @Bind(R.id.rv_news)
    RecyclerView mRvNews;
    @Bind(R.id.swl_refresh)
    SwipeRefreshLayout mSwlRefresh;
    ConvenientBanner mBanner;
    private List<NewsList> news = new ArrayList<>();
    private SparseArray<List<NewsList>> showNews = new SparseArray<>();
    private SparseArray<String> parms = new SparseArray<>();
    private NewsAdapter mAdapter;
    private Integer mNewsType;
    private Call<NewsCode> mobile;
    private CustomProgressDialog mDialog;
    private long startTime;
    private NewAPiServer mNewAPiServer;
    //广告栏使用的数据
    private List<String> mBannerList = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager mLayoutManager;
    private int oldPages;
    private int newPages;
    private boolean isRrefresh = false;

    public static NewsFragment newInstance(Integer newsType) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("newsType", newsType);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    private void getData(int type) {
        parms.put(0, "c25e79b36387196ad9cf3620f4fe059a");
        parms.put(1, "15");
        mNewAPiServer = RetrofitUtils.newInstance(mContext);
        mDialog = new CustomProgressDialog(getActivity(), "正在加载中...", R.drawable.frame, R.style.ProgressDialog);
        mDialog.setCancelable(false);
        mDialog.show();
        loadMobile(mNewAPiServer, type);

        startTime = System.currentTimeMillis();
    }


    private void loadMobile(NewAPiServer newAPiServer, int type) {
        startTime = System.currentTimeMillis();
        switch (type) {
            case 0:
                mobile = newAPiServer.GetSocialNew(parms.get(0), parms.get(1));
                break;
            case 1:
                mobile = newAPiServer.GetItNew(parms.get(0), parms.get(1));
                break;
            case 2:
                mobile = newAPiServer.GetmobileNew(parms.get(0), parms.get(1));
                break;
            case 3:
                mobile = newAPiServer.GetkejiNew(parms.get(0), parms.get(1));
                break;
            case 4:
                mobile = newAPiServer.GetNbaNew(parms.get(0), parms.get(1));
                break;
            case 5:
                mobile = newAPiServer.GetTravel(parms.get(0), parms.get(1));
                break;
            case 6:
                mobile = newAPiServer.GetMeinv(parms.get(0), parms.get(1));
                break;
            default:
                mobile = newAPiServer.GetmobileNew(parms.get(0), parms.get(1));
        }

        mobile.enqueue(new Callback<NewsCode>() {
            @Override
            public void onResponse(Call<NewsCode> call, Response<NewsCode> response) {
                if (response.isSuccessful()) {
                    news = response.body().getNewslist();
                    long endTime = System.currentTimeMillis();

                       /* initBanner();*/
                    dialogDismiss();
                    if (news.size() == newPages) {
                        if (isRrefresh) {
                            mAdapter.replaceAll(news);
                            isRrefresh = false;
                        }
                        else
                            mAdapter.addAll(news.subList(oldPages, newPages));
                    } else {
                        if (isRrefresh) {
                            mAdapter.replaceAll(news);
                            isRrefresh = false;
                        }
                        else
                            mAdapter.addAll(news);
                    }
                    mSwlRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<NewsCode> call, Throwable t) {
               dialogDismiss();
                Toast.makeText(getActivity(), "请检查网络后重试", Toast.LENGTH_LONG).show();
                   setFootStates(Constant.NetWorkError);
            }
        });
    }

    private void dialogDismiss() {
        if (mDialog.isShowing() && mDialog != null)
            mDialog.dismiss();
    }


    @Override
    public void init(Bundle savedInstanceState) {

        mNewsType = getArguments().getInt("newsType", 0);


    }

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        //mBanner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        //mBanner.stopTurning();
    }


    @Override
    public int getResourceId() {
        return R.layout.fragmetn_news;
    }

    @Override
    public void initView(View view) {
        //下拉监听
        mSwlRefresh.setProgressViewEndTarget(true, 170);
        mSwlRefresh.setColorSchemeResources(R.color.toolbar);
        mSwlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                parms.setValueAt(1, "20");
                isRrefresh = true;
                loadMobile(mNewAPiServer, mNewsType);
            }
        });

        mAdapter = new NewsAdapter(mContext, news, new NewsAdapter.RvItemClick() {
            @Override
            public void ItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("newsUrl", news.get(position).getUrl());
                ((BaseActivity) mContext).startActivity(WebViewActivity.class, bundle);
                getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
            }
        });
        mRvNews.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRvNews.setLayoutManager(mLayoutManager);
        mRvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 >=
                        mLayoutManager.getItemCount()) {
                    oldPages = Integer.parseInt(parms.get(1));
                    if (oldPages < 50) {
                        if (oldPages > 35)
                            newPages = 50;
                        else
                            newPages = oldPages + 15;
                        setFootStates(Constant.Loading);
                        parms.setValueAt(1, String.valueOf(newPages));
                        loadMobile(mNewAPiServer, mNewsType);
                    } else {
                        setFootStates(Constant.TheEnd);
                    }

                }
            }
        });
    }

    private void setFootStates(int states){
        if (null != mAdapter && null != mAdapter.mFootViewHolder)
        mAdapter.mFootViewHolder.setData(states);
    }
    @Override
    protected void onFragmentFirstVisible() {

        getData(mNewsType);


    }


}
