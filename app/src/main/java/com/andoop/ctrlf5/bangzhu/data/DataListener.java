package com.andoop.ctrlf5.bangzhu.data;

/**
 * Created by domob on 2016/12/9.
 */

public interface DataListener<T> {
    void onStart();
    void onSuccess(T data);
    void onError(String err);
    void onLoading(int persent);
}
