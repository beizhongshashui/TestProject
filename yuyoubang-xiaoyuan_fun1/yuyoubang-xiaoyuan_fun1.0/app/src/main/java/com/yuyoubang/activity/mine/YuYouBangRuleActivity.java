package com.yuyoubang.activity.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yuyoubang.R;
import com.yuyoubang.activity.base.BaseActivity;

/**
 * Created by hongchen on 16/12/23.
 */

public class YuYouBangRuleActivity extends BaseActivity {
    private String url;
    private WebView mWv;
    private ProgressBar mPb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        setListener();
    }

    private void initViews() {
        url = getIntent().getStringExtra("url");
        mWv = getViewById(R.id.wv);
        mPb = getViewById(R.id.pb);
    }

    private void setListener() {
        mWv.setWebViewClient(new WebViewClient());
        mWv.getSettings().setJavaScriptEnabled(true);
        mWv.getSettings().setDefaultTextEncodingName("gb2312");
        mWv.setWebChromeClient(new WebChromeClient());
        mWv.getSettings().setDomStorageEnabled(true);
        mWv.getSettings().setDatabaseEnabled(true);
        if (!TextUtils.isEmpty(url)) {
            mWv.loadUrl(url);
        }
    }

    @Override
    protected void initTitleBar(HeaderBuilder builder) {
        int position = getIntent().getIntExtra("position", -1);
        String title = getIntent().getStringExtra("title");
        switch (position) {
            case 1:
                builder.setTitle("遇游邦邦主管理规则");
                break;
            case 2:
                builder.setTitle("结算说明");
                break;
            case 3:
                builder.setTitle("用户协议");
                break;
            case 4:
                builder.setTitle("报名须知");
                break;
            case 5:
                builder.setTitle(title);
                break;
        }
    }


    @Override
    protected int getContentResId() {
        return R.layout.activity_webview;
    }

    @Override
    public void onResume() {

        /**
         * WebView加载过程
         */
        mWv.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        mWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                mPb.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }

        });
        WebSettings webSettings = mWv.getSettings(); // webView:
        // 类WebView的实例

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
