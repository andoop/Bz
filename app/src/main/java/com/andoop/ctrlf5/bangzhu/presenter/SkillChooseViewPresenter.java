package com.andoop.ctrlf5.bangzhu.presenter;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/7
* explain：技能选择页面persenter
* * * * * * * * * * * * * * * * * * */

import android.util.Log;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.data.BzDataManager;
import com.andoop.ctrlf5.bangzhu.data.DataListener;
import com.andoop.ctrlf5.bangzhu.modle.BzUser;
import com.andoop.ctrlf5.bangzhu.modle.ChooseDataBean;
import com.andoop.ctrlf5.bangzhu.view.SkillChooseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillChooseViewPresenter {

    private SkillChooseActivity chooseActivity;

    public SkillChooseViewPresenter(SkillChooseActivity chooseActivity){

        this.chooseActivity = chooseActivity;
    }

    public void loadChooseData() {

        List<ChooseDataBean> dataBeanList=new ArrayList<>();
        ChooseDataBean chooseDataBean = new ChooseDataBean();
        chooseDataBean.title="编程语言";
        chooseDataBean.cats=new String[]{"python","java","php","c","go","c++","c#","ruby","python","groove","gulp"};
        dataBeanList.add(chooseDataBean);

        ChooseDataBean chooseDataBean2 = new ChooseDataBean();
        chooseDataBean2.title="创意设计";
        chooseDataBean2.cats=new String[]{"视觉设计","PS","Axure","产品设计"};
        dataBeanList.add(chooseDataBean2);

        ChooseDataBean chooseDataBean3 = new ChooseDataBean();
        chooseDataBean3.title="工具";
        chooseDataBean3.cats=new String[]{"视频剪辑","音乐剪辑","Flash","PPT","Excel","wps"};
        dataBeanList.add(chooseDataBean3);

        ChooseDataBean chooseDataBean4 = new ChooseDataBean();
        chooseDataBean4.title="商务";
        chooseDataBean4.cats=new String[]{"谈判技巧","商务知识","公关知识"};
        dataBeanList.add(chooseDataBean4);

        chooseActivity.showData(dataBeanList);
    }

    public void postData(final List<String> chooseskills) {
        String aaa="";
        for (int i = 0; i < chooseskills.size(); i++) {
            if(i==chooseskills.size()-1){
                aaa=aaa+chooseskills.get(i)+"";
            }else {
                aaa=aaa+chooseskills.get(i)+"|";
            }
        }
        Map params=new HashMap();
        params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
        params.put("skills",aaa);
        BzDataManager.getInstance().changeUserInfo(chooseActivity,params, new DataListener<BzUser>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(BzUser data) {
                chooseActivity.success();
            }

            @Override
            public void onError(String err) {
                chooseActivity.showError(err);
            }

            @Override
            public void onLoading(int persent) {

            }
        });

        for(String ss:chooseskills){
            aaa=aaa+" : "+ss;
        }
        Log.e("----->" + "SkillChooseVie", "postData:" + aaa);
    }

    public void postData1(final List<String> chooseskills) {

        chooseActivity.showLoading();

        String aaa="";
        for (int i = 0; i < chooseskills.size(); i++) {
            if(i==chooseskills.size()-1){
                aaa=aaa+chooseskills.get(i)+"";
            }else {
                aaa=aaa+chooseskills.get(i)+"|";
            }
        }
        Map params=new HashMap();
        params.put("uid",BzUser.getCurentuser().getUserinfo().getUid()+"");
        params.put("skills",aaa);
        BzDataManager.getInstance().changeUserInfo(chooseActivity,params, new DataListener<BzUser>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(BzUser data) {
                //chooseActivity.success();
                chooseActivity.finish();
            }

            @Override
            public void onError(String err) {
                chooseActivity.showError(err);
            }

            @Override
            public void onLoading(int persent) {

            }
        });

        for(String ss:chooseskills){
            aaa=aaa+" : "+ss;
        }
        Log.e("----->" + "SkillChooseVie", "postData:" + aaa);
    }

    public void updateSkills(List<String> chooseskills) {
       // Toast.makeText(chooseActivity, "更改技能", Toast.LENGTH_SHORT).show();
    }
}
