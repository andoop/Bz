package com.andoop.ctrlf5.bangzhu.modle;

import java.util.List;

/**
 * Created by domob on 2016/12/9.
 */

public class FabuXuQiuData {


    /**
     * apply_users : [{"name":"洞洞","uid":4}]
     * condition : dsp|PM
     * content : 快要接触多盟的dsp平台，谁比较熟悉帮忙讲解一下
     * pub_time : 1481295935
     * require_id : 8
     * reward : 请喝咖啡
     * status : 0
     * title : 求帮忙解释dsp是个啥
     * uid : 3
     * user_avatar : /static/defalut-avatar
     * user_name : 洞洞
     */

    private String condition;
    private String content;
    private long pub_time;
    private int require_id;
    private String reward;
    private int status;
    private String title;
    private int uid;
    private String user_avatar;
    private String user_name;
    private List<ApplyUsersBean> apply_users;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPub_time() {
        return pub_time;
    }

    public void setPub_time(int pub_time) {
        this.pub_time = pub_time;
    }

    public int getRequire_id() {
        return require_id;
    }

    public void setRequire_id(int require_id) {
        this.require_id = require_id;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<ApplyUsersBean> getApply_users() {
        return apply_users;
    }

    public void setApply_users(List<ApplyUsersBean> apply_users) {
        this.apply_users = apply_users;
    }

    public static class ApplyUsersBean {
        /**
         * name : 洞洞
         * uid : 4
         */

        private String name;
        private int uid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
