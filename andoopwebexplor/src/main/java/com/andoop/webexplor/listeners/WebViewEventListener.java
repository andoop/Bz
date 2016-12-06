package com.andoop.webexplor.listeners;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;

/**
 * Created by domob on 2016/11/24.
 */

public interface WebViewEventListener {
     void onProgressChanged(int newProgress);

     void onReceivedTitle(String title);

     void onReceivedIcon(Bitmap icon);

     void onReceivedTouchIconUrl(String url, boolean precomposed);

    public boolean shouldOverrideUrlLoading(WebResourceRequest request);

    public boolean shouldOverrideUrlLoading(String url) ;

    public void onPageStarted(String url, Bitmap favicon);

    public void onPageFinished(String url);

    public void onReceivedError(int errorCode, String description, String failingUrl);

    public void doUpdateVisitedHistory(String url, boolean isReload);

    public void onReceivedSslError(SslErrorHandler handler, SslError error);
}
