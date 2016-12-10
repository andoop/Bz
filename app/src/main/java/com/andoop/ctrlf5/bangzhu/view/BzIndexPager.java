package com.andoop.ctrlf5.bangzhu.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.FabuXuQiuData;
import com.andoop.ctrlf5.bangzhu.modle.ListShowDataBean;
import com.andoop.ctrlf5.bangzhu.presenter.IndexViewPresenter;
import com.andoop.ctrlf5.bangzhu.utils.MdateUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/6
* explain：首页
* * * * * * * * * * * * * * * * * * */

public class BzIndexPager extends BzBasePager implements SwipeRefreshLayout.OnRefreshListener {

    private IndexViewPresenter indexViewPresenter;
    private RecyclerView recyclerView;
    private List<FabuXuQiuData> dataBeanList;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static FabuXuQiuData mmmdata;
    TextView searchView;
    @Override
    public void onSelect(AndoopPage andoopPage, int pos) {

    }

    @Override
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_index,null);
    }

    @Override
    public void onResume() {
        super.onResume();
        indexViewPresenter.loadData(1);
        dataBeanList.clear();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rv_index_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchView= (TextView) getView().findViewById(R.id.tv_index_search);
        swipeRefreshLayout= (SwipeRefreshLayout) getView().findViewById(R.id.srf_index);

        swipeRefreshLayout.setOnRefreshListener(this);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),BzSearchActivity.class));
            }
        });
        title.setText("广场");
        add.setVisibility(View.VISIBLE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),FabuActivity.class));
            }
        });

        dataBeanList = new ArrayList<>();
        recyclerView.setAdapter(new HomeAdapter());

        indexViewPresenter = new IndexViewPresenter(this);

    }

    /**
     * 显示数据
     * @param data
     * @param page 表明是第几页数据
     */
    public void showData(List<FabuXuQiuData> data, int page){
        swipeRefreshLayout.setRefreshing(false);
        dataBeanList.addAll(data);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void showLoading(){
        swipeRefreshLayout.setRefreshing(true);
    }
    public void showError(String err){
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(),err+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        dataBeanList.clear();
        indexViewPresenter.loadData(1);
    }

    public void update() {
        dataBeanList.clear();
        indexViewPresenter.loadData(1);
    }

    private class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder>{

        @Override
        public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            HomeViewHolder homeViewHolder = new HomeViewHolder(View.inflate(getActivity(), R.layout.item_list, null));
            return homeViewHolder;
        }

        @Override
        public void onBindViewHolder(HomeViewHolder holder, int position) {

            FabuXuQiuData listShowDataBean = dataBeanList.get(position);
            holder.icon.setTag(listShowDataBean.getUid());
            holder.wyqd.setTag(listShowDataBean.getRequire_id()+":"+position);
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
            return dataBeanList.size();
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
                   indexViewPresenter.qiangdan(BzUser.getCurentuser().getUserinfo().getUid()+"",split[0],split[1]);
               }
           });

           ll_guangchang.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Object tag = v.getTag();
                   if(tag!=null&&tag instanceof FabuXuQiuData){
                       Intent intent = new Intent(BzIndexPager.this.getActivity(), QiangDanActivity.class);
                       Bundle extra=new Bundle();
                       FabuXuQiuData data= (FabuXuQiuData) tag;
                       extra.putString("qiangdanid",data.getRequire_id()+"");
                       intent.putExtras(extra);
                       BzIndexPager.this.getActivity().startActivity(intent);
                   }
               }
           });

           icon.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(getActivity(), PersonZiLiaoActivity.class);
                   Bundle extra=new Bundle();
                   extra.putString("id",v.getTag().toString());
                   intent.putExtras(extra);
                   startActivity(intent);
               }
           });
       }
   }
}
