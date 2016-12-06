package com.andoop.ctrlf5.bangzhu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andoop.andooptabframe.AndoopPage;
import com.andoop.ctrlf5.bangzhu.R;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/6
* explain：base tab页面
* * * * * * * * * * * * * * * * * * */

public abstract class BzBasePager extends AndoopPage {
    protected TextView title;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return createView(inflater);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title= (TextView) getView().findViewById(R.id.tv_title_cm_title);
    }
    protected abstract View createView(LayoutInflater inflater);
}
