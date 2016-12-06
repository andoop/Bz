package com.andoop.loginlib.listener;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/23
* explain：对外注册回调
* * * * * * * * * * * * * * * * * * */
public interface OutSignCallback {
    void success();
    void fail(String error);
}
