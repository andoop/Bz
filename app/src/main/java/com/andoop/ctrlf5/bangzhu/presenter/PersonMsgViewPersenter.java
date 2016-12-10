package com.andoop.ctrlf5.bangzhu.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.data.net.Api;
import com.andoop.ctrlf5.bangzhu.data.net.UpLoadUtils;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.view.PersonMsgActivity;
import com.dvx.network.NetCallback;
import com.dvx.network.NetWorkExcutor;
import com.dvx.network.request.DefaultPostRequest;
import com.dvx.network.worker.NetError;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;

/**
 * Created by domob on 2016/12/9.
 */

public class PersonMsgViewPersenter {
    private PersonMsgActivity msgActivity;

    public PersonMsgViewPersenter(PersonMsgActivity msgActivity){

        this.msgActivity = msgActivity;
    }

    public void uploadImg(Bitmap bitmap, final File file) {
        String[] filePaths=new String[1];
        filePaths[0]=file.getAbsolutePath();
        msgActivity.showLoading();
        BmobFile.uploadBatch(msgActivity, filePaths, new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> list, List<String> list1) {
                Log.e("----->" + "UpLoadUtils", "onSuccess:" + list1.get(0));
                Map params=new HashMap();
                params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
                params.put("avatar",list1.get(0));
                BzDataManager.getInstance().changeUserInfo(msgActivity,params,new DataListener<BzUser>(){
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onSuccess(BzUser bzUser) {
                        Log.e("----->" + "PePersenter", "onSuccess:" + bzUser.getStatus());
                        if(bzUser.getStatus()==100){
                            msgActivity.success(bzUser);
                        }else {
                            msgActivity.showError("更改头像失败！");
                        }
                    }
                    @Override
                    public void onError(String err) {
                        msgActivity.showError(err);
                    }

                    @Override
                    public void onLoading(int persent) {

                    }
                });

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
                msgActivity.showError("更改头像失败！");

            }
        });
    }


}
