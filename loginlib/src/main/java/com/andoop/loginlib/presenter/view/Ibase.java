package com.andoop.loginlib.presenter.view;

/**
 * Created by domob on 2016/11/23.
 */

public interface Ibase {
    void showloading();
    void onSuccess();
    void onFail(String error);
}
