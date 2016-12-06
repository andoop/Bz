package com.andoop.loginlib;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by domob on 2016/11/22.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected T mPresenter;
    protected ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProgressDialog=new ProgressDialog(getActivity());
        View view = initLayout(inflater);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter=initPresenter();
        initData();
    }
    protected abstract void initView(View view);
    protected abstract View initLayout(LayoutInflater inflater);
    protected abstract void initData();
    protected abstract T initPresenter();
}
