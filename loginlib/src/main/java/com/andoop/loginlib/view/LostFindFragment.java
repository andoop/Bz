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
import com.andoop.loginlib.presenter.LostFindPresenter;
import com.andoop.loginlib.presenter.view.IlostFind;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/22
* explain：找回密码页面
* * * * * * * * * * * * * * * * * * */
public class LostFindFragment extends BaseFragment<LostFindPresenter> implements IlostFind{
    TextView tv_title;
    EditText et_name_lost;
    EditText et_phone_lost;
    EditText et_answer_lost;
    TextView tv_error_lost;

    @Override
    protected void initView(View view) {
        tv_title= (TextView) view.findViewById(R.id.tv_title_commenttitleview);
        tv_title.setText("密码找回");
        et_name_lost= (EditText) view.findViewById(R.id.et_name_lostfind);
        et_phone_lost= (EditText) view.findViewById(R.id.et_phone_lostfind);
        et_answer_lost= (EditText) view.findViewById(R.id.et_question_lostfind);
        tv_error_lost= (TextView) view.findViewById(R.id.tv_error_lostfind);
        view.findViewById(R.id.iv_back_commenttitleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });
        view.findViewById(R.id.bt_find_lostfind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(v);
            }
        });
    }

    @Override
    protected View initLayout(LayoutInflater inflater) {
        View inflate = inflater.inflate(R.layout.fragment_lostfind_layout, null);
        return inflate;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected LostFindPresenter initPresenter() {
        return new LostFindPresenter(getActivity(),this);
    }
    //返回
    public void back(View view){
        PagerManager.getInstance(getActivity()).back(getActivity());
    }

    //找回密码
    public void find(View view){
        String name = et_name_lost.getText().toString().trim();
        String phone = et_phone_lost.getText().toString().trim();
        String answer = et_answer_lost.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(getActivity(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(answer)){
            Toast.makeText(getActivity(),"答案不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        mPresenter.find(name,phone,answer);
    }
    @Override
    public void showloading() {
        mProgressDialog.show();
    }

    @Override
    public void onSuccess() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onFail(String error) {
        mProgressDialog.dismiss();
        Toast.makeText(getActivity(),error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPass(String pass) {
        tv_error_lost.setText("密码："+pass+"(请及时修改密码)");
    }
}
