package com.andoop.loginlib.presenter;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：登录页面presenter
* * * * * * * * * * * * * * * * * * */

import android.app.Activity;

import com.andoop.loginlib.AndoopLogin;
import com.andoop.loginlib.BasePresenter;
import com.andoop.loginlib.listener.OutLoginCallback;
import com.andoop.loginlib.presenter.view.Ilogin;

public class LoginPresenter extends BasePresenter<Ilogin> implements OutLoginCallback {

    public LoginPresenter(Activity activity, Ilogin view) {
        super(activity, view);
    }

    public void login(String name,String pass){
        mView.showloading();
        AndoopLogin.getInstance(mActivity).ioutLogin().onLogin(name,pass,this);
    }
/************************logincallback*******************************/
    @Override
    public void success() {
        mView.onSuccess();
    }

    @Override
    public void onFail(String error) {
        mView.onFail(error);
    }
    @Override
    public void notRegist() {
        mView.onFail("用户名不存在或密码错误！");
    }
}
