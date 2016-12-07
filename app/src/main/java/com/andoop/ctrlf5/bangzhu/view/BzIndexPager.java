package com.andoop.ctrlf5.bangzhu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.modle.ListShowDataBean;
import com.andoop.ctrlf5.bangzhu.presenter.IndexViewPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/6
* explain：首页
* * * * * * * * * * * * * * * * * * */

public class BzIndexPager extends BzBasePager {

    private IndexViewPresenter indexViewPresenter;
    private RecyclerView recyclerView;
    private List<ListShowDataBean> dataBeanList;
    @Override
    public void onSelect(AndoopPage andoopPage, int pos) {

    }

    @Override
    protected View createView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_index,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rv_index_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        title.setText("广场");
        add.setVisibility(View.VISIBLE);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "添加", Toast.LENGTH_SHORT).show();
            }
        });

        dataBeanList = new ArrayList<>();
        recyclerView.setAdapter(new HomeAdapter());

        indexViewPresenter = new IndexViewPresenter(this);
        indexViewPresenter.loadData(0);
    }

    /**
     * 显示数据
     * @param data
     * @param page 表明是第几页数据
     */
    public void showData(List<ListShowDataBean> data, int page){
        dataBeanList.addAll(data);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void showLoading(){
        Toast.makeText(getActivity(), "正在加载数据", Toast.LENGTH_SHORT).show();
    }
    public void showError(String err){
        Toast.makeText(getActivity(),err, Toast.LENGTH_SHORT).show();
    }

    private class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder>{

        @Override
        public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            HomeViewHolder homeViewHolder = new HomeViewHolder(View.inflate(getActivity(), R.layout.item_list, null));
            return homeViewHolder;
        }

        @Override
        public void onBindViewHolder(HomeViewHolder holder, int position) {

            ListShowDataBean listShowDataBean = dataBeanList.get(position);
            ImageLoader.getInstance().displayImage(listShowDataBean.user.headerimg,holder.icon);
            holder.name.setText(listShowDataBean.user.name);
            holder.content.setText(listShowDataBean.content);
            holder.payway.setText(listShowDataBean.payway);
            holder.requre.setText(listShowDataBean.requare);

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
       public HomeViewHolder(View itemView) {
           super(itemView);
           icon= (ImageView) itemView.findViewById(R.id.iv_item_list_icon);
           name= (TextView) itemView.findViewById(R.id.tv_item_list_name);
           content= (TextView) itemView.findViewById(R.id.tv_item_list_des);
           requre= (TextView) itemView.findViewById(R.id.tv_item_list_tag);
           payway= (TextView) itemView.findViewById(R.id.tv_item_list_xuanshang);
       }
   }
}
