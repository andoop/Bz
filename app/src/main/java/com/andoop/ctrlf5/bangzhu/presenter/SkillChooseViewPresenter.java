package com.andoop.ctrlf5.bangzhu.presenter;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/7
* explain：技能选择页面persenter
* * * * * * * * * * * * * * * * * * */

import android.util.Log;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.modle.ChooseDataBean;
import com.andoop.ctrlf5.bangzhu.view.SkillChooseActivity;

import java.util.ArrayList;
import java.util.List;

public class SkillChooseViewPresenter {

    private SkillChooseActivity chooseActivity;

    public SkillChooseViewPresenter(SkillChooseActivity chooseActivity){

        this.chooseActivity = chooseActivity;
    }

    public void loadChooseData() {

        List<ChooseDataBean> dataBeanList=new ArrayList<>();
        ChooseDataBean chooseDataBean = new ChooseDataBean();
        chooseDataBean.title="编程语言";
        chooseDataBean.cats=new String[]{"python","java","php","python","java","php","python","java","php","python","java","php"};
        dataBeanList.add(chooseDataBean);

        ChooseDataBean chooseDataBean2 = new ChooseDataBean();
        chooseDataBean2.title="创意设计";
        chooseDataBean2.cats=new String[]{"视觉设计","PS","Axure","产品设计"};
        dataBeanList.add(chooseDataBean2);

        ChooseDataBean chooseDataBean3 = new ChooseDataBean();
        chooseDataBean3.title="工具";
        chooseDataBean3.cats=new String[]{"视频剪辑","音乐剪辑","Flash","PPT","Excel"};
        dataBeanList.add(chooseDataBean3);

        ChooseDataBean chooseDataBean4 = new ChooseDataBean();
        chooseDataBean4.title="商务";
        chooseDataBean4.cats=new String[]{"谈判技巧","商务知识"};
        dataBeanList.add(chooseDataBean4);

        chooseActivity.showData(dataBeanList);
    }

    public void postData(List<String> chooseskills) {
        String aaa="";
        for(String ss:chooseskills){
            aaa=aaa+" : "+ss;
        }
        Log.e("----->" + "SkillChooseVie", "postData:" + aaa);
    }

    public void updateSkills(List<String> chooseskills) {
        Toast.makeText(chooseActivity, "更改技能", Toast.LENGTH_SHORT).show();
    }
}
