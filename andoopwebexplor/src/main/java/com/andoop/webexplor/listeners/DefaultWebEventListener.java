package com.andoop.webexplor.listeners;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;

import com.andoop.webexplor.AndoopExplor;

/**
 * Created by domob on 2016/11/24.
 */

public  class DefaultWebEventListener implements WebViewEventListener {
    private Activity activity;

    public DefaultWebEventListener(Activity activity){

        this.activity = activity;
    }
    @Override
    public void onProgressChanged(int newProgress) {

    }

    @Override
    public void onReceivedTitle(String title) {

    }

    @Override
    public void onReceivedIcon(Bitmap icon) {

    }

    @Override
    public void onReceivedTouchIconUrl(String url, boolean precomposed) {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebResourceRequest request) {
        return shouldOverride(request.getUrl().toString());
    }

    @Override
    public boolean shouldOverrideUrlLoading(String url) {
        return shouldOverride(url);
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onReceivedError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void doUpdateVisitedHistory(String url, boolean isReload) {

    }

    @Override
    public void onReceivedSslError(SslErrorHandler handler, SslError error) {

    }

    private boolean shouldOverride(String url){
        if(url.contains("openself")){
            return false;
        }else {
            AndoopExplor.getInstance().showUrl(activity,url,false,false);
            return true;
        }
    }
}
