package com.andoop.ctrlf5.bangzhu;

import android.app.Application;

import com.andoop.ctrlf5.bangzhu.im.BzImHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/5
* explain：Bz 自定义application
* * * * * * * * * * * * * * * * * * */

public class BzApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //只有主进程运行的时候才需要初始化
        if (getApplicationInfo().packageName.equals(getMyProcessName())) {
            //NewIM初始化
            BmobIM.init(this);
            //注册消息接收器
            BmobIM.registerDefaultMessageHandler(new BzImHandler(this));
        }
    }

    /**
     * 获取当前运行的进程名
     *
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
