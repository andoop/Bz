package com.andoop.loginlib.presenter;

import android.app.Activity;

import com.andoop.loginlib.AndoopLogin;
import com.andoop.loginlib.BasePresenter;
import com.andoop.loginlib.listener.OutLostFindCallback;
import com.andoop.loginlib.presenter.view.IlostFind;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：忘记密码页面persenter
* * * * * * * * * * * * * * * * * * */
public class LostFindPresenter extends BasePresenter<IlostFind> implements OutLostFindCallback {

    public LostFindPresenter(Activity activity, IlostFind view) {
        super(activity, view);
    }

    //找回密码
    public void find(String name, String phone, String answer) {
        mView.showloading();
        AndoopLogin.getInstance(mActivity).ioutLostFind().onFind(name,phone,answer,this);
    }

    @Override
    public void success(String password) {
        mView.onSuccess();
        mView.showPass(password);
    }

    @Override
    public void fail(String error) {
        mView.onFail(error);
    }
}
