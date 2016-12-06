package com.andoop.loginlib.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.loginlib.BaseFragment;
import com.andoop.loginlib.PagerManager;
import com.andoop.loginlib.R;
import com.andoop.loginlib.presenter.LoginPresenter;
import com.andoop.loginlib.presenter.view.Ilogin;


/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：登录页面
* * * * * * * * * * * * * * * * * * */
public class LoginFragment extends BaseFragment<LoginPresenter> implements Ilogin {
    EditText et_name;
    EditText et_pass;
    TextView tv_forget;
    ImageView iv_back;
    TextView tv_title;
    protected View initLayout(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_login_layout, null);
        return view;
    }
    @Override
    protected void initView(View view) {
        et_name= (EditText) view.findViewById(R.id.et_name_login);
        et_pass= (EditText) view.findViewById(R.id.et_password_login);
        tv_forget= (TextView) view.findViewById(R.id.tv_forgetpass_login);
        iv_back= (ImageView) view.findViewById(R.id.iv_back_commenttitleview);
        tv_title= (TextView) view.findViewById(R.id.tv_title_commenttitleview);
        iv_back.setVisibility(View.GONE);
        tv_title.setText("登录");
        view.findViewById(R.id.bt_login_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
        view.findViewById(R.id.bt_signup_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign(v);
            }
        });
        view.findViewById(R.id.tv_forgetpass_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget(v);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(getActivity(),this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



    public void back(View view){
        PagerManager.getInstance(getActivity()).back(getActivity());
    }
    public void sign(View view){
       PagerManager.getInstance(getActivity()).toSign(getActivity());
    }
    public void forget(View view){
        PagerManager.getInstance(getActivity()).toLostFind(getActivity());
    }
    //登录
    public void login(View view){
        //检查用户名密码
        String name = et_name.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(getActivity(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(getActivity(), "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.login(name,pass);
    }

    @Override
    public void showloading() {
        mProgressDialog.setMessage("正在登陆中...");
        mProgressDialog.show();
    }

    @Override
    public void onSuccess() {
        mProgressDialog.dismiss();
        //登录成功，自动跳转到首页
        PagerManager.getInstance(getActivity()).toMain(getActivity());
    }

    @Override
    public void onFail(String error) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
