package com.andoop.ctrlf5.bangzhu;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.andoop.ctrlf5.bangzhu.data.sp.SPColumns;
import com.andoop.ctrlf5.bangzhu.data.sp.SPUtils;
import com.andoop.loginlib.AndoopLogin;
import com.andoop.ctrlf5.bangzhu.login.BzLogin;
import com.andoop.ctrlf5.bangzhu.login.BzLostFind;
import com.andoop.ctrlf5.bangzhu.login.BzSign;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //查看是否需要登录
                String uid = new SPUtils(SplashActivity.this, SPColumns.SP_NAME).getString(SPColumns.BZ_UID, "");
                if(TextUtils.isEmpty(uid)){
                    //登录
                    toLogin();
                }else {
                    finish();
                    //进入主页
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
            }
        },2000);
    }

    /**
     * 登录
     */
    private void toLogin() {
        finish();
        AndoopLogin.getInstance(this).regist(MainActivity.class,new BzLogin(this),new BzSign(),new BzLostFind());
        AndoopLogin.getInstance(this).show(this);
    }
}
