package com.andoop.ctrlf5.bangzhu.presenter;

import android.util.Log;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.FxSearchData;
import com.andoop.ctrlf5.bangzhu.modle.GCSearChData;
import com.andoop.ctrlf5.bangzhu.view.BzSearchActivity;
import com.andoop.ctrlf5.bangzhu.view.BzSearchFaxianActivity;
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

public class SearchFxianViewPresenter {

    private BzSearchFaxianActivity searchActivity;

    public SearchFxianViewPresenter(BzSearchFaxianActivity searchActivity) {

        this.searchActivity = searchActivity;
    }

    public void loadRecommandData() {
        List<String> datas = new ArrayList<>();
        datas.add("java");
        datas.add("视频剪辑");
        datas.add("Flash");
        datas.add("Ps");

        datas.add("商务");
        datas.add("销售");
        datas.add("php");
        datas.add("产品");
        searchActivity.showRecommand(datas);


    }

    public void search(final String s) {
        searchActivity.showLoading();

        Map<String, String> params = new HashMap<>();
        params.put("uid", BzUser.getCurentuser().getUserinfo().getUid() + "");
        params.put("skill", s);
        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.discover_search)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        Log.e("----->" + "SearwPresenter", "onSuccess:" + datastr);
                        try {
                            int status = new JSONObject(datastr).optInt("status");
                            if (status == 101) {
                                searchActivity.showError("未找到相关匹配");
                            } else if (status == 100) {
                                FxSearchData gcSearChData = new Gson().fromJson(datastr, FxSearchData.class);
                                if (gcSearChData == null) {
                                    searchActivity.showError("没有匹配的内容");
                                } else {
                                    searchActivity.showFindData(gcSearChData);
                                }
                            } else {
                                searchActivity.showError("没有匹配的内容");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            searchActivity.showError("没有匹配的内容2");
                        }

                    }

                    @Override
                    public void onFail(NetError netError) {
                        searchActivity.showError("网络异常");
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);
    }
}
