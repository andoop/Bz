package com.andoop.ctrlf5.bangzhu.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.customview.MTextView;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.FabuXuQiuData;
import com.andoop.ctrlf5.bangzhu.modle.GCSearChData;
import com.andoop.ctrlf5.bangzhu.presenter.SearchViewPresenter;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;
import com.andoop.ctrlf5.bangzhu.utils.MdateUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class BzSearchActivity extends AppCompatActivity {

    LinearLayout ll_recommand;
    private SearchViewPresenter searchViewPresenter;
    EditText editText;
    RecyclerView recyclerView;
    private Dialog dialog;
    private List<GCSearChData.RequirementsBean> requirements;

    String searchstr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bz_search);
        ll_recommand= (LinearLayout) findViewById(R.id.ll_search_recommand);
        editText= (EditText) findViewById(R.id.et_search_content);
        recyclerView= (RecyclerView) findViewById(R.id.rv_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HomeAdapter());

        findViewById(R.id.iv_search_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.tv_search_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String trim = editText.getText().toString().trim();
                if(TextUtils.isEmpty(trim)){
                    Toast.makeText(BzSearchActivity.this, "请输入要搜索的技能", Toast.LENGTH_SHORT).show();
                    return;
                }
                search(trim);
            }
        });

        initData();
    }

    private void search(String trim) {
        searchstr=trim;
        //搜索
        searchViewPresenter.search(trim);
    }

    private void initData() {
        searchViewPresenter = new SearchViewPresenter(this);
        //加载推荐数据
        searchViewPresenter.loadRecommandData();
    }

    public void showRecommand(List<String> datas){
        dialog.dismiss();
        LinearLayout linearLayout0=null;
        for (int i = 0; i <datas.size(); i++) {
            String cat=datas.get(i);
            if(i%4==0){
                linearLayout0 = new LinearLayout(this);
                linearLayout0.setOrientation(LinearLayout.HORIZONTAL);
                ll_recommand.addView(linearLayout0);
            }
            MTextView view = new MTextView(this);
            view.setTag(cat);
            view.setText(cat);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight=1;
            view.setPadding(20,20,20,20);
            view.setGravity(Gravity.CENTER);
            layoutParams.leftMargin=15;
            layoutParams.rightMargin=15;
            layoutParams.topMargin=15;
            layoutParams.bottomMargin=15;
            view.setBackgroundResource(R.drawable.skill_choose_selector);
            linearLayout0.addView(view,layoutParams);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    search(v.getTag().toString());
                    Toast.makeText(BzSearchActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void showLoading(){
        dialog = DialogUtils.showLoadingView(this);
    }
    public void showError(String err){
        dialog.dismiss();
        Toast.makeText(this,err, Toast.LENGTH_SHORT).show();
    }

    public void showFindData(GCSearChData gcSearChData) {
        requirements = gcSearChData.getRequirements();
        dialog.dismiss();
        ll_recommand.setVisibility(View.GONE);
        recyclerView.getAdapter().notifyDataSetChanged();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog!=null)
            dialog.dismiss();
    }

    public void update() {
        search(searchstr);
    }


    private class HomeAdapter extends RecyclerView.Adapter<BzSearchActivity.HomeViewHolder>{

        @Override
        public BzSearchActivity.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            BzSearchActivity.HomeViewHolder homeViewHolder = new BzSearchActivity.HomeViewHolder(View.inflate(BzSearchActivity.this, R.layout.item_list, null));
            return homeViewHolder;
        }

        @Override
        public void onBindViewHolder(BzSearchActivity.HomeViewHolder holder, int position) {

            GCSearChData.RequirementsBean listShowDataBean = requirements.get(position);
            holder.icon.setTag(listShowDataBean.getUid());
            holder.wyqd.setTag(listShowDataBean.require_id+":"+position);
            ImageLoader.getInstance().displayImage(listShowDataBean.getUser_avatar(),holder.icon);
            holder.name.setText(listShowDataBean.getUser_name());
            holder.content.setText(listShowDataBean.getContent());
            holder.payway.setText(listShowDataBean.getReward());
            holder.requre.setText(listShowDataBean.getCondition());
            holder.ll_guangchang.setTag(listShowDataBean);

            holder.time.setText(MdateUtils.getDate1(listShowDataBean.getPub_time()*1000));
            Log.e("----->" + "HomeAdapter", "onBindViewHolder:" + listShowDataBean.getPub_time());

            holder.tv_count.setText("抢单 "+(listShowDataBean.getApply_users()==null?0:listShowDataBean.getApply_users().size()));

        }

        @Override
        public int getItemCount() {
            return requirements==null?0:requirements.size();
        }
    }

    private class HomeViewHolder extends RecyclerView.ViewHolder{
        public ImageView icon;
        public TextView name;
        public TextView content;
        public TextView requre;
        public TextView payway;
        public LinearLayout ll_guangchang;
        public TextView wyqd;
        public TextView tv_count;
        public TextView time;
        public HomeViewHolder(View itemView) {
            super(itemView);
            icon= (ImageView) itemView.findViewById(R.id.iv_item_list_icon);
            name= (TextView) itemView.findViewById(R.id.tv_item_list_name);
            content= (TextView) itemView.findViewById(R.id.tv_item_list_des);
            requre= (TextView) itemView.findViewById(R.id.tv_item_list_tag);
            time= (TextView) itemView.findViewById(R.id.tv_item_list_time);
            payway= (TextView) itemView.findViewById(R.id.tv_item_list_xuanshang);
            ll_guangchang= (LinearLayout) itemView.findViewById(R.id.ll_guangchang);
            tv_count= (TextView) itemView.findViewById(R.id.tv_guangchang_qd_count);
            wyqd= (TextView) itemView.findViewById(R.id.wyqd);

            wyqd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String toString = v.getTag().toString();
                    String[] split = toString.split(":");
                    searchViewPresenter.qiangdan(BzUser.getCurentuser().getUserinfo().getUid()+"",split[0],split[1]);
                }
            });

            ll_guangchang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if(tag!=null&&tag instanceof GCSearChData.RequirementsBean){
                        Intent intent = new Intent(BzSearchActivity.this, QiangDanActivity.class);
                        Bundle extra=new Bundle();
                        GCSearChData.RequirementsBean data= (GCSearChData.RequirementsBean) tag;
                        extra.putString("qiangdanid",data.require_id+"");
                        intent.putExtras(extra);
                        startActivity(intent);
                    }
                }
            });

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BzSearchActivity.this, PersonZiLiaoActivity.class);
                    Bundle extra=new Bundle();
                    extra.putString("id",v.getTag().toString());
                    intent.putExtras(extra);
                    startActivity(intent);
                }
            });
        }
    }
}
