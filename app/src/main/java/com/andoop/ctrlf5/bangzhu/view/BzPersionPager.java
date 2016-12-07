package com.andoop.ctrlf5.bangzhu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;

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
        getView().findViewById(R.id.bt_f_p_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
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
    }
}
