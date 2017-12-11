package com.zachary.daidai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWebView();

    }
    private void initWebView() {
        WebView webView= (WebView) findViewById(R.id.wv2);
        webView.loadUrl(getIntent().getStringExtra("url"));
        WebSettings webSettings =webView.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){

                                     @Override
                                     //只要重写此方法，就能在本应用中加载网页
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                      /*  if (url.startsWith("http")||url.startsWith("https")){
                                            view.loadUrl(url);
                                            return true;
                                        }else {
                                            return false;
                                        }*/
                                         view.loadUrl(url);
                                         return true;
                                         //返回值时true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器
                                     }
                                     @Override
                                     public void onReceivedError(WebView view, int errorCode,
                                                                 String description, String failingUrl) {

                                     }

                                 }
        );
    }
}
