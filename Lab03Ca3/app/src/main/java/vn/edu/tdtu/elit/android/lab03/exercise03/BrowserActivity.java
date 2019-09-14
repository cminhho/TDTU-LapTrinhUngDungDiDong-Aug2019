package vn.edu.tdtu.elit.android.lab03.exercise03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import android.webkit.WebViewClient;
import java.net.URL;
import vn.edu.tdtu.elit.android.lab03.R;

public class BrowserActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise3_webview);

    handleIntent();
  }

  private void handleIntent() {
    Intent intent = getIntent();

    Uri data = intent.getData();
    URL url = null;

    try {
      url = new URL(data.getScheme(),
          data.getHost(),
          data.getPath());
    } catch (Exception e) {
      e.printStackTrace();
    }

    WebView webView = (WebView) findViewById(R.id.webView1);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(url.toString());
  }

}