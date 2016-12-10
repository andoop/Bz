package com.andoop.ctrlf5.bangzhu.data;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.CareList;
import com.andoop.ctrlf5.bangzhu.modle.FabuXuQiuData;
import com.andoop.ctrlf5.bangzhu.modle.RequreDetail;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;

/**
 * Created by domob on 2016/12/9.
 */
public class BzDataManager {
    private static BzDataManager ourInstance = new BzDataManager();

    public static BzDataManager getInstance() {
        return ourInstance;
    }

    private BzDataManager() {

    }

    public void getGuangChangListData(Context context, Map params, final DataListener<List<FabuXuQiuData>> dataListener){
        Toast.makeText(context, "请求广场列表数据", Toast.LENGTH_SHORT).show();

//        String datamock="{\n" +
//                "    \"requirements\": [\n" +
//                "        {\n" +
//                "            \"answer_user\": 1,\n" +
//                "            \"apply_users\": [\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 1\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 3\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 4\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"condition\": \"积分墙\",\n" +
//                "            \"content\": \"rt\",\n" +
//                "            \"reward\": \"一颗棒棒糖\",\n" +
//                "            \"status\": 1,\n" +
//                "            \"title\": \"请帮忙查看一下imei为xxxx的用户积分为什么没有加上?\",\n" +
//                "            \"uid\": 1,\n" +
//                "            \"user_avatar\": \"/static/defalut-avatar\",\n" +
//                "            \"user_name\": \"洞洞\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"apply_users\": [\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 1\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"左烨\",\n" +
//                "                    \"uid\": 2\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 4\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"condition\": \"java\",\n" +
//                "            \"content\": \"我想学习java有谁能教我一下...\",\n" +
//                "            \"reward\": \"请吃饭\",\n" +
//                "            \"status\": 0,\n" +
//                "            \"title\": \"帮忙教java\",\n" +
//                "            \"uid\": 2,\n" +
//                "            \"user_avatar\": \"/static/defalut-avatar\",\n" +
//                "            \"user_name\": \"左烨\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"apply_users\": [\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 1\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 3\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"子畅\",\n" +
//                "                    \"uid\": 5\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"condition\": \"dsp|PM\",\n" +
//                "            \"content\": \"快要接触多盟的dsp平台，谁比较熟悉帮忙讲解一下\",\n" +
//                "            \"reward\": \"请喝咖啡\",\n" +
//                "            \"status\": 0,\n" +
//                "            \"title\": \"求帮忙解释dsp是个啥\",\n" +
//                "            \"uid\": 3,\n" +
//                "            \"user_avatar\": \"/static/defalut-avatar\",\n" +
//                "            \"user_name\": \"洞洞\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"apply_users\": [\n" +
//                "                {\n" +
//                "                    \"name\": \"左烨\",\n" +
//                "                    \"uid\": 2\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 3\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 4\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"condition\": \"java\",\n" +
//                "            \"content\": \"我家电脑坏了，有谁能帮忙修一下吗？今晚家里没人。。。\",\n" +
//                "            \"reward\": \"请吃饭\",\n" +
//                "            \"status\": 0,\n" +
//                "            \"title\": \"我家电脑坏了，怎么破\",\n" +
//                "            \"uid\": 4,\n" +
//                "            \"user_avatar\": \"/static/defalut-avatar\",\n" +
//                "            \"user_name\": \"洞洞\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"apply_users\": [\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 3\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"洞洞\",\n" +
//                "                    \"uid\": 4\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"name\": \"子畅\",\n" +
//                "                    \"uid\": 5\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"condition\": \"python\",\n" +
//                "            \"content\": \"最近在学，多线程开发，想找人碰一下坑\",\n" +
//                "            \"reward\": \"玩游戏\",\n" +
//                "            \"status\": 0,\n" +
//                "            \"title\": \"关于python多线程开发的问题\",\n" +
//                "            \"uid\": 5,\n" +
//                "            \"user_avatar\": \"/static/defalut-avatar\",\n" +
//                "            \"user_name\": \"子畅\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";

        dataListener.onStart();
        //new Gson().fromJson()

        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url("http://192.168.168.190:8000/api/ground_list")
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {

                        try {
                            JSONObject jsonObject = new JSONObject(datastr);
                            int status = jsonObject.optInt("status");
                            if(status==100){
                                Type type = new TypeToken<List<FabuXuQiuData>>() {}.getType();
                                List<FabuXuQiuData> requirements = new Gson().fromJson(jsonObject.optString("requirements"), type);
                                dataListener.onSuccess(requirements);
                            }else {
                                dataListener.onError("请求数据失败");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataListener.onError("获取数据失败，请检查网络");
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        dataListener.onError(netError.getText());
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);
    }

    public void login(Context context, Map params, DataListener<BzUser> dataListener){
        Toast.makeText(context, "请求登录数据", Toast.LENGTH_SHORT).show();
    }

    public void getHuaTiListData(Context context,Map params,DataListener datalistener){
        Toast.makeText(context, "请求话题列表数据", Toast.LENGTH_SHORT).show();
    }


    public void getRequireDetail(Context context, Map params, final DataListener<RequreDetail> dataListener) {
        Toast.makeText(context, "请求抢单详情", Toast.LENGTH_SHORT).show();


           DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.get_requirement)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        RequreDetail requreDetail = new Gson().fromJson(datastr, RequreDetail.class);
                        if(requreDetail.getStatus()==100){
                            dataListener.onSuccess(requreDetail);
                        }else {
                            dataListener.onError("获取数据失败");
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        Log.e("----->" + "BzDataManager", "onFail:" + netError.getText());
                        dataListener.onError("网络异常");
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);
    }

    public void changeUserInfo(final Context context, Map params, final DataListener<BzUser> dataListener) {

   DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.user)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        BzUser bzUser = new Gson().fromJson(datastr, BzUser.class);
                        if(bzUser.getStatus()==100){
                            dataListener.onSuccess(bzUser);
                            BzUser.setCurrentUser(bzUser);
                            BzUser.updateUser(context,datastr);
                            BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(bzUser.getUserinfo().getUid()+"",bzUser.getUserinfo().getUsername(),bzUser.getUserinfo().getAvatar()));

                        }else {
                            dataListener.onError("更改信息失败");
                        }

                    }
                    @Override
                    public void onFail(NetError netError) {
                        dataListener.onError(netError.getText());
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/
    }

    public void getCareList(FragmentActivity activity, Map params, final DataListener<CareList> dataListener) {

        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.carelist)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        CareList careList = new Gson().fromJson(datastr, CareList.class);
                        if(careList.getStatus()==100){
                            dataListener.onSuccess(careList);
                        }else {
                            dataListener.onError("获取失败");
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        dataListener.onError(netError.getText());
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/
    }

    public void faBuXuQiu(Map params, final DataListener dataListener) {
        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.create_requirement)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        try {
                            JSONObject jsonObject = new JSONObject(datastr);
                            int status = jsonObject.optInt("status");
                            if(status==100){
                                dataListener.onSuccess("");
                            }else {
                                dataListener.onError("发表失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            dataListener.onError("发表失败");
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        dataListener.onError(netError.getText());
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/
    }

    public void getUserDetail(Map params, final DataListener<BzUser> dataListener) {
        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.userdetail)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        BzUser bzUser = new Gson().fromJson(datastr, BzUser.class);
                        if(bzUser.getStatus()==100){
                            dataListener.onSuccess(bzUser);
                        }else {
                            dataListener.onError("获取信息失败");
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        dataListener.onError(netError.getText());
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);/**/
    }
}
