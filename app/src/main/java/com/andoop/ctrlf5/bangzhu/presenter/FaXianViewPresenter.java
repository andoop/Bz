package com.andoop.ctrlf5.bangzhu.presenter;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.DiscoverDataBean;
import com.andoop.ctrlf5.bangzhu.modle.FaxianDataBean;
import com.andoop.ctrlf5.bangzhu.view.BzExchangePager;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;

/**
 * Created by domob on 2016/12/7.
 */

public class FaXianViewPresenter {
    private BzExchangePager bzExchangePager;

    public FaXianViewPresenter(BzExchangePager bzExchangePager) {
        this.bzExchangePager = bzExchangePager;
    }

    public void loadData() {

        bzExchangePager.showloading();
        Map<String, String> params=new HashMap<>();
        params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.discover_home)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {

                        DiscoverDataBean discoverDataBean = new Gson().fromJson(datastr, DiscoverDataBean.class);
                        if(discoverDataBean!=null&&discoverDataBean.getStatus()==100){
                            bzExchangePager.showData(discoverDataBean.getData().getHot(),discoverDataBean.getData().getRecommend());
                        }else {
                            bzExchangePager.showErr("获取信息失败");
                        }

                    }
                    @Override
                    public void onFail(NetError netError) {
                        bzExchangePager.showErr("获取信息失败");
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/



    }
}
