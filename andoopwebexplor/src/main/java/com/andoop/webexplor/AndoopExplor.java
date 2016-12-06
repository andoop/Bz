package com.andoop.webexplor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.andoop.webexplor.core.ExplorConfig;

/**
 * Created by domob on 2016/11/24.
 */
public class AndoopExplor {
    private static AndoopExplor ourInstance = new AndoopExplor();
    private ExplorConfig config;

    public static AndoopExplor getInstance() {
        return ourInstance;
    }

    private AndoopExplor() {
    }

    /**
     * 初始化
     */
    public void init(){
        config=new ExplorConfig.Builder().build();
        init(config);
    }

    /**
     * 初始化
     * @param config 浏览器配置
     */
    public void init( ExplorConfig config){
        this.config=config;
    }

    /**
     * 打开url，
     * @param url 要打开的链接
     */
    public void showUrl(Activity activity,String url){
        showUrl(activity,url,true,false,this.config);
    }
    /**
     * 打开url，
     * @param url 要打开的链接
     * @param opennew 是否打开新页
     * @param opensystem 是否用系统浏览器打开
     */
    public void showUrl(Activity activity,String url, boolean opennew, boolean opensystem){
        showUrl(activity,url,opennew,opensystem,this.config);
    }

    /**
     * 打开url，
     * @param url 要打开的链接
     * @param opennew 是否打开新页
     * @param opensystem 是否用系统浏览器打开
     */
    public void showUrl(Activity activity,String url, boolean opennew, boolean opensystem,ExplorConfig config){
        Intent intent=new Intent(activity,ExplorActivity.class);
        Bundle extra=new Bundle();
        extra.putSerializable("config",config);
        extra.putBoolean("opennew",opennew);
        extra.putBoolean("opensystem",opensystem);
        extra.putString("url",url);
        intent.putExtras(extra);
        activity.startActivity(intent);
    }

    /**
     * 得到配置对象
     * @return
     */
    public ExplorConfig config(){
        return this.config;
    }

}
