package com.andoop.ctrlf5.bangzhu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.GCSearChData;
import com.andoop.ctrlf5.bangzhu.modle.RequreDetail;
import com.andoop.ctrlf5.bangzhu.view.BzSearchActivity;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by domob on 2016/12/7.
 */

public class SearchViewPresenter {

    private BzSearchActivity searchActivity;

    public SearchViewPresenter(BzSearchActivity searchActivity){

        this.searchActivity = searchActivity;
    }

    public void loadRecommandData() {
        searchActivity.showLoading();
        List<String> datas=new ArrayList<>();
        datas.add("java");
        datas.add("视频剪辑");
        datas.add("Flash");
        datas.add("Ps");

        datas.add("商务"); datas.add("销售"); datas.add("php");
        datas.add("产品");

        searchActivity.showRecommand(datas);


    }

    public void search(String s) {

        searchActivity.showLoading();

        Map<String, String> params=new HashMap<>();
        params.put("uid", BzUser.getCurentuser().getUserinfo().getUid()+"");
        params.put("skill",s);
        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.gc_search)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        GCSearChData gcSearChData = new Gson().fromJson(datastr, GCSearChData.class);
                        if(gcSearChData==null){
                            searchActivity.showError("没有匹配的内容");
                        }else {
                            searchActivity.showFindData(gcSearChData);
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        searchActivity.showError("网络异常");
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);

    }


    public void qiangdan(String s, String requreid, String pos) {
        Map<String, String> params=new HashMap<>();
        params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
        params.put("requirement_id",requreid);

        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.answer_requirement)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {

                        try {
                            JSONObject jsonObject = new JSONObject(datastr);
                            int status = jsonObject.optInt("status");
                            if(status==100){
                                //成功
                                Toast.makeText(searchActivity, "抢单成功，在 ‘我的抢单’ 中查看", Toast.LENGTH_SHORT).show();
                            }else if(status==101){
                                //单被抢
                                Toast.makeText(searchActivity, "下手晚了，单被抢了", Toast.LENGTH_SHORT).show();

                            }else if(status==112){
                                //单被抢
                                Toast.makeText(searchActivity, "你已经抢过此单了", Toast.LENGTH_SHORT).show();

                            }else {
                                //失败
                                Toast.makeText(searchActivity, "抢单失败", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(searchActivity, "抢单失败", Toast.LENGTH_SHORT).show();
                        }
                        searchActivity.update();
                    }
                    @Override
                    public void onFail(NetError netError) {
                        Toast.makeText(searchActivity, "抢单失败", Toast.LENGTH_SHORT).show();
                        searchActivity.update();

                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/
    }
}
