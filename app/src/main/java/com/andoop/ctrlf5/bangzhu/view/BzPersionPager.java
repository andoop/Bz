package com.andoop.ctrlf5.bangzhu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.presenter.PersonalViewPresenter;

import cn.bmob.imdemo.model.UserModel;
import cn.bmob.imdemo.ui.LoginActivity;
import cn.bmob.imdemo.ui.MainActivity;
import cn.bmob.newim.BmobIM;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/6
* explain：个人中心页面
* * * * * * * * * * * * * * * * * * */

public class BzPersionPager extends BzBasePager {

    private PersonalViewPresenter personalViewPresenter;

    @Override
    public void onSelect(AndoopPage andoopPage, int pos) {

    }

    @Override
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_person,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title.setText("我的");
        getView().findViewById(R.id.rl_person_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                Bundle extra=new Bundle();
                extra.putInt("chat",1);
                intent.putExtras(extra);
                getActivity().startActivity(intent);
            }
        });

        getView().findViewById(R.id.rl_person_gz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                Bundle extra=new Bundle();
                extra.putInt("chat",2);
                intent.putExtras(extra);
                getActivity().startActivity(intent);
            }
        });

        getView().findViewById(R.id.bt_f_p_signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.getInstance().logout();
                //可断开连接
                BmobIM.getInstance().disConnect();
                getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
            }
        });

        getView().findViewById(R.id.rl_person_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),PersonMsgActivity.class));
            }
        });

        personalViewPresenter = new PersonalViewPresenter(this);
    }
}
