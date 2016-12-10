package com.andoop.ctrlf5.bangzhu.modle;

import java.util.List;

/**
 * Created by domob on 2016/12/9.
 */

public class CareList {


    /**
     * care_list : [{"avatar":"/static/defalut-avatar","name":"左烨","uid":2}]
     * status : 100
     */

    private int status;
    private List<CareListBean> care_list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CareListBean> getCare_list() {
        return care_list;
    }

    public void setCare_list(List<CareListBean> care_list) {
        this.care_list = care_list;
    }

    public static class CareListBean {
        /**
         * avatar : /static/defalut-avatar
         * name : 左烨
         * uid : 2
         */

        private String avatar;
        private String name;
        private int uid;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

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
