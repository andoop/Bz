package com.andoop.ctrlf5.bangzhu.presenter;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.modle.CareList;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.imdemo.ui.fragment.ContactFragment;

/**
 * Created by domob on 2016/12/9.
 */

public class ContactViewPresenter {

    private ContactFragment contactFragment;

    public ContactViewPresenter(ContactFragment contactFragment){

        this.contactFragment = contactFragment;
    }

    public void loadData(String uid) {

        Map params=new HashMap();
        params.put("uid",uid);
        contactFragment.loading();
        BzDataManager.getInstance().getCareList(contactFragment.getActivity(),params,new DataListener<CareList>(){
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(CareList data) {
                contactFragment.success(data);
            }

            @Override
            public void onError(String err) {
                contactFragment.onError(err);
            }

            @Override
            public void onLoading(int persent) {

            }
        });
    }
}
