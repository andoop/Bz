package com.andoop.ctrlf5.bangzhu.presenter;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.view.PersonZiLiaoActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by domob on 2016/12/8.
 */

public class PersonZiLiaoViewPresenter {
    private PersonZiLiaoActivity personZiLiaoActivity;

    public PersonZiLiaoViewPresenter(PersonZiLiaoActivity personZiLiaoActivity){
        this.personZiLiaoActivity = personZiLiaoActivity;
    }

    public void loadData(String id) {
        personZiLiaoActivity.showloading();

        Map params=new HashMap();
        params.put("detail_userid",id);
        params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
        BzDataManager.getInstance().getUserDetail(params,new DataListener<BzUser>(){
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(BzUser data) {
                personZiLiaoActivity.success(data);
            }

            @Override
            public void onError(String err) {
                personZiLiaoActivity.showError(err);
            }

            @Override
            public void onLoading(int persent) {

            }
        });
    }
}
