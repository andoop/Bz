package com.andoop.ctrlf5.bangzhu.im;

import android.util.Log;

import com.andoop.ctrlf5.bangzhu.BzApplication;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/5
* explain：自定义消息接收器继承自BmobIMMessageHandler来处理服务器发来的消息和离线消息。
* * * * * * * * * * * * * * * * * * */
public class BzImHandler extends BmobIMMessageHandler {
    public BzImHandler(BzApplication bzApplication) {

    }

    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);
        Log.e("----->" + "BzImHandler", "onMessageReceive:");
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
        Log.e("----->" + "BzImHandler", "onOfflineReceive:");
    }
}
