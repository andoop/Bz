package com.andoop.ctrlf5.bangzhu.view;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.customview.BzWebView;
import com.andoop.ctrlf5.bangzhu.modle.JsInterFace;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;

public class BzWebActivity extends AppCompatActivity {

    BzWebView bzWebView;
    String mUrl="";
    TextView tvleft;
    TextView tvTitle;
    String backText;
    private Dialog loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bz_web);
        bzWebView= (BzWebView) findViewById(R.id.bzwebview);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
           mUrl = extras.getString("url");
            backText=extras.getString("backname");
        }

        tvleft= (TextView) findViewById(R.id.tv_back_text);
        tvleft.setText(backText);
        tvTitle= (TextView) findViewById(R.id.tv_title);

        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bzWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingView = DialogUtils.showLoadingView(BzWebActivity.this);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                loadingView.dismiss();
            }
        });
        bzWebView.addJavascriptInterface(new JsInterFace(this),"ddb");
        bzWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                tvTitle.setText(title);
            }


        });
        bzWebView.loadUrl(mUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loadingView!=null)
            loadingView.dismiss();
    }
}
