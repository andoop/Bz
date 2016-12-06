package com.andoop.webexplor.presenter;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/24
* explain：exploractivity对应的presenter
* * * * * * * * * * * * * * * * * * */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;

import com.andoop.webexplor.AndoopExplor;
import com.andoop.webexplor.core.ExplorConfig;
import com.andoop.webexplor.listeners.WebViewEventListener;
import com.andoop.webexplor.presenter.view.IExplor;

public class ExplorPresenter implements WebViewEventListener{
    private Activity activity;
    private IExplor iExplor;
    private ExplorConfig config;
    private boolean opennew=true;
    private boolean opensystem=false;

    public ExplorPresenter(Activity activity, IExplor iExplor){
        this.activity = activity;
        this.iExplor = iExplor;
        initData();
    }
    //解析数据
    private void initData() {
        Intent intent = activity.getIntent();
        if(intent!=null){
            Bundle extras = intent.getExtras();
            if(extras!=null){
               this.config = (ExplorConfig) extras.getSerializable("config");
                opennew=extras.getBoolean("opennew");
                opensystem=extras.getBoolean("opensystem");
                String url = extras.getString("url");
                if(opensystem){
                    openSystem(url);
                    return;
                }
                //设置webview
                iExplor.enableOprate(config.isShowOperate());
                iExplor.enableOprates(config.isShowBack(),config.isShowWeb(),config.isShowRefresh(),config.isShowMore());
                iExplor.loadUrl(url);

            }else {
                Log.e("----->", "initData: "+"extra is null");
            }
        }else {
            Log.e("----->", "initData: "+"inite is null");
        }
    }

    //用系统浏览器打开
    private void openSystem(String url) {
       iExplor.openSys(Uri.parse(url));
    }
    private boolean shouldOverride(String url){
      if(url.contains("andoop_openself")){
          iExplor.loadUrl(url);
          return true;
      }
        if(opensystem){
            iExplor.openSys(Uri.parse(url));
            return true;
        }
        if(opennew){
            AndoopExplor.getInstance().showUrl(activity,url,opennew,opensystem);
            return true;
        }else if(config.isOpenNew()){
            AndoopExplor.getInstance().showUrl(activity,url,opennew,opensystem,config);
            return true;
        }else {
            iExplor.loadUrl(url);
        }
        return false;
    }
/***********************************************************/
    @Override
    public void onProgressChanged(int newProgress) {
        iExplor.showProgress(newProgress);
    }

    @Override
    public void onReceivedTitle(String title) {
        iExplor.showTitle(title);
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
        iExplor.showLoading();
    }

    @Override
    public void onPageFinished(String url) {
         iExplor.finishLoading();
    }

    @Override
    public void onReceivedError(int errorCode, String description, String failingUrl) {
        iExplor.loadingError(description);
    }

    @Override
    public void doUpdateVisitedHistory(String url, boolean isReload) {

    }

    @Override
    public void onReceivedSslError(SslErrorHandler handler, SslError error) {

    }
}
