package com.andoop.ctrlf5.bangzhu.view;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.customview.BzWebView;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.JsInterFace;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;
import com.andoop.webexplor.CustomWebView;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/6
* explain：广场页面
* * * * * * * * * * * * * * * * * * */

public class BzSquarePager extends BzBasePager {
    BzWebView webView;
    private Dialog loadingView;
    private int pos;

    @Override
    public void onSelect(AndoopPage andoopPage, int pos) {

        this.pos = pos;
    }

    @Override
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_square,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title.setText("话题");
        add.setVisibility(View.VISIBLE);
        webView= (BzWebView) getView().findViewById(R.id.faxian_wb);

        webView.addJavascriptInterface(new JsInterFace(getActivity()),"ddb");
        add.setImageResource(R.drawable.add_huati);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FaBuHuaTiActivity.class));
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return shouldOverrid(view,url);
            }
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return shouldOverrid(view,request.getUrl().toString());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if(pos==1)
                    loadingView = DialogUtils.showLoadingView(getActivity());
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(loadingView!=null)
                loadingView.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if(loadingView!=null)
                loadingView.dismiss();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        String uuu = Uri.parse(Api.page_topic).buildUpon()
                .appendQueryParameter("uid", BzUser.getCurentuser()
                        .getUserinfo().getUid() + "").build().toString();
        webView.loadUrlForSelf(uuu);
    }

    private boolean shouldOverrid(WebView webView, String uri){
        if(uri.contains("bznew=ok")){
            return false;
        }else {
            String uuu = Uri.parse(Api.page_topic).buildUpon()
                    .appendQueryParameter("uid", BzUser.getCurentuser()
                            .getUserinfo().getUid() + "").build().toString();
            Intent intent = new Intent(getActivity(), BzWebActivity.class);
            Bundle extra=new Bundle();
            extra.putString("url",uuu);
            extra.putString("backname","话题");
            intent.putExtras(extra);
            startActivity(intent);
            return true;
        }

    }
}
