package com.andoop.loginlib;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.andoop.loginlib.view.LoginFragment;
import com.andoop.loginlib.view.LostFindFragment;
import com.andoop.loginlib.view.SignFragment;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：页面管理者
* * * * * * * * * * * * * * * * * * */
public class PagerManager {
    private static PagerManager ourInstance;

    public static PagerManager getInstance(Activity activity)
    {
        if(ourInstance==null){
            synchronized (PagerManager.class){
                if(ourInstance==null)
                    ourInstance=new PagerManager(activity);
            }
        }
        return ourInstance;
    }

    private PagerManager(Activity activity) {
    }


    public void back(FragmentActivity activity){
       activity.finish();
    }

    public void toSign(FragmentActivity activity) {
        SignFragment signFragment = new SignFragment();
        toPage(activity,signFragment);
    }

    public void toLogin(FragmentActivity activity) {
        LoginFragment loginFragment = new LoginFragment();
        toPage(activity,loginFragment);
    }

    public void toLostFind(FragmentActivity activity) {
        LostFindFragment lostFindFragment = new LostFindFragment();
        toPage(activity,lostFindFragment);
    }
    private void toPage(FragmentActivity activity,Fragment fragment) {
        FragmentTransaction fragmentTransaction =
                activity.getSupportFragmentManager().beginTransaction();
        if(activity.getSupportFragmentManager().getBackStackEntryCount()<=0){
            fragmentTransaction.setCustomAnimations(FragmentTransaction.TRANSIT_NONE, android.R.anim.slide_out_right,
                    FragmentTransaction.TRANSIT_NONE, android.R.anim.slide_out_right);
        }else {
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
                fragmentTransaction
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .add(R.id.fl_content_sign_login_find,fragment).commit();
    }

    public void toMain(FragmentActivity activity) {
        activity.startActivity(new Intent(activity,AndoopLogin.getInstance(activity).main));
        activity.finish();
    }
}
