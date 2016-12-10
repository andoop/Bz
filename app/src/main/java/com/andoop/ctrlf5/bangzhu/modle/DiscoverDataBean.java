package com.andoop.ctrlf5.bangzhu.modle;

import java.util.List;

/**
 * Created by domob on 2016/12/10.
 */

public class DiscoverDataBean {

    /**
     * data : {"hot":[{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/a66b827de040484aa544ceb7aa2dbf41.jpg","gender":1,"name":"左烨","position":"销售","skills":["Axure,产品设计"],"uid":2},{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/59ff4c90e4d7478295962b7f49b2e585.jpg","gender":1,"name":"安澜","position":"客户端工程师","skills":["Flash","PPT","Excel"],"uid":4}],"recommend":[{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/a66b827de040484aa544ceb7aa2dbf41.jpg","gender":1,"name":"左烨","position":"销售","skills":["Axure,产品设计"],"uid":2},{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/59ff4c90e4d7478295962b7f49b2e585.jpg","gender":1,"name":"安澜","position":"客户端工程师","skills":["Flash","PPT","Excel"],"uid":4},{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/88d3c635e3df43fd8374193b48a34352.jpg","gender":1,"name":"浩天","position":"客户端工程师","skills":["Axure","Flash","产品设计"],"uid":3}]}
     * status : 100
     */

    private DataBean data;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private List<HotBean> hot;
        private List<RecommendBean> recommend;

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public static class HotBean {
            /**
             * avatar : http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/a66b827de040484aa544ceb7aa2dbf41.jpg
             * gender : 1
             * name : 左烨
             * position : 销售
             * skills : ["Axure,产品设计"]
             * uid : 2
             */

            private String avatar;
            private int gender;
            private String name;
            private String position;
            private int uid;
            private String[] skills;

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

            public String[] getSkills() {
                return skills;
            }

        }

        public static class RecommendBean {
            /**
             * avatar : http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/a66b827de040484aa544ceb7aa2dbf41.jpg
             * gender : 1
             * name : 左烨
             * position : 销售
             * skills : ["Axure,产品设计"]
             * uid : 2
             */

            private String avatar;
            private int gender;
            private String name;
            private String position;
            private int uid;
            private String[] skills;

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

            public String[] getSkills() {
                return skills;
            }

        }
    }
}
