package com.andoop.ctrlf5.bangzhu.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.presenter.PersonZiLiaoViewPresenter;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.imdemo.ui.ChatActivity;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMUserInfo;

public class PersonZiLiaoActivity extends AppCompatActivity {

    private PersonZiLiaoViewPresenter personZiLiaoViewPresenter;
    private Dialog dialog;

    ImageView iv_zl_icon;
    TextView tv_zl_name;
    ImageView iv_zl_sex;
    TextView tv_zl_zhiwei;
    TextView tv_zl_gz;
    TextView tv_zl_jn1;
    TextView tv_zl_jn2;
    TextView tv_zl_jn3;
    TextView tv_zl_lianxi;
    private BzUser data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_zi_liao);
        iv_zl_icon= (ImageView) findViewById(R.id.iv_zl_icon);
        iv_zl_sex= (ImageView) findViewById(R.id.iv_zl_sex);
        tv_zl_name= (TextView) findViewById(R.id.tv_zl_name);
        tv_zl_zhiwei= (TextView) findViewById(R.id.tv_zl_zhiwei);
        tv_zl_gz= (TextView) findViewById(R.id.tv_zl_gz);
        tv_zl_jn1= (TextView) findViewById(R.id.tv_zl_jn1);
        tv_zl_jn2= (TextView) findViewById(R.id.tv_zl_jn2);
        tv_zl_jn3= (TextView) findViewById(R.id.tv_zl_jn3);
        tv_zl_lianxi= (TextView) findViewById(R.id.tv_zl_lianxi);



        personZiLiaoViewPresenter = new PersonZiLiaoViewPresenter(this);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String id = extras.getString("id");
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
            personZiLiaoViewPresenter.loadData(id);
        }
    }

    public void showloading(){
        dialog = DialogUtils.showLoadingView(this);
    }

    public void success(final BzUser data) {
        this.data = data;
        dialog.dismiss();

        ImageLoader.getInstance().displayImage(data.getUserinfo().getAvatar(),iv_zl_icon);
        tv_zl_name.setText(data.getUserinfo().getUsername());
        if(data.getUserinfo().getGender()==1){
            iv_zl_sex.setImageResource(R.drawable.nan);
        }else {
            iv_zl_sex.setImageResource(R.drawable.nv);
        }
        tv_zl_lianxi.setText("手机  "+data.getUserinfo().getPhone()+"\nQQ "+data.getUserinfo().getQq()+"\nemail "+data.getUserinfo().getEmail());

        final List<String> skills = data.getUserinfo().getSkills();
        if(skills==null){
            tv_zl_jn1.setVisibility(View.GONE);
            tv_zl_jn2.setVisibility(View.GONE);
            tv_zl_jn3.setVisibility(View.GONE);
        }else if(skills.size()==1){
            tv_zl_jn1.setVisibility(View.VISIBLE);
            tv_zl_jn2.setVisibility(View.GONE);
            tv_zl_jn3.setVisibility(View.GONE);

            tv_zl_jn1.setText(skills.get(0));
        }else if(skills.size()==2){
            tv_zl_jn1.setVisibility(View.VISIBLE);
            tv_zl_jn2.setVisibility(View.VISIBLE);
            tv_zl_jn3.setVisibility(View.GONE);

            tv_zl_jn1.setText(skills.get(0));
            tv_zl_jn2.setText(skills.get(1));
        }else if(skills.size()==3){
            tv_zl_jn1.setVisibility(View.VISIBLE);
            tv_zl_jn2.setVisibility(View.VISIBLE);
            tv_zl_jn3.setVisibility(View.VISIBLE);

            tv_zl_jn1.setText(skills.get(0));
            tv_zl_jn2.setText(skills.get(1));
            tv_zl_jn3.setText(skills.get(2));
        }

        Log.e("----->" + "PersonZivity", "success:" +data.getUserinfo().is_care );
        if(data.getUserinfo().is_care){
            //取消关注
            tv_zl_gz.setSelected(true);
            tv_zl_gz.setTextColor(Color.WHITE);
            tv_zl_gz.setText("已关注");


        }else {
            //关注
            tv_zl_gz.setSelected(false);
            tv_zl_gz.setTextColor(Color.parseColor("#3e3e39"));
            tv_zl_gz.setText("+关注");
        }

        if(data.getUserinfo().getUid()==BzUser.getCurentuser().getUserinfo().getUid()){
            tv_zl_gz.setVisibility(View.GONE);


        }

        tv_zl_gz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params=new HashMap<String, String>();
                params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
                params.put("care_id",data.getUserinfo().getUid()+"");
                if(data.getUserinfo().is_care){
                    //取消关注
                    DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                            .url(Api.cancel_care)
                            .addParams(params)
                            .addCallback(new NetCallback() {
                                @Override
                                public void onSuccess(String datastr) {
                                    try {
                                        int status = new JSONObject(datastr).optInt("status");
                                        if(status==100){
                                            data.getUserinfo().is_care=false;
                                            //关注
                                            tv_zl_gz.setSelected(false);
                                            tv_zl_gz.setTextColor(Color.parseColor("#3e3e39"));
                                            tv_zl_gz.setText("+关注");
                                        }else {
                                            Toast.makeText(PersonZiLiaoActivity.this, "取消失败", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(PersonZiLiaoActivity.this, "取消失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFail(NetError netError) {
                                    Toast.makeText(PersonZiLiaoActivity.this, "取消失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                    NetWorkExcutor.getInstance().excutePost(defaultPostRequest);
                }else {
                    //关注
                    DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                            .url(Api.care)
                            .addParams(params)
                            .addCallback(new NetCallback() {
                                @Override
                                public void onSuccess(String datastr) {
                                    try {
                                        int status = new JSONObject(datastr).optInt("status");
                                        if(status==100){
                                            data.getUserinfo().is_care=true;
                                            tv_zl_gz.setSelected(true);
                                            tv_zl_gz.setTextColor(Color.WHITE);
                                            tv_zl_gz.setText("已关注");
                                        }else {
                                            Toast.makeText(PersonZiLiaoActivity.this, "关注失败", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(PersonZiLiaoActivity.this, "取消失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFail(NetError netError) {
                                    Toast.makeText(PersonZiLiaoActivity.this, "关注失败", Toast.LENGTH_SHORT).show();

                                }
                            });
                    NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/
                }

            }
        });

    }

    public void faxiaoxi(View view){
        if(data==null)
            return;
        BmobIMUserInfo info = new BmobIMUserInfo(data.getUserinfo().getUid()+"",data.getUserinfo().getUsername(),data.getUserinfo().getAvatar());
        //启动一个会话，实际上就是在本地数据库的会话列表中先创建（如果没有）与该用户的会话信息，且将用户信息存储到本地的用户表中
        BmobIMConversation c = BmobIM.getInstance().startPrivateConversation(info, null);
        Bundle bundle = new Bundle();
        bundle.putSerializable("c", c);
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(getPackageName(),bundle);
        startActivity(intent);
        //startActivity(this, bundle);
    }


    public void showError(String err) {
        dialog.dismiss();
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
