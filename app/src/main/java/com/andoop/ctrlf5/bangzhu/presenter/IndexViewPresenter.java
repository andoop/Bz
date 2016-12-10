package com.andoop.ctrlf5.bangzhu.presenter;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/7
* explain：首页view的presenter
* * * * * * * * * * * * * * * * * * */

import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.CareList;
import com.andoop.ctrlf5.bangzhu.modle.FabuXuQiuData;
import com.andoop.ctrlf5.bangzhu.modle.ListShowDataBean;
import com.andoop.ctrlf5.bangzhu.modle.UserDataBean;
import com.andoop.ctrlf5.bangzhu.view.BzIndexPager;
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

public class IndexViewPresenter {
    private BzIndexPager bzIndexPager;

    public IndexViewPresenter(BzIndexPager bzIndexPager){

        this.bzIndexPager = bzIndexPager;
    }

    public void loadData(int page) {
        bzIndexPager.showLoading();


        Map params=new HashMap();
        params.put("page",page+"");
        params.put("page_count",20+"");
        params.put("uid", BzUser.getCurentuser().getUserinfo().getUid()+"");
        BzDataManager.getInstance().getGuangChangListData(bzIndexPager.getActivity(), params, new DataListener<List<FabuXuQiuData>>() {
            @Override
            public void onStart() {
                bzIndexPager.showLoading();
            }

            @Override
            public void onSuccess(List<FabuXuQiuData> data) {

                List<FabuXuQiuData> temp=new ArrayList<FabuXuQiuData>();

               for(FabuXuQiuData fxqd:data){
                   if(fxqd.getStatus()!=0){
                       temp.add(fxqd);
                   }
               }
                data.removeAll(temp);

                bzIndexPager.showData(data,0);
            }

            @Override
            public void onError(String err) {
                bzIndexPager.showError(err);
            }

            @Override
            public void onLoading(int persent) {

            }
        });
/*
        List<ListShowDataBean> datas=new ArrayList<>();
        datas.add(newMockData("php","想要学习php","请一顿饭",new UserDataBean("http://v1.qzone.cc/avatar/201407/21/14/49/53ccb7e5d8ebb651.jpg%21200x200.jpg","小苹果","1231")));
        datas.add(newMockData("视屏剪辑","我会java，可以教会你java","技能交换",new UserDataBean("http://v1.qzone.cc/avatar/201401/17/14/10/52d8c966e1d74080.jpg%21200x200.jpg","清月","1232")));
        datas.add(newMockData("商务","想了解一下商务有关的东东","食堂随便吃",new UserDataBean("http://www.qqxoo.com/uploads/allimg/161105/22335BQ7-14.jpg","小明","1233")));
        datas.add(newMockData("Ps","Ps大神帮帮忙","食堂随便吃",new UserDataBean("http://www.poluoluo.com/qq/UploadFiles_7828/201611/2016110420035637.jpg","蜗牛","1234")));
        bzIndexPager.showData(datas,0);*/
    }

    private ListShowDataBean newMockData(String requare,String content,String payway,UserDataBean userDataBean){
        ListShowDataBean listShowDataBean = new ListShowDataBean();
        listShowDataBean.user=userDataBean;
        listShowDataBean.content=content;
        listShowDataBean.requare=requare;
        listShowDataBean.payway=payway;
        return listShowDataBean;
    }

    public void qiangdan(String uid, String requreid,String pos) {

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
                                Toast.makeText(bzIndexPager.getActivity(), "抢单成功，在 ‘我的抢单’ 中查看", Toast.LENGTH_SHORT).show();
                            }else if(status==101){
                                //单被抢
                                Toast.makeText(bzIndexPager.getActivity(), "下手晚了，单被抢了", Toast.LENGTH_SHORT).show();

                            }else if(status==112){
                                //单被抢
                                Toast.makeText(bzIndexPager.getActivity(), "你已经抢过此单了", Toast.LENGTH_SHORT).show();

                            }else {
                                //失败
                                Toast.makeText(bzIndexPager.getActivity(), "抢单失败", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(bzIndexPager.getActivity(), "抢单失败", Toast.LENGTH_SHORT).show();
                        }
                        bzIndexPager.update();
                    }
                    @Override
                    public void onFail(NetError netError) {
                        Toast.makeText(bzIndexPager.getActivity(), "抢单失败", Toast.LENGTH_SHORT).show();
                        bzIndexPager.update();

                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/

    }
}
