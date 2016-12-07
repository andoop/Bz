package com.andoop.ctrlf5.bangzhu.presenter;

import com.andoop.ctrlf5.bangzhu.view.BzSearchActivity;

import java.util.ArrayList;
import java.util.List;

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

    }
}
