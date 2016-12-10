package com.andoop.ctrlf5.bangzhu.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.presenter.PersonalViewPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    TextView msg_count;

    @Override
    public void onSelect(AndoopPage andoopPage, int pos) {
        if (msg_count==null)
            return;
        long allUnReadCount = BmobIM.getInstance().getAllUnReadCount();
        if (allUnReadCount<=0){
            msg_count.setVisibility(View.GONE);
        }else {
            msg_count.setVisibility(View.VISIBLE);
            msg_count.setText(allUnReadCount+"");
        }
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
                msg_count.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                Bundle extra=new Bundle();
                extra.putInt("chat",1);
                intent.putExtras(extra);
                getActivity().startActivity(intent);
            }
        });

        msg_count= (TextView) getView().findViewById(R.id.tv_person_item_count_msg);

        ImageView iv_personla_icon= (ImageView) getView().findViewById(R.id.iv_personla_icon);
        ImageLoader.getInstance().displayImage(BzUser.getCurentuser().getUserinfo().getAvatar(),iv_personla_icon);
        TextView tv_personal_name= (TextView) getView().findViewById(R.id.tv_personal_name);
        tv_personal_name.setText(BzUser.getCurentuser().getUserinfo().getUsername());

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

        getView().findViewById(R.id.rl_shezhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShezhiActivity.class);
                getActivity().startActivity(intent);
            }
        });


       /* getView().findViewById(R.id.bt_f_p_signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.getInstance().logout();
                //可断开连接
                BmobIM.getInstance().disConnect();
                getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
            }
        });*/

        getView().findViewById(R.id.rl_person_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),PersonMsgActivity.class));
            }
        });

        getView().findViewById(R.id.rl_wfbxq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uuu = Uri.parse(Api.page_wfbdxq).buildUpon()
                        .appendQueryParameter("uid", BzUser.getCurentuser()
                                .getUserinfo().getUid() + "").build().toString();
                //我发布的需求
                Intent intent = new Intent(getActivity(), BzWebActivity.class);
                Bundle extra=new Bundle();
                extra.putString("url",uuu);
                extra.putString("backname","我的");
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        getView().findViewById(R.id.rl_wqd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uuu = Uri.parse(Api.page_wdqd).buildUpon()
                        .appendQueryParameter("uid", BzUser.getCurentuser()
                                .getUserinfo().getUid() + "").build().toString();
                //我的抢单
                Intent intent = new Intent(getActivity(), BzWebActivity.class);
                Bundle extra=new Bundle();
                extra.putString("url",uuu);
                extra.putString("backname","我的");
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        getView().findViewById(R.id.rl_wfbht).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uuu = Uri.parse(Api.page_wfbdht).buildUpon()
                        .appendQueryParameter("uid", BzUser.getCurentuser()
                                .getUserinfo().getUid() + "").build().toString();
                //我发布的话题
                Intent intent = new Intent(getActivity(), BzWebActivity.class);
                Bundle extra=new Bundle();
                extra.putString("url",uuu);
                extra.putString("backname","我的");
                intent.putExtras(extra);
                startActivity(intent);
            }
        });
        getView().findViewById(R.id.rl_whfht).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uuu = Uri.parse(Api.page_whfht).buildUpon()
                        .appendQueryParameter("uid", BzUser.getCurentuser()
                                .getUserinfo().getUid() + "").build().toString();
                //我回复的话题
                Intent intent = new Intent(getActivity(), BzWebActivity.class);
                Bundle extra=new Bundle();
                extra.putString("url",uuu);
                extra.putString("backname","我的");
                intent.putExtras(extra);
                startActivity(intent);
            }
        });


        personalViewPresenter = new PersonalViewPresenter(this);
    }
}
