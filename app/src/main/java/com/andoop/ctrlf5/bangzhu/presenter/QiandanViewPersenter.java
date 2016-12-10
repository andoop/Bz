package com.andoop.ctrlf5.bangzhu.presenter;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.modle.RequreDetail;
import com.andoop.ctrlf5.bangzhu.view.QiangDanActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by domob on 2016/12/9.
 */

public class QiandanViewPersenter {
    private QiangDanActivity activity;

    public QiandanViewPersenter(QiangDanActivity activity){
        this.activity = activity;
    }
    public void loadData(String qiangdanid) {

        Map params=new HashMap();
        params.put("requirement_id",qiangdanid);
        activity.showloading();
        BzDataManager.getInstance().getRequireDetail(activity, params, new DataListener<RequreDetail>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(RequreDetail data) {
                activity.showData(data);
            }

            @Override
            public void onError(String err) {
                activity.showError(err);
            }

            @Override
            public void onLoading(int persent) {

            }
        });
    }
}
