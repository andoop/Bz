package com.andoop.ctrlf5.bangzhu.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.customview.MTextView;
import com.andoop.ctrlf5.bangzhu.modle.DiscoverDataBean;
import com.andoop.ctrlf5.bangzhu.modle.FaxianDataBean;
import com.andoop.ctrlf5.bangzhu.modle.FxSearchData;
import com.andoop.ctrlf5.bangzhu.modle.GCSearChData;
import com.andoop.ctrlf5.bangzhu.presenter.SearchFxianViewPresenter;
import com.andoop.ctrlf5.bangzhu.presenter.SearchViewPresenter;
import com.andoop.ctrlf5.bangzhu.utils.DialogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class BzSearchFaxianActivity extends AppCompatActivity {

    LinearLayout ll_recommand;
    private SearchFxianViewPresenter searchViewPresenter;
    RecyclerView recyclerView;
    EditText editText;
    String searchstr="";
    private Dialog dialog;
    private FxSearchData fxSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bz_search);
        ll_recommand= (LinearLayout) findViewById(R.id.ll_search_recommand);
        recyclerView= (RecyclerView) findViewById(R.id.rv_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FaxianAdapter());
        editText= (EditText) findViewById(R.id.et_search_content);
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
                    Toast.makeText(BzSearchFaxianActivity.this, "请输入要搜索的技能", Toast.LENGTH_SHORT).show();
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
        searchViewPresenter = new SearchFxianViewPresenter(this);
        //加载推荐数据
        searchViewPresenter.loadRecommandData();
    }

    public void showRecommand(List<String> datas){
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

    public void showFindData(FxSearchData fxSearchData) {
        dialog.dismiss();
        this.fxSearchData = fxSearchData;
        ll_recommand.setVisibility(View.GONE);
        recyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog!=null)
            dialog.dismiss();
    }

    private class FaxianAdapter extends RecyclerView.Adapter<BzSearchFaxianActivity.FaxianViewHolder>{
        @Override
        public BzSearchFaxianActivity.FaxianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BzSearchFaxianActivity.FaxianViewHolder(View.inflate(BzSearchFaxianActivity.this,R.layout.faxian_item_list,null));
        }

        @Override
        public void onBindViewHolder(BzSearchFaxianActivity.FaxianViewHolder holder, int position) {
            FxSearchData.DataBean dataBean = fxSearchData.getData().get(position);

            //隐藏title
            holder.lltitle.setVisibility(View.GONE);
            holder.v_title_lie.setVisibility(View.GONE);

            holder.ll_faxian.setTag(dataBean.getUid());

            holder.name.setText(dataBean.getName());
            holder.zhiwei.setText(dataBean.getPosition());
            ImageLoader.getInstance().displayImage(dataBean.getAvatar(),holder.icon);
            if(dataBean.getGender()==1){
                holder.sex.setImageResource(R.drawable.nan);
            }else {
                holder.sex.setImageResource(R.drawable.nv);
            }

            holder.tag01.setVisibility(View.GONE);
            holder.tag02.setVisibility(View.GONE);
            holder.tag03.setVisibility(View.GONE);

            if(dataBean.getSkills().size()>=3){
                holder.tag01.setText(dataBean.getSkills().get(0));
                holder.tag02.setText(dataBean.getSkills().get(1));
                holder.tag03.setText(dataBean.getSkills().get(2));

                holder.tag01.setVisibility(View.VISIBLE);
                holder.tag02.setVisibility(View.VISIBLE);
                holder.tag03.setVisibility(View.VISIBLE);

            }else if(dataBean.getSkills().size()>=2){
                holder.tag01.setText(dataBean.getSkills().get(0));
                holder.tag02.setText(dataBean.getSkills().get(1));
                holder.tag01.setVisibility(View.VISIBLE);
                holder.tag02.setVisibility(View.VISIBLE);
            }else if(dataBean.getSkills().size()>=1){
                holder.tag01.setVisibility(View.VISIBLE);
                holder.tag01.setText(dataBean.getSkills().get(0));
            }
        }

        @Override
        public int getItemCount() {
            return fxSearchData==null?0:fxSearchData.getData().size();
        }
    }
    private class FaxianViewHolder extends RecyclerView.ViewHolder{
        public ImageView icon;
        public TextView name;
        public TextView title;
        public ImageView sex;
        public TextView tag01;
        public TextView tag02;
        public TextView tag03;
        public TextView zhiwei;
        public LinearLayout lltitle;
        public View v_title_lie;
        public View vv;
        public RelativeLayout ll_faxian;
        public FaxianViewHolder(View itemView) {
            super(itemView);
            icon= (ImageView) itemView.findViewById(R.id.iv_faxian_item_icon);
            name= (TextView) itemView.findViewById(R.id.tv_faxian_item_name);
            title= (TextView) itemView.findViewById(R.id.tv_faxian_item_title);
            sex= (ImageView) itemView.findViewById(R.id.iv_faxian_item_sex);
            zhiwei= (TextView) itemView.findViewById(R.id.tv_faxian_item_zhiwei);
            tag01= (TextView) itemView.findViewById(R.id.tv_faxian_item_tag01);
            tag02= (TextView) itemView.findViewById(R.id.tv_faxian_item_tag02);
            tag03= (TextView) itemView.findViewById(R.id.tv_faxian_item_tag03);
            vv=itemView.findViewById(R.id.v_faxian_item_vvv);
            v_title_lie=itemView.findViewById(R.id.faxian_01);
            lltitle= (LinearLayout) itemView.findViewById(R.id.ll_faxian_item_title);
            ll_faxian= (RelativeLayout) itemView.findViewById(R.id.ll_faxian);
            ll_faxian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
                    if(tag!=null){
                        Intent intent = new Intent(BzSearchFaxianActivity.this, PersonZiLiaoActivity.class);
                        Bundle extra=new Bundle();
                        extra.putString("id",v.getTag().toString());
                        intent.putExtras(extra);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
