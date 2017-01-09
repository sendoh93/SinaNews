package com.tcs.sinanews.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tcs.sinanews.R;
import com.tcs.sinanews.bean.NewsCode;
import com.tcs.sinanews.bean.NewsList;
import com.tcs.sinanews.netwrok.NewAPiServer;
import com.tcs.sinanews.netwrok.RetrofitUtils;
import com.tcs.sinanews.ui.activity.BaseActivity;
import com.tcs.sinanews.ui.activity.WebViewActivity;
import com.tcs.sinanews.widget.CustomProgressDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    private List<NewsList> news = new ArrayList<>();
    private SparseArray<List<NewsList>> showNews = new SparseArray<>();
    private SparseArray<String> parms = new SparseArray<>();
    private RecyclerView.Adapter<MyViewHolder> mAdapter;
    private Integer mNewsType;
    private Call<NewsCode> mobile;
    private CustomProgressDialog mDialog;
    private long startTime;
    private NewAPiServer mNewAPiServer;

    public static NewsFragment newInstance(Integer newsType) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("newsType", newsType);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView mSimpleDraweeView;
        public TextView mMTv;
        public LinearLayout mLlnews;

        public MyViewHolder(View itemView) {
            super(itemView);
            mSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.new_pic);
            mMTv = (TextView) itemView.findViewById(R.id.tv_news_title);
            mLlnews = (LinearLayout) itemView.findViewById(R.id.ll_news);
        }
    }

    private void getData(int type) {
        parms.put(0, "c25e79b36387196ad9cf3620f4fe059a");
        parms.put(1, "30");
        mNewAPiServer = RetrofitUtils.newInstance();
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
                    Handler handler = new Handler();
                    if (endTime - startTime <= 1600) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mDialog.dismiss();
                                mAdapter.notifyDataSetChanged();
                                mSwlRefresh.setRefreshing(false);
                            }
                        }, 1600 - endTime + startTime);
                    }else {
                        mDialog.dismiss();
                        mAdapter.notifyDataSetChanged();
                        mSwlRefresh.setRefreshing(false);
                    }

                }
            }

            @Override
            public void onFailure(Call<NewsCode> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void init(Bundle savedInstanceState) {

        mNewsType = getArguments().getInt("newsType", 0);


    }

    @Override
    public int getResourceId() {
        return R.layout.fragmetn_news;
    }

    @Override
    public void initView(View view) {
        //下拉监听
        mSwlRefresh.setProgressViewEndTarget(true,170);
        mSwlRefresh.setColorSchemeResources(R.color.toolbar);
        mSwlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadMobile(mNewAPiServer,mNewsType);
            }
        });

        mAdapter = new RecyclerView.Adapter<MyViewHolder>() {
            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = View.inflate(getActivity(), R.layout.item_news, null);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, final int position) {
                holder.mSimpleDraweeView.setImageURI(news.get(position).getPicUrl());
                holder.mMTv.setText(news.get(position).getTitle());
                holder.mLlnews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("newsUrl", news.get(position).getUrl());
                        ((BaseActivity) mContext).startActivity(WebViewActivity.class, bundle);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return news.size();
            }
        };
        mRvNews.setAdapter(mAdapter);
        mRvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void onFragmentFirstVisible() {

        getData(mNewsType);


    }


}
