package com.andoop.ctrlf5.bangzhu.modle;

import android.content.Context;

import com.andoop.ctrlf5.bangzhu.data.sp.SPColumns;
import com.andoop.ctrlf5.bangzhu.data.sp.SPUtils;

import java.util.List;

/**
 * Created by domob on 2016/12/9.
 */

public class BzUser {

    private static BzUser curentuser;
    public static void setCurrentUser(BzUser bzUser){
        curentuser=bzUser;
    }

    public static void updateUser(Context context, String userjson){
        SPUtils spUtils = new SPUtils(context, SPColumns.SP_NAME);
        spUtils.putString(SPColumns.USER_JSON,userjson);
    }

    public static BzUser getCurentuser() {
        return curentuser;
    }

    /**
     * status : 100
     * userinfo : {"avatar":"/static/defalut-avatar","company":"小蓝标","email":"huangdongdong@domob.cn","free_time":"{1:[\"13-15\", \"19-20\"],2:[],3:[\"15-18\"],4:[\"10-11\"],5:[],6:[],7:[]}","gender":1,"phone":"18510001000","position":"销售","qq":null,"skills":["blabla"],"uid":2,"username":"左烨"}
     */
    private int status;
    private UserinfoBean userinfo;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * avatar : /static/defalut-avatar
         * company : 小蓝标
         * email : huangdongdong@domob.cn
         * free_time : {1:["13-15", "19-20"],2:[],3:["15-18"],4:["10-11"],5:[],6:[],7:[]}
         * gender : 1
         * phone : 18510001000
         * position : 销售
         * qq : null
         * skills : ["blabla"]
         * uid : 2
         * username : 左烨
         */
        private String avatar;
        private String company;
        private String email="";
        private String free_time;
        public boolean is_care;
        private int gender;
        private String phone="";
        private String position;
        private Object qq="";
        private int uid;
        private String username;
        private List<String> skills;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFree_time() {
            return free_time;
        }

        public void setFree_time(String free_time) {
            this.free_time = free_time;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getSkills() {
            return skills;
        }

        public void setSkills(List<String> skills) {
            this.skills = skills;
        }
    }
}
