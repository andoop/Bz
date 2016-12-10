package com.andoop.ctrlf5.bangzhu.presenter;

import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.FabuBean;
import com.andoop.ctrlf5.bangzhu.view.FabuActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by domob on 2016/12/8.
 */

public class FaBuJnViewPersenter {
    private FabuActivity fabuActivity;

    public FaBuJnViewPersenter(FabuActivity fabuActivity){

        this.fabuActivity = fabuActivity;
    }

    public void fabu(final FabuBean fabuBean) {

        fabuActivity.showloading();

        Map params=new HashMap();
        params.put("condition",fabuBean.jn);
        params.put("reward",fabuBean.xuanshang);
        params.put("content",fabuBean.content);
        params.put("uid", BzUser.getCurentuser().getUserinfo().getUid()+"");

        BzDataManager.getInstance().faBuXuQiu(params,new DataListener(){
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object data) {
                fabuActivity.success();
            }

            @Override
            public void onError(String err) {
                fabuActivity.showErr(err);
            }

            @Override
            public void onLoading(int persent) {

            }
        });




    }
}
