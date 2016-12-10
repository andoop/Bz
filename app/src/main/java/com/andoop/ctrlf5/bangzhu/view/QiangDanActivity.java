package com.andoop.ctrlf5.bangzhu.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a.a.V;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.CareList;
import com.andoop.ctrlf5.bangzhu.modle.FabuXuQiuData;
import com.andoop.ctrlf5.bangzhu.modle.ListShowDataBean;
import com.andoop.ctrlf5.bangzhu.modle.RequreDetail;
import com.andoop.ctrlf5.bangzhu.presenter.QiandanViewPersenter;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;
import com.andoop.ctrlf5.bangzhu.utils.MdateUtils;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.imdemo.ui.ChatActivity;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;

public class QiangDanActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public ImageView icon;
    public TextView name;
    public TextView content;
    public TextView requre;
    public TextView payway;
    private Dialog dialog;
    public TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiang_dan);

        QiandanViewPersenter qiandanViewPersenter = new QiandanViewPersenter(this);
        Bundle extras = getIntent().getExtras();

        TextView ttt= (TextView) findViewById(R.id.tv_title);
        ttt.setText("发布详情");
        TextView tttleft= (TextView) findViewById(R.id.tv_back_text);
        tttleft.setText("");

        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        icon= (ImageView) findViewById(R.id.iv_item_list_icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                if(tag!=null){
                    Intent intent = new Intent(QiangDanActivity.this, PersonZiLiaoActivity.class);
                    Bundle extra=new Bundle();
                    extra.putString("id",v.getTag().toString());
                    intent.putExtras(extra);
                    startActivity(intent);
                }
            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.rv_qiangdan);
        icon= (ImageView) this.findViewById(R.id.iv_item_list_icon);
        name= (TextView) this.findViewById(R.id.tv_item_list_name);
        content= (TextView) this.findViewById(R.id.tv_item_list_des);
        requre= (TextView) this.findViewById(R.id.tv_item_list_tag);
        payway= (TextView) this.findViewById(R.id.tv_item_list_xuanshang);
        time= (TextView) findViewById(R.id.tv_item_list_time);

        if(extras!=null){
            qiandanViewPersenter.loadData(extras.getString("qiangdanid"));
        }
    }

    public void showloading(){
        dialog = DialogUtils.showLoadingView(this);
    }

    public void showError(String err){
        dialog.dismiss();
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    public void showData(RequreDetail data) {
        dialog.dismiss();
           if(data!=null){
            icon.setTag(data.getData().getUid());
          //  ImageLoader.getInstance().displayImage(data.get(),icon);
            //name.setText(data.getu());
            content.setText(data.getData().getContent());
               name.setText(data.getData().getPublish_user().getName());
               ImageLoader.getInstance().displayImage(data.getData().getPublish_user().getAvatar(),icon);
               time.setText(MdateUtils.getDate1(data.getData().getPub_time()*1000));

            payway.setText(data.getData().getReward());
            requre.setText(data.getData().getCondition());
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
               recyclerView.setAdapter(new QiandanUserAdapter(data));
        }

    }

    private class QiandanUserAdapter extends RecyclerView.Adapter<QiandanUserViewHoder>{
        private RequreDetail data;

        public QiandanUserAdapter(RequreDetail data) {

            this.data = data;
        }

        @Override
        public QiandanUserViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new QiandanUserViewHoder(View.inflate(QiangDanActivity.this,R.layout.qiangdan_item_list,null));
        }

        @Override
        public void onBindViewHolder(QiandanUserViewHoder holder, int position) {
            List<RequreDetail.DataBean.AnswerUsersBean> answer_users = data.getData().getAnswer_users();
            if(answer_users!=null){
                RequreDetail.DataBean.AnswerUsersBean answerUsersBean = answer_users.get(position);
                if(position>0){
                    //隐藏title
                    holder.lltitle.setVisibility(View.GONE);
                    holder.v_title_lie.setVisibility(View.GONE);
                }else {
                    //显示title
                    holder.lltitle.setVisibility(View.VISIBLE);
                    holder.v_title_lie.setVisibility(View.VISIBLE);
                }
                holder.tv_qd_jishou.setTag(answerUsersBean.getUid());
                holder.name.setTag(data.getData().getRequirement_id());
                holder.title.setTag(answerUsersBean);
                if(data.getData().getUid()!= BzUser.getCurentuser().getUserinfo().getUid()){
                    holder.tv_qd_jishou.setVisibility(View.GONE);
                }else {
                    holder.tv_qd_jishou.setVisibility(View.VISIBLE);
                }

                holder.name.setText(answerUsersBean.getName());
                holder.time.setText(MdateUtils.getDate1(answerUsersBean.getAnswer_time()*1000));
                holder.icon.setTag(answerUsersBean.getUid());

                ImageLoader.getInstance().displayImage(answerUsersBean.getAvatar(),holder.icon);

            }
        }

        @Override
        public int getItemCount() {
            return data.getData().getAnswer_users().size();
        }
    }
    private class QiandanUserViewHoder extends RecyclerView.ViewHolder{

        public ImageView icon;
        public TextView name;
        public TextView title;
        public TextView time;
        public LinearLayout lltitle;
        public View v_title_lie;
        public View vv;
        public RelativeLayout ll_faxian;
        public TextView tv_qd_jishou;
        public QiandanUserViewHoder(View itemView) {
            super(itemView);
            icon= (ImageView) itemView.findViewById(R.id.iv_faxian_item_icon);
            name= (TextView) itemView.findViewById(R.id.tv_faxian_item_name);
            title= (TextView) itemView.findViewById(R.id.tv_faxian_item_title);
            time= (TextView) itemView.findViewById(R.id.tv_faxian_item_time);
            vv=itemView.findViewById(R.id.v_faxian_item_vvv);
            v_title_lie=itemView.findViewById(R.id.faxian_01);
            tv_qd_jishou= (TextView) itemView.findViewById(R.id.tv_qd_jishou);
            lltitle= (LinearLayout) itemView.findViewById(R.id.ll_faxian_item_title);
            ll_faxian= (RelativeLayout) itemView.findViewById(R.id.ll_faxian);

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if(tag!=null){
                        Intent intent = new Intent(QiangDanActivity.this, PersonZiLiaoActivity.class);
                        Bundle extra=new Bundle();
                        extra.putString("id",v.getTag().toString());
                        intent.putExtras(extra);
                        startActivity(intent);
                    }
                }
            });

            tv_qd_jishou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ss = v.getTag().toString();
                    Map<String, String> params=new HashMap<String, String>();
                    params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
                    params.put("answer_user_id",ss);
                    params.put("requirement_id",name.getTag().toString());
                    DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                            .url(Api.carelist)
                            .addParams(params)
                            .addCallback(new NetCallback() {
                                @Override
                                public void onSuccess(String datastr) {
                                    try {
                                        int status = new JSONObject(datastr).optInt("status");
                                        if(status==100){
                                            RequreDetail.DataBean.AnswerUsersBean answerUsersBean= (RequreDetail.DataBean.AnswerUsersBean) title.getTag();
                                            BmobIMUserInfo info = new BmobIMUserInfo(answerUsersBean.getUid()+"",answerUsersBean.getName(),answerUsersBean.getAvatar());
                                            //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
                                            BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("c", c);
                                            Intent intent = new Intent(QiangDanActivity.this, ChatActivity.class);
                                            intent.putExtra(QiangDanActivity.this.getPackageName(),bundle);
                                            QiangDanActivity.this.startActivity(intent);
                                            Toast.makeText(QiangDanActivity.this, "快通知他吧！", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                @Override
                                public void onFail(NetError netError) {
                                }
                            });
                    NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/
                }
            });


        }
    }
}
