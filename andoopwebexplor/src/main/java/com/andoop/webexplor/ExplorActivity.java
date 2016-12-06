package com.andoop.webexplor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.webexplor.presenter.ExplorPresenter;
import com.andoop.webexplor.presenter.view.IExplor;


/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/24
* explain：浏览器页面
* * * * * * * * * * * * * * * * * * */
public class ExplorActivity extends Activity implements IExplor{
    CustomWebView customWebView;
    ImageView iv_close;
    ImageView iv_back;
    ImageView iv_refresh;
    ImageView iv_web;
    ImageView iv_more;
    FrameLayout fl_back;
    FrameLayout fl_refresh;
    FrameLayout fl_web;
    FrameLayout fl_more;
    LinearLayout ll_oprate;
    TextView tv_title;
    ProgressBar pb_progress;
    private ExplorPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explor);
        initLayout();
        //初始化presenter
        mPresenter=new ExplorPresenter(this,this);
        customWebView.setWebViewEventListener(mPresenter);
    }

    private void initLayout() {
         customWebView= (CustomWebView) findViewById(R.id.wv_explor);
         iv_close= (ImageView) findViewById(R.id.iv_close_explor);
         iv_back= (ImageView) findViewById(R.id.iv_back_explor);
         iv_refresh= (ImageView) findViewById(R.id.iv_refresh_explor);
         iv_web= (ImageView) findViewById(R.id.iv_web_explor);
         iv_more= (ImageView) findViewById(R.id.iv_more_explor);
         fl_back= (FrameLayout) findViewById(R.id.fl_back_explor);
         fl_refresh= (FrameLayout) findViewById(R.id.fl_refresh_explor);
         fl_web= (FrameLayout) findViewById(R.id.fl_web_explor);
         fl_more= (FrameLayout) findViewById(R.id.fl_more_explor);
         ll_oprate= (LinearLayout) findViewById(R.id.ll_oprate_explor);
         tv_title= (TextView) findViewById(R.id.tv_title_explor);
         pb_progress= (ProgressBar) findViewById(R.id.pb_progress_explor);
    }

    public void close(View view){
        finish();
    }
    public void refresh(View view){
        customWebView.reload();
    }
    public void openSystem(View vew){
      openSys(Uri.parse(customWebView.getOriginalUrl()));
    }
    public void back(View view){
        if(customWebView.canGoBack()){
            customWebView.goBack();
        }else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(customWebView.canGoBack()){
                customWebView.goBack();
                return true;
            }else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    public void more(View view){
        Toast.makeText(this, "功能正在完善", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadUrl(String url) {
        customWebView.loadUrlForSelf(url);
    }

    @Override
    public void showLoading() {
        pb_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(int persent) {
        pb_progress.setProgress(persent);
    }

    @Override
    public void finishLoading() {
        pb_progress.setVisibility(View.GONE);
    }

    @Override
    public void loadingError(String err) {
        pb_progress.setVisibility(View.GONE);
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enableOprate(boolean enable) {
        if(enable){
            ll_oprate.setVisibility(View.VISIBLE);
        }else {
            ll_oprate.setVisibility(View.GONE);
        }
    }

    @Override
    public void enableOprates(boolean showback, boolean showweb, boolean showrefresh, boolean showmore) {
        fl_back.setVisibility(showback?View.VISIBLE:View.GONE);
        fl_refresh.setVisibility(showrefresh?View.VISIBLE:View.GONE);
        fl_web.setVisibility(showweb?View.VISIBLE:View.GONE);
        fl_more.setVisibility(showmore?View.VISIBLE:View.GONE);
    }

    @Override
    public void showTitle(String title) {
        tv_title.setText(title);
    }

    @Override
    public void openSys(Uri uri) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
       // finish();
    }
}
