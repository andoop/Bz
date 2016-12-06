package com.andoop.webexplor.core;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/11/24
* explain：浏览器配置
* * * * * * * * * * * * * * * * * * */

import java.io.Serializable;

public class ExplorConfig implements Serializable{
    //是否显示下面的操作区
    private boolean showOperate;
    //是否显示返回按钮
    private boolean showBack;
    //是否显示刷新按钮
    private boolean showRefresh;
    //是否显示跳转系统浏览器按钮
    private boolean showWeb;
    //是否显示更多按钮
    private boolean showMore;
    private boolean openNew;

    public boolean isShowOperate() {
        return showOperate;
    }

    public boolean isShowBack() {
        return showBack;
    }

    public boolean isShowRefresh() {
        return showRefresh;
    }

    public boolean isShowWeb() {
        return showWeb;
    }

    public boolean isShowMore() {
        return showMore;
    }

    public boolean isOpenNew() {
        return openNew;
    }

    public void setShowOperate(boolean showOperate) {
        this.showOperate = showOperate;
    }

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
    }

    public void setShowRefresh(boolean showRefresh) {
        this.showRefresh = showRefresh;
    }

    public void setShowWeb(boolean showWeb) {
        this.showWeb = showWeb;
    }

    public void setShowMore(boolean showMore) {
        this.showMore = showMore;
    }

    public void setOpenNew(boolean openNew) {
        this.openNew = openNew;
    }

    public static class Builder{
        private ExplorConfig config;
        public Builder(){
            config=new ExplorConfig();
            config.setShowBack(true);
            config.setShowMore(true);
            config.setShowRefresh(true);
            config.setShowWeb(true);
            config.setShowOperate(true);
            config.setOpenNew(true);
        }
        public Builder showBack(boolean showback){
            config.setShowBack(showback);
            return this;
        }
        public Builder showMore(boolean showmore){
            config.setShowMore(showmore);
            return this;
        }
        public Builder showRefresh(boolean refresh){
            config.setShowRefresh(refresh);
            return this;
        }
        public Builder showWeb(boolean showweb){
            config.setShowWeb(showweb);
            return this;
        }
        public Builder showOperate(boolean showoprate){
            config.setShowOperate(showoprate);
            return this;
        }

        public Builder openNew(boolean opennew) {
            config.setOpenNew(opennew);
            return this;
        }
        public ExplorConfig build(){
            return config;
        }


    }
}
