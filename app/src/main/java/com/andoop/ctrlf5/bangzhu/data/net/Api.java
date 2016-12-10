package com.andoop.ctrlf5.bangzhu.data.net;

/**
 * Created by domob on 2016/12/8.
 */

public class Api {

    public static final String baseurl="http://hackthon.wuyingchuan.cc:8000/api";

    public static final String basepageurl="http://hackthon.wuyingchuan.cc:8000/ui/html";

    public static final String login=baseurl+"/login";
    public static final String get_requirement=baseurl+"/get_requirement";
    //创建需求 /create_requirement
    public static final String create_requirement=baseurl+"/create_requirement";
    //抢单 /answer_requirement
    public static final String answer_requirement=baseurl+"/answer_requirement";
    //接受抢单 /accept_answer
    public static final String accept_answer=baseurl+"/accept_answer";
    //发现首页 /discover_home
    public static final String discover_home=baseurl+"/discover_home";
    //发现搜索 /discover_search
    public static final String discover_search=baseurl+"/discover_search";
    //更改用户信息
    public static final String user=baseurl+"/user";

    public static final String cjht=baseurl+"/topic";

    public static final String carelist=baseurl+"/user/carelist";

    public static final String ground_list=baseurl+"/ground_list";

    public static final String userdetail=baseurl+"/userdetail";

    public static final String care=baseurl+"/user/care";
    public static final String cancel_care=baseurl+"/user/cancel";

    public static final String gc_search=baseurl+"/ground/search";
    public static final String fx_search=baseurl+"/discover_search";

    public static final String page_wdqd=basepageurl+"/myscrape.html";
    public static final String page_wfbdxq=basepageurl+"/publishedNeed.html";
    public static final String page_wfbdht=basepageurl+"/publishedTopic.html";
    public static final String page_topic=basepageurl+"/topic.html";
    public static final String page_topic_detail=basepageurl+"/topicDetail.html";
    public static final String page_whfht=basepageurl+"/repliedTopic.html";

}
