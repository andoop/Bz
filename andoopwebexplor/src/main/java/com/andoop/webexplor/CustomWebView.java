package com.andoop.webexplor;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.andoop.webexplor.core.CustomWebChromClient;
import com.andoop.webexplor.core.CustomWebViewClient;
import com.andoop.webexplor.listeners.WebViewEventListener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/24
* explain：定制webview
* * * * * * * * * * * * * * * * * * */

public class CustomWebView extends WebView {
    private CustomWebChromClient webChromClient;
    private CustomWebViewClient webViewClient;
    public CustomWebView(Context context) {
        super(context);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //启用js
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        //禁用缩放
        settings.setSupportZoom(false);
        //关闭调试
        enableDebugging(false);
        //设置chromeclient
        webChromClient=new CustomWebChromClient();
        webViewClient=new CustomWebViewClient();
        setWebChromeClient(webChromClient);
        setWebViewClient(webViewClient);

        //设置布局
       // setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
    //webview调试开关
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void enableDebugging(boolean enable){
        setWebContentsDebuggingEnabled(enable);
    }

    public void setWebViewEventListener(WebViewEventListener webViewEventListener){
        webChromClient.setWebViewEventListener(webViewEventListener);
        webViewClient.setWebViewEventListener(webViewEventListener);
    }

    public void loadUrlForSelf(String url){
        Uri uri = Uri.parse(url).buildUpon().appendQueryParameter("andoop_openself", "1").build();
        loadUrl(uri.toString());
    }
}
