package com.andoop.ctrlf5.bangzhu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a.a.V;
import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.modle.FaxianDataBean;
import com.andoop.ctrlf5.bangzhu.presenter.FaXianViewPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/6
* explain：技能交换页面
* * * * * * * * * * * * * * * * * * */

public class BzExchangePager extends BzBasePager {
    private TextView tv_search;
    private RecyclerView recyclerView;
    private List<FaxianDataBean> mDatas_rm;
    private List<FaxianDataBean> mDatas_tj;
    @Override
    public void onSelect(AndoopPage andoopPage, int pos) {

    }

    @Override
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_exchange,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title.setText("发现");
        tv_search= (TextView) getView().findViewById(R.id.tv_faxian_search);
        recyclerView= (RecyclerView) getView().findViewById(R.id.rv_faxian_list);
        mDatas_rm=new ArrayList<>();
        mDatas_tj=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FaxianAdapter());

        FaXianViewPresenter faXianViewPresenter = new FaXianViewPresenter(this);
        faXianViewPresenter.loadData();
    }

    public void showloading(){
        Toast.makeText(getActivity(), "加载中", Toast.LENGTH_SHORT).show();
    }
    public void showErr(String err){
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    public void showData(List<FaxianDataBean> data_rm,List<FaxianDataBean> data_tj){
        mDatas_rm.addAll(data_rm);
        mDatas_tj.addAll(data_tj);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
    private class FaxianAdapter extends RecyclerView.Adapter<FaxianViewHolder>{
        @Override
        public FaxianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FaxianViewHolder(View.inflate(getActivity(),R.layout.faxian_item_list,null));
        }

        @Override
        public void onBindViewHolder(FaxianViewHolder holder, int position) {
            FaxianDataBean dataBean=null;
          if(position<mDatas_rm.size()){
              dataBean=mDatas_rm.get(position);
              if(position!=0){
                  //隐藏title
                  holder.lltitle.setVisibility(View.GONE);
                  holder.v_title_lie.setVisibility(View.GONE);
              }else {
                  //显示title
                  holder.lltitle.setVisibility(View.VISIBLE);
                  holder.v_title_lie.setVisibility(View.VISIBLE);
              }
              if(position==mDatas_rm.size()-1){
                  holder.vv.setVisibility(View.VISIBLE);
              }else {
                  holder.vv.setVisibility(View.GONE);
              }
          }else {
              dataBean=mDatas_tj.get(position-mDatas_rm.size());
              if(position!=mDatas_rm.size()){
                  //隐藏title
                  holder.lltitle.setVisibility(View.GONE);
                  holder.v_title_lie.setVisibility(View.GONE);
              }else {
                  //显示title
                  holder.lltitle.setVisibility(View.VISIBLE);
                  holder.v_title_lie.setVisibility(View.VISIBLE);
              }

              if(position==mDatas_rm.size()+mDatas_tj.size()-1){
                  holder.vv.setVisibility(View.VISIBLE);
              }else {
                  holder.vv.setVisibility(View.GONE);
              }
          }
            holder.title.setText(dataBean.belong);
            holder.name.setText(dataBean.name);
            holder.zhiwei.setText(dataBean.zhiwei);
            ImageLoader.getInstance().displayImage(dataBean.headerimg,holder.icon);
            if(dataBean.sex==1){
                holder.sex.setImageResource(R.drawable.nan);
            }else {
                holder.sex.setImageResource(R.drawable.nv);
            }
            if(dataBean.tags.length>=3){
                holder.tag01.setText(dataBean.tags[0]);
                holder.tag02.setText(dataBean.tags[1]);
                holder.tag03.setText(dataBean.tags[2]);
            }else if(dataBean.tags.length>=2){
                holder.tag01.setText(dataBean.tags[0]);
                holder.tag02.setText(dataBean.tags[1]);
            }else if(dataBean.tags.length>=1){
                holder.tag01.setText(dataBean.tags[0]);
            }
        }

        @Override
        public int getItemCount() {
            return mDatas_rm.size()+mDatas_tj.size();
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
        }
    }
}
