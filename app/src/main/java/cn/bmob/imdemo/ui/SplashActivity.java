package cn.bmob.imdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.andoop.ctrlf5.bangzhu.*;
import com.andoop.ctrlf5.bangzhu.data.sp.SPColumns;
import com.andoop.ctrlf5.bangzhu.data.sp.SPUtils;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.google.gson.Gson;

import cn.bmob.imdemo.base.BaseActivity;
import cn.bmob.imdemo.bean.User;
import cn.bmob.imdemo.model.UserModel;

/**启动界面

 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler =new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SPUtils spUtils = new SPUtils(SplashActivity.this, SPColumns.SP_NAME);
                String string = spUtils.getString(SPColumns.USER_JSON);

                if(TextUtils.isEmpty(string)){
                    startActivity(LoginActivity.class,null,true);
                }else {
                    BzUser bzUser = new Gson().fromJson(string, BzUser.class);

                    if(bzUser!=null){
                        BzUser.setCurrentUser(bzUser);
                        startActivity(com.andoop.ctrlf5.bangzhu.MainActivity.class,null,true);
                    }else {
                        startActivity(LoginActivity.class,null,true);
                    }
                }
              /*  User user = UserModel.getInstance().getCurrentUser();
                if (user == null) {
                    startActivity(LoginActivity.class,null,true);
                }else{
                    startActivity(com.andoop.ctrlf5.bangzhu.MainActivity.class,null,true);
                }*/
            }
        },1000);

    }
}
