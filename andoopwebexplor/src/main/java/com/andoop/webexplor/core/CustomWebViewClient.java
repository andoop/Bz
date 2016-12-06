package com.andoop.webexplor.core;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.andoop.webexplor.listeners.WebViewEventListener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/24
* explain：
* * * * * * * * * * * * * * * * * * */

public class CustomWebViewClient extends WebViewClient {

    private WebViewEventListener webViewEventListener;

    public void setWebViewEventListener(WebViewEventListener webViewEventListener){

        this.webViewEventListener = webViewEventListener;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        return webViewEventListener.shouldOverrideUrlLoading(request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return webViewEventListener.shouldOverrideUrlLoading(url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(webViewEventListener!=null)
            webViewEventListener.onPageStarted(url,favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(webViewEventListener!=null)
            webViewEventListener.onPageFinished(url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if(webViewEventListener!=null)
            webViewEventListener.onReceivedError(errorCode,description,failingUrl);
    }

    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
        if(webViewEventListener!=null)
            webViewEventListener.doUpdateVisitedHistory(url,isReload);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        if(webViewEventListener!=null)
            webViewEventListener.onReceivedSslError(handler,error);
    }
}
