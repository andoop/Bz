package com.andoop.loginlib.outimp;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/23
* explain：login逻辑，对外接口
* * * * * * * * * * * * * * * * * * */

import com.andoop.loginlib.listener.OutLoginCallback;

public interface IoutLogin {
    //触发登录
    void onLogin(String name, String password, OutLoginCallback loginCallback);
    //进入密码找回页面
    void onToLostFind();
    //进入注册页面
    void onToSign();
}
