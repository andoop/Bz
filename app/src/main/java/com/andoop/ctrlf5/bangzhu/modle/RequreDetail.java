package com.andoop.ctrlf5.bangzhu.modle;

import java.util.List;

/**
 * Created by domob on 2016/12/9.
 */

public class RequreDetail {

    /**
     * data : {"answer_users":[{"answer_time":1481316292,"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/2bbe390a273b4e37803e80b6c276d006.jpg","gender":1,"name":"洞洞","status":0,"uid":1},{"answer_time":1481316306,"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/2bbe390a273b4e37803e80b6c276d006.jpg","gender":1,"name":"洞洞","status":0,"uid":1}],"condition":"视频剪辑","content":"哈哈^_^","pub_time":1481301489,"publish_user":{"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/88d3c635e3df43fd8374193b48a34352.jpg","name":"浩天","uid":3},"requirement_id":17,"reward":"红包","status":0,"title":"","uid":3}
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
        /**
         * answer_users : [{"answer_time":1481316292,"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/2bbe390a273b4e37803e80b6c276d006.jpg","gender":1,"name":"洞洞","status":0,"uid":1},{"answer_time":1481316306,"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/2bbe390a273b4e37803e80b6c276d006.jpg","gender":1,"name":"洞洞","status":0,"uid":1}]
         * condition : 视频剪辑
         * content : 哈哈^_^
         * pub_time : 1481301489
         * publish_user : {"avatar":"http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/88d3c635e3df43fd8374193b48a34352.jpg","name":"浩天","uid":3}
         * requirement_id : 17
         * reward : 红包
         * status : 0
         * title :
         * uid : 3
         */

        private String condition;
        private String content;
        private long pub_time;
        private PublishUserBean publish_user;
        private int requirement_id;
        private String reward;
        private int status;
        private String title;
        private int uid;
        private List<AnswerUsersBean> answer_users;

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


        public PublishUserBean getPublish_user() {
            return publish_user;
        }

        public void setPublish_user(PublishUserBean publish_user) {
            this.publish_user = publish_user;
        }

        public int getRequirement_id() {
            return requirement_id;
        }

        public void setRequirement_id(int requirement_id) {
            this.requirement_id = requirement_id;
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

        public List<AnswerUsersBean> getAnswer_users() {
            return answer_users;
        }

        public void setAnswer_users(List<AnswerUsersBean> answer_users) {
            this.answer_users = answer_users;
        }

        public static class PublishUserBean {
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

        public static class AnswerUsersBean {
            /**
             * answer_time : 1481316292
             * avatar : http://bmob-cdn-398.b0.upaiyun.com/2016/12/10/2bbe390a273b4e37803e80b6c276d006.jpg
             * gender : 1
             * name : 洞洞
             * status : 0
             * uid : 1
             */

            private long answer_time;
            private String avatar;
            private int gender;
            private String name;
            private int status;
            private int uid;

            public long getAnswer_time() {
                return answer_time;
            }

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
