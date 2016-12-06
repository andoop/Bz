package com.andoop.loginlib.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.loginlib.BaseFragment;
import com.andoop.loginlib.PagerManager;
import com.andoop.loginlib.R;
import com.andoop.loginlib.presenter.SignPresenter;
import com.andoop.loginlib.presenter.view.Isign;


/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：注册页面
* * * * * * * * * * * * * * * * * * */
public class SignFragment extends BaseFragment<SignPresenter> implements Isign{
    TextView tv_title;
    EditText et_name_sign;
    EditText et_phone_sign;
    EditText et_pass_sign;
    EditText et_rpass_sign;
    TextView tv_error_sign;
    EditText et_answer_sign;

    @Override
    protected void initView(View view) {
        tv_title= (TextView) view.findViewById(R.id.tv_title_commenttitleview);
        et_name_sign= (EditText) view.findViewById(R.id.et_name_sign);
        et_phone_sign= (EditText) view.findViewById(R.id.et_phone_sign);
        et_pass_sign= (EditText) view.findViewById(R.id.et_pass_signup);
        et_rpass_sign= (EditText) view.findViewById(R.id.et_rpass_signup);
        tv_error_sign= (TextView) view.findViewById(R.id.tv_error_signup);
        et_answer_sign= (EditText) view.findViewById(R.id.et_answer_signup);
        tv_title.setText("账号注册");
        view.findViewById(R.id.bt_finish_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign(v);
            }
        });
        view.findViewById(R.id.iv_back_commenttitleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
    }

    @Override
    protected View initLayout(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_signup_layout, null);
        return view;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected SignPresenter initPresenter() {
        return new SignPresenter(getActivity(),this);
    }
    //返回
    public void back(View view){
        PagerManager.getInstance(getActivity()).back(getActivity());
    }
    //注册
    public void sign(View view) {
        //检查注册信息
        String name = et_name_sign.getText().toString().trim();
        String pass = et_pass_sign.getText().toString();
        String rpass = et_rpass_sign.getText().toString();
        String phone = et_phone_sign.getText().toString().trim();
        String answer = et_answer_sign.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(),"用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getActivity(),"密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pass.equals(rpass)){
            Toast.makeText(getActivity(),"两次输入密码不一致！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(answer)){
            Toast.makeText(getActivity(),"问题答案不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.sign(name,pass,phone,answer);
    }


    @Override
    public void showloading() {
        mProgressDialog.setMessage("注册中...");
        mProgressDialog.show();
    }

    @Override
    public void onSuccess() {
        mProgressDialog.dismiss();
        PagerManager.getInstance(getActivity()).back(getActivity());
    }

    @Override
    public void onFail(String error) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(),error, Toast.LENGTH_SHORT).show();

    }
}
