package com.andoop.webexplor.presenter.view;

import android.net.Uri;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/24
* explain：exploractivity协议接口
* * * * * * * * * * * * * * * * * * */
public interface IExplor extends IBaseView {
    void loadUrl(String url);
    void showLoading();
    void showProgress(int persent);
    void finishLoading();
    void loadingError(String err);
    void enableOprate(boolean enable);
    void enableOprates(boolean showback, boolean showweb, boolean showrefresh, boolean showmore);
    void showTitle(String title);

    void openSys(Uri uri);
}
