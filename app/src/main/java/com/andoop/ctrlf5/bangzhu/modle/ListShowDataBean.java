package com.andoop.ctrlf5.bangzhu.modle;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/7
* explain：一条发布的信息
* * * * * * * * * * * * * * * * * * */

import java.io.Serializable;

public class ListShowDataBean implements Serializable{
    public UserDataBean user;
    public String content;
    public String requare;
    public String payway;
    public long time;
    public int status;
    public int follow;
}
