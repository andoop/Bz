package com.andoop.ctrlf5.bangzhu.presenter;

import com.andoop.ctrlf5.bangzhu.modle.FaxianDataBean;
import com.andoop.ctrlf5.bangzhu.view.BzExchangePager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domob on 2016/12/7.
 */

public class FaXianViewPresenter {
    private BzExchangePager bzExchangePager;

    public FaXianViewPresenter(BzExchangePager bzExchangePager){
        this.bzExchangePager = bzExchangePager;
    }

    public void loadData() {

        List<FaxianDataBean> datas=new ArrayList<>();
        FaxianDataBean faxianDataBean = new FaxianDataBean();
        faxianDataBean.belong="热门";
        faxianDataBean.cat=1;
        faxianDataBean.name="小米1";
        faxianDataBean.sex=1;
        faxianDataBean.headerimg="http://v1.qzone.cc/avatar/201406/30/18/36/53b13da1589af701.jpg%21200x200.jpg";
        faxianDataBean.zhiwei="产品经理";
        faxianDataBean.tags=new String[]{"java","php"};
        datas.add(faxianDataBean);

        FaxianDataBean faxianDataBean2 = new FaxianDataBean();
        faxianDataBean2.belong="热门";
        faxianDataBean2.cat=1;
        faxianDataBean2.name="小米2";
        faxianDataBean2.sex=2;
        faxianDataBean2.headerimg="http://i.zeze.com/attachment/forum/201608/21/083953yktqrpalrfl4r4a0.jpg";
        faxianDataBean2.zhiwei="销售";
        faxianDataBean2.tags=new String[]{"商务","销售"};
        datas.add(faxianDataBean2);

        FaxianDataBean faxianDataBean3 = new FaxianDataBean();
        faxianDataBean3.belong="推荐";
        faxianDataBean3.cat=2;
        faxianDataBean3.name="小米3";
        faxianDataBean3.sex=1;
        faxianDataBean3.headerimg="http://www.poluoluo.com/qq/UploadFiles_7828/201611/2016110701032147.jpg";
        faxianDataBean3.zhiwei="工程师";
        faxianDataBean3.tags=new String[]{"ps","php"};
        datas.add(faxianDataBean3);

        FaxianDataBean faxianDataBean4 = new FaxianDataBean();
        faxianDataBean4.belong="推荐";
        faxianDataBean4.cat=2;
        faxianDataBean4.name="小米4";
        faxianDataBean4.sex=2;
        faxianDataBean4.headerimg="http://v1.qzone.cc/avatar/201502/10/11/43/54d97e7f5603c478.jpg%21200x200.jpg";
        faxianDataBean4.zhiwei="工程师";
        faxianDataBean4.tags=new String[]{"go","php"};
        datas.add(faxianDataBean4);

        FaxianDataBean faxianDataBean5 = new FaxianDataBean();
        faxianDataBean5.belong="推荐";
        faxianDataBean5.cat=2;
        faxianDataBean5.name="小米5";
        faxianDataBean5.sex=1;
        faxianDataBean5.headerimg="http://v1.qzone.cc/avatar/201308/25/11/42/52197d174e4a7384.png%21200x200.jpg";
        faxianDataBean5.zhiwei="产品经理";
        faxianDataBean5.tags=new String[]{"java","php"};
        datas.add(faxianDataBean5);

        FaxianDataBean faxianDataBean6 = new FaxianDataBean();
        faxianDataBean6.belong="推荐";
        faxianDataBean6.cat=2;
        faxianDataBean6.name="小米1";
        faxianDataBean6.sex=2;
        faxianDataBean6.headerimg="http://v1.qzone.cc/avatar/201408/17/14/22/53f04a277d3dd110.jpg%21200x200.jpg";
        faxianDataBean6.zhiwei="产品经理";
        faxianDataBean6.tags=new String[]{"java","php"};
        datas.add(faxianDataBean6);

        FaxianDataBean faxianDataBean7 = new FaxianDataBean();
        faxianDataBean7.belong="推荐";
        faxianDataBean7.cat=2;
        faxianDataBean7.name="小米7";
        faxianDataBean7.sex=1;
        faxianDataBean7.headerimg="http://v1.qzone.cc/avatar/201408/15/16/36/53edc6a090b24004.jpg%21200x200.jpg";
        faxianDataBean7.zhiwei="产品经理";
        faxianDataBean7.tags=new String[]{"java","php"};
        datas.add(faxianDataBean7);
        List<FaxianDataBean> datas_tj=new ArrayList<>();
        List<FaxianDataBean> datas_rm=new ArrayList<>();
        for(FaxianDataBean dataBean:datas){
            if(dataBean.cat==1){
                datas_rm.add(dataBean);
            }else {
                datas_tj.add(dataBean);
            }
        }
        bzExchangePager.showData(datas_rm,datas_tj);

    }
}
