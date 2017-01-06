package com.tcs.sinanews.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tcs.sinanews.R;

import butterknife.Bind;

/**
 * Created by pingfan.yang on 2017/1/6.
 */
public class WebViewActivity extends BaseActivity {
    @Bind(R.id.webview)
    WebView mWebview;
    @Bind(R.id.progress)
    ProgressBar mProgress;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("newsUrl");
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100)
                {
                    mProgress.setVisibility(View.GONE);
                }else {
                    if (View.GONE == mProgress.getVisibility())
                    {
                        mProgress.setVisibility(View.VISIBLE);
                    }
                    mProgress.setProgress(newProgress);

                }
                super.onProgressChanged(view, newProgress);
            }
        });

        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setSupportZoom(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setDisplayZoomControls(false);
        mWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.loadUrl(url);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebview.goBack();
            }
        });
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
        return true;
    }

}
