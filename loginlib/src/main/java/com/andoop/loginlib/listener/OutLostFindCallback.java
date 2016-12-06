package com.andoop.loginlib.listener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/23
* explain：对外找回密码回调
* * * * * * * * * * * * * * * * * * */

public interface OutLostFindCallback {
    void success(String password);
    void fail(String error);
}
