package cn.bmob.imdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.*;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;

import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.imdemo.base.BaseActivity;
import cn.bmob.imdemo.bean.User;
import cn.bmob.imdemo.event.FinishEvent;
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


        DefaultPostRequest defaultPostRequest = new DefaultPostRequest().url(Api.login)
                .addParam("username",et_username.getText().toString().trim())
                .addParam("password",et_password.getText().toString().trim())
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        Toast.makeText(LoginActivity.this, datastr, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetError netError) {
                        Toast.makeText(LoginActivity.this, netError.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);


        UserModel.getInstance().login(et_username.getText().toString(), et_password.getText().toString(), new LogInListener() {

            @Override
            public void done(Object o, BmobException e) {
                if (e == null) {

                    User user =(User)o;
                    BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
                    Log.e("----->" + "LoginActivity", "done:" + ""+user.toString());
                   startActivity(new Intent(LoginActivity.this, com.andoop.ctrlf5.bangzhu.view.SkillChooseActivity.class));
                    LoginActivity.this.finish();
                } else {
                    toast(e.getMessage() + "(" + e.getErrorCode() + ")");
                }
            }
        });
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
