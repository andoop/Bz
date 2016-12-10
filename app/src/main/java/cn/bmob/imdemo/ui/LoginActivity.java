package cn.bmob.imdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.*;
import com.andoop.ctrlf5.bangzhu.MainActivity;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.data.sp.SPColumns;
import com.andoop.ctrlf5.bangzhu.data.sp.SPUtils;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.imdemo.base.BaseActivity;
import cn.bmob.imdemo.bean.User;
import cn.bmob.imdemo.event.FinishEvent;
import cn.bmob.imdemo.model.BaseModel;
import cn.bmob.imdemo.model.UserModel;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**登陆界面
 * @author :smile
 * @project:LoginActivity
 * @date :2016-01-15-18:23
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.tv_register)
    TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick(View view){

        if(TextUtils.isEmpty(et_username.getText().toString().trim())){
            Toast.makeText(this, "工号不能为空", Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(et_password.getText().toString().trim())){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        DefaultPostRequest defaultPostRequest = new DefaultPostRequest().url(Api.login)
                .addParam("username",et_username.getText().toString().trim())
                .addParam("password",et_password.getText().toString().trim())
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        BzUser bzUser = new Gson().fromJson(datastr, BzUser.class);
                        if(bzUser.getStatus()==100){
                            //保存json
                            SPUtils spUtils = new SPUtils(LoginActivity.this, SPColumns.SP_NAME);
                            spUtils.putString(SPColumns.USER_JSON,datastr);
                            BzUser.setCurrentUser(bzUser);
                            BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(bzUser.getUserinfo().getUid()+"",bzUser.getUserinfo().getUsername(),bzUser.getUserinfo().getAvatar()));
                            if(TextUtils.isEmpty(spUtils.getString(SPColumns.SKILLS))){
                                startActivity(new Intent(LoginActivity.this, com.andoop.ctrlf5.bangzhu.view.SkillChooseActivity.class));
                                LoginActivity.this.finish();
                            }else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                LoginActivity.this.finish();
                            }

                        }else {
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        Toast.makeText(LoginActivity.this,"网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);


      /*  UserModel.getInstance().register(et_username.getText().toString(), et_password.getText().toString(),et_password.getText().toString(),new LogInListener() {
            @Override
            public void done(Object o, BmobException e) {
                if(e==null){

                    UserModel.getInstance().login(et_username.getText().toString(), et_password.getText().toString(), new LogInListener() {

                        @Override
                        public void done(Object o, BmobException e) {
                            Log.e("----->" + "LoginActivity", "done:" + e);
                           *//* if (e == null) {
                                User user =(User)o;
                                BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
                                Log.e("----->" + "LoginActivity", "done:" + ""+user.toString());
                                startActivity(new Intent(LoginActivity.this, com.andoop.ctrlf5.bangzhu.view.SkillChooseActivity.class));
                                LoginActivity.this.finish();
                            } else {
                                toast(e.getMessage() + "(" + e.getErrorCode() + ")");
                            }*//*
                        }
                    });
                }
            }
        });*/


    }

    @OnClick(R.id.tv_register)
    public void onRegisterClick(View view){
        startActivity(RegisterActivity.class, null, false);
    }

    @Subscribe
    public void onEventMainThread(FinishEvent event){
        finish();
    }
}
