package com.andoop.loginlib;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

public class SignLoginFindActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_login_find);

        //默认页面为登录页面
        PagerManager.getInstance(this).toLogin(this);
    }

    @Override
    public void finish() {
        getSupportFragmentManager()
                .popBackStack();
        Toast.makeText(this,getSupportFragmentManager().getBackStackEntryCount()+"", Toast.LENGTH_SHORT).show();
        if(getSupportFragmentManager().getBackStackEntryCount()<=1){
            super.finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            Toast.makeText(this, "sss", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
