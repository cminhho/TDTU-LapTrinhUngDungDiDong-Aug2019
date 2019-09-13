package com.tdt.lab03.exercise03;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.tdt.lab03.R;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        String url = getIntent().getStringExtra("URL");

        // lookup views
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }

}