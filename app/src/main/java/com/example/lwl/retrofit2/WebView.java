package com.example.lwl.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {
    private android.webkit.WebView mWbZb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWbZb= (android.webkit.WebView) findViewById(R.id.wb_zb);
        //启用支持javascript
        WebSettings settings = mWbZb.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        mWbZb.setBackgroundColor(0);
        mWbZb.loadUrl("file:///android_asset/index.html");
//        mWbZb.loadUrl("http://weblbs.yystatic.com/s/20507371/20507371/yycomscene.swf");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWbZb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    }

