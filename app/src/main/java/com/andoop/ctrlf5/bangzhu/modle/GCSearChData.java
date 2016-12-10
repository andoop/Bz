package com.andoop.ctrlf5.bangzhu.modle;

import java.util.List;

/**
 * Created by domob on 2016/12/10.
 */

public class GCSearChData {

    private List<RequirementsBean> requirements;

    public List<RequirementsBean> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementsBean> requirements) {
        this.requirements = requirements;
    }

    public static class RequirementsBean {
        /**
         * apply_users : [{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/88d3c635e3df43fd8374193b48a34352.jpg","name":"浩天","uid":3}]
         * condition : java
         * content : 我想学习java有谁能教我一下...
         * pub_time : 1481295935
         * reward : 请吃饭
         * status : 0
         * title : 帮忙教java
         * uid : 2
         * user_avatar : http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/a66b827de040484aa544ceb7aa2dbf41.jpg
         * user_name : 左烨
         */

        private String condition;
        private String content;
        private int pub_time;
        private String reward;
        private int status;
        private String title;
        private int uid;
        public String require_id;
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

        public int getPub_time() {
            return pub_time;
        }

        public void setPub_time(int pub_time) {
            this.pub_time = pub_time;
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
             * avatar : http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/88d3c635e3df43fd8374193b48a34352.jpg
             * name : 浩天
             * uid : 3
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
}
