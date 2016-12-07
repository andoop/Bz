package com.andoop.ctrlf5.bangzhu.presenter;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/7
* explain：首页view的presenter
* * * * * * * * * * * * * * * * * * */

import com.andoop.ctrlf5.bangzhu.modle.ListShowDataBean;
import com.andoop.ctrlf5.bangzhu.modle.UserDataBean;
import com.andoop.ctrlf5.bangzhu.view.BzIndexPager;

import java.util.ArrayList;
import java.util.List;

public class IndexViewPresenter {
    private BzIndexPager bzIndexPager;

    public IndexViewPresenter(BzIndexPager bzIndexPager){

        this.bzIndexPager = bzIndexPager;
    }

    public void loadData(int page) {
        bzIndexPager.showLoading();

        List<ListShowDataBean> datas=new ArrayList<>();
        datas.add(newMockData("php","想要学习php","请一顿饭",new UserDataBean("http://v1.qzone.cc/avatar/201407/21/14/49/53ccb7e5d8ebb651.jpg%21200x200.jpg","小苹果","1231")));
        datas.add(newMockData("视屏剪辑","我会java，可以教会你java","技能交换",new UserDataBean("http://v1.qzone.cc/avatar/201401/17/14/10/52d8c966e1d74080.jpg%21200x200.jpg","清月","1232")));
        datas.add(newMockData("商务","想了解一下商务有关的东东","食堂随便吃",new UserDataBean("http://www.qqxoo.com/uploads/allimg/161105/22335BQ7-14.jpg","小明","1233")));
        datas.add(newMockData("Ps","Ps大神帮帮忙","食堂随便吃",new UserDataBean("http://www.poluoluo.com/qq/UploadFiles_7828/201611/2016110420035637.jpg","蜗牛","1234")));
        bzIndexPager.showData(datas,0);
    }

    private ListShowDataBean newMockData(String requare,String content,String payway,UserDataBean userDataBean){
        ListShowDataBean listShowDataBean = new ListShowDataBean();
        listShowDataBean.user=userDataBean;
        listShowDataBean.content=content;
        listShowDataBean.requare=requare;
        listShowDataBean.payway=payway;
        return listShowDataBean;
    }
}
