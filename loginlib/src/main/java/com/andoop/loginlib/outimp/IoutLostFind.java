package com.andoop.loginlib.outimp;

import com.andoop.loginlib.listener.OutLostFindCallback;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/23
* explain：找回密码，对外接口
* * * * * * * * * * * * * * * * * * */
public interface IoutLostFind {
    //触发找回动作
    void onFind(String name, String phone, String question, OutLostFindCallback lostFindCallback);
    //返回
    void onBack();
}
