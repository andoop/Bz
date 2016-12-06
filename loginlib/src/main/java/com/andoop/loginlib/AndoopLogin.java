package com.andoop.loginlib;

import android.app.Activity;
import android.content.Intent;

import com.andoop.loginlib.outimp.IoutLogin;
import com.andoop.loginlib.outimp.IoutLostFind;
import com.andoop.loginlib.outimp.IoutSign;

/**
 * Created by domob on 2016/11/22.
 */
public class AndoopLogin {
    private static AndoopLogin ourInstance;
    private IoutLogin ioutLogin;
    private IoutSign ioutSign;
    private IoutLostFind ioutLostFind;
    public Class<? extends Activity> main;

    public static AndoopLogin getInstance(Activity activity) {
        if(ourInstance==null){
            synchronized (AndoopLogin.class){
                if(ourInstance==null)
                    ourInstance=new AndoopLogin(activity);
            }
        }
        return ourInstance;
    }

    private AndoopLogin(Activity activity) {

    }

    /**
     * 注册登录，注册，密码找回接口
     * @param ioutLogin
     * @param ioutSign
     * @param ioutLostFind
     */
    public void regist(Class<? extends Activity> clazz,IoutLogin ioutLogin, IoutSign ioutSign, IoutLostFind ioutLostFind){
        this.main=clazz;
        this.ioutLogin = ioutLogin;
        this.ioutSign = ioutSign;
        this.ioutLostFind = ioutLostFind;
    }
    public void show(Activity activity){
       // PagerManager.getInstance(activity).toMain(activity);
        activity.startActivity(new Intent(activity,SignLoginFindActivity.class));
    }
    public IoutSign ioutSign(){
        return ioutSign;
    }
    public IoutLogin ioutLogin(){
        return ioutLogin;
    }
    public IoutLostFind ioutLostFind(){
        return ioutLostFind;
    }
}
