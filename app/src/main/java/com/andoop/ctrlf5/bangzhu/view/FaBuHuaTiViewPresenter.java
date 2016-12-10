package com.andoop.ctrlf5.bangzhu.view;

import android.util.Log;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.FHTBean;
import com.andoop.ctrlf5.bangzhu.modle.RequreDetail;
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

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * Created by domob on 2016/12/8.
 */

public class FaBuHuaTiViewPresenter {

    private FaBuHuaTiActivity huaTiActivity;

    public FaBuHuaTiViewPresenter(FaBuHuaTiActivity huaTiActivity){
        this.huaTiActivity = huaTiActivity;
    }

    public void fabu(final FHTBean fhtBean) {
        final List<String> paths=new ArrayList<>();
        huaTiActivity.showloading();
        if(fhtBean.file0!=null){
            paths.add(fhtBean.file0.getAbsolutePath());
        }
        if(fhtBean.file1!=null){
            paths.add(fhtBean.file1.getAbsolutePath());
        }

        if(fhtBean.file2!=null){
            paths.add(fhtBean.file2.getAbsolutePath());
        }


        String[] filePaths=new String[paths.size()];
        for (int i = 0; i < paths.size(); i++) {
            filePaths[i]=paths.get(i);
        }

        if(paths.size()<=0){
            fffff(new ArrayList<String>(), fhtBean);
            return;
        }

        BmobFile.uploadBatch(huaTiActivity, filePaths, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> list, List<String> list1) {
                Log.e("----->" + "UpLoadUtils", "onSuccess:" + list1.get(0));
                Log.e("----->" + "UpLoadUtils", "onSuccess:" + list1.size()+":"+list.size());
                if(list1.size()==paths.size())
                    fffff(list1, fhtBean);
            }

            @Override
            public void onProgress(int i, int i1, int i2, int i3) {
                Log.e("----->" + "UpLoadUtils", "onProgress:" + i);
                //1、curIndex--表示当前第几个文件正在上传
                //2、curPercent--表示当前上传文件的进度值（百分比）
                //3、total--表示总的上传文件数
                //4、totalPercent--表示总的上传进度（百分比）


            }

            @Override
            public void onError(int i, String s) {
                Log.e("----->" + "UpLoadUtils", "onError:"+s);
                huaTiActivity.showError("发表失败");

            }
        });
    }

    private void fffff(List<String> list1, FHTBean fhtBean) {
        Map<String, String> params=new HashMap<String, String>();
        params.put("uid", BzUser.getCurentuser().getUserinfo().getUid()+"");
        params.put("content",fhtBean.content);
        String aaa="";

        for (int i = 0; i < list1.size(); i++){
            if(i==list1.size()-1){
                aaa=aaa+list1.get(i)+"";
            }else{
                aaa=aaa+list1.get(i)+"|";
            }
        }
        params.put("image",aaa);
        DefaultPostRequest defaultPostRequest = new DefaultPostRequest()
                .url(Api.cjht)
                .addParams(params)
                .addCallback(new NetCallback() {
                    @Override
                    public void onSuccess(String datastr) {
                        try {
                            int status = new JSONObject(datastr).optInt("status");
                            if(status==100){
                                huaTiActivity.success();
                            }else {
                                huaTiActivity.showError("发布失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            huaTiActivity.showError("发布失败");
                        }
                    }
                    @Override
                    public void onFail(NetError netError) {
                        huaTiActivity.showError("发布失败");
                    }
                });
        NetWorkExcutor.getInstance().excutePost(defaultPostRequest);
    }
}
