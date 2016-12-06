package com.andoop.webexplor.core;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.andoop.webexplor.listeners.WebViewEventListener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/24
* explain：This is an implementation of WebChromeClient for
     * use in handling JavaScript dialogs, favicons, titles, and the progress.
     * This will replace the current handler.
* * * * * * * * * * * * * * * * * * */

public class CustomWebChromClient extends WebChromeClient {
    private WebViewEventListener webViewEventListener;

    public void setWebViewEventListener(WebViewEventListener webViewEventListener){

        this.webViewEventListener = webViewEventListener;
    }
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if(webViewEventListener!=null)
            webViewEventListener.onProgressChanged(newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if(webViewEventListener!=null)
            webViewEventListener.onReceivedTitle(title);
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
        if(webViewEventListener!=null)
            webViewEventListener.onReceivedIcon(icon);
    }

    @Override
    public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
        super.onReceivedTouchIconUrl(view, url, precomposed);
        if(webViewEventListener!=null)
            webViewEventListener.onReceivedTouchIconUrl(url,precomposed);
    }
}
