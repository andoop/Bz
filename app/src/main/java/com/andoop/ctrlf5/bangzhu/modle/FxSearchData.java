package com.andoop.ctrlf5.bangzhu.modle;

import java.util.List;

/**
 * Created by domob on 2016/12/10.
 */

public class FxSearchData  {


    /**
     * data : [{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/59ff4c90e4d7478295962b7f49b2e585.jpg","gender":1,"name":"安澜","position":"客户端工程师","skills":["Flash","PPT","Excel"],"uid":4}]
     * status : 100
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * avatar : http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/59ff4c90e4d7478295962b7f49b2e585.jpg
         * gender : 1
         * name : 安澜
         * position : 客户端工程师
         * skills : ["Flash","PPT","Excel"]
         * uid : 4
         */

        private String avatar;
        private int gender;
        private String name;
        private String position;
        private int uid;
        private List<String> skills;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public List<String> getSkills() {
            return skills;
        }

        public void setSkills(List<String> skills) {
            this.skills = skills;
        }
    }
}
