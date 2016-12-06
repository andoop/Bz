package com.andoop.ctrlf5.bangzhu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/6
* explain：首页
* * * * * * * * * * * * * * * * * * */

public class BzIndexPager extends BzBasePager {
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
        title.setText("首页");
    }
}
