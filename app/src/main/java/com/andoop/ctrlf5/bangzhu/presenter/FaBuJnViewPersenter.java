package com.andoop.ctrlf5.bangzhu.presenter;

import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.modle.FabuBean;
import com.andoop.ctrlf5.bangzhu.view.FabuActivity;

/**
 * Created by domob on 2016/12/8.
 */

public class FaBuJnViewPersenter {
    private FabuActivity fabuActivity;

    public FaBuJnViewPersenter(FabuActivity fabuActivity){

        this.fabuActivity = fabuActivity;
    }

    public void fabu(FabuBean fabuBean) {
        fabuActivity.showloading();

        Toast.makeText(fabuActivity, fabuBean.xuanshang, Toast.LENGTH_SHORT).show();

        fabuActivity.success();

    }
}
