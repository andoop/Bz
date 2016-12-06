package com.andoop.loginlib.listener;


/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/23
* explain：对外登录回调
* * * * * * * * * * * * * * * * * * */
public interface OutLoginCallback {
    void success();
    void onFail(String error);
    void notRegist();
}
