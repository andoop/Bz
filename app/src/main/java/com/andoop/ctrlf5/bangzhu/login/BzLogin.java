package com.andoop.ctrlf5.bangzhu.login;

import android.content.Context;
import android.text.TextUtils;

import com.andoop.ctrlf5.bangzhu.data.sp.SPColumns;
import com.andoop.ctrlf5.bangzhu.data.sp.SPUtils;
import com.andoop.loginlib.listener.OutLoginCallback;
import com.andoop.loginlib.outimp.IoutLogin;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/5
* explain：登录回调接口
* * * * * * * * * * * * * * * * * * */

public class BzLogin implements IoutLogin {
    private final Context context;

    public BzLogin(Context context){
        this.context=context;
    }
    @Override
    public void onLogin(String name, String password, OutLoginCallback loginCallback) {
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(password)){
            loginCallback.onFail("用户名或密码不能为空！");
        }else {
            if(name.equals(password)){
                loginCallback.success();
                //保存用户标识
                new SPUtils(context, SPColumns.SP_NAME).putString(SPColumns.BZ_UID,name);
            }else {
                loginCallback.onFail("用户名不存在或密码错误！");
            }
        }
    }

    @Override
    public void onToLostFind() {

    }

    @Override
    public void onToSign() {

    }
}
