package com.andoop.ctrlf5.bangzhu.customview;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by domob on 2016/12/9.
 */

public class BzWebView extends WebView {
    public boolean loadForself=false;
    public BzWebView(Context context) {
        super(context);
        init(context);
    }

    public BzWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        //启用js
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        //禁用缩放
        settings.setSupportZoom(false);
        //关闭调试
        enableDebugging(true);
        //设置chromeclient


    }

    //webview调试开关
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void enableDebugging(boolean enable){
        setWebContentsDebuggingEnabled(enable);
    }

    public void loadUrlForSelf(String s) {
        loadUrl( Uri.parse(s)
                .buildUpon().appendQueryParameter("bznew","ok")
                .build().toString());
    }
}
