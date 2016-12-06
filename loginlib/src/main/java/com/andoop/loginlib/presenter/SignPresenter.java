package com.andoop.loginlib.presenter;

import android.app.Activity;

import com.andoop.loginlib.AndoopLogin;
import com.andoop.loginlib.BasePresenter;
import com.andoop.loginlib.listener.OutSignCallback;
import com.andoop.loginlib.presenter.view.Isign;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：注册页面presenter
* * * * * * * * * * * * * * * * * * */
public class SignPresenter extends BasePresenter<Isign> implements OutSignCallback {

    public SignPresenter(Activity activity, Isign view) {
        super(activity, view);
    }

    public void sign(String name, String pass, String phone, String answer) {
        mView.showloading();
        AndoopLogin.getInstance(mActivity).ioutSign().onSign(name,pass,phone,answer,this);
    }

    @Override
    public void success() {
        mView.onSuccess();
    }

    @Override
    public void fail(String error) {
        mView.onFail(error);
    }
}
