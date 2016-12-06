package com.andoop.loginlib;

import android.app.Activity;

import com.andoop.loginlib.presenter.view.Ibase;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：
* * * * * * * * * * * * * * * * * * */
public class BasePresenter<T extends Ibase> {
    protected Activity mActivity;
    protected T mView;

    public BasePresenter(Activity activity,T view){
        mActivity = activity;
        this.mView=view;
    }
}
