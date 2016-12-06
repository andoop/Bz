package com.andoop.loginlib.outimp;

import com.andoop.loginlib.listener.OutSignCallback;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/23
* explain：注册逻辑，对外接口
* * * * * * * * * * * * * * * * * * */
public interface IoutSign {
    //触发注册
    void onSign(String name, String password, String phone, String question, OutSignCallback signCallback);
    //返回
    void onBack();
}
