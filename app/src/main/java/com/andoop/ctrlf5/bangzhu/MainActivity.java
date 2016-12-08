package com.andoop.ctrlf5.bangzhu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.andoop.andooptabframe.AndoopFrameListener;
import com.andoop.andooptabframe.AndoopPage;
import com.andoop.andooptabframe.AndoopTabFrame;
import com.andoop.andooptabframe.core.AndoopFrame;
import com.andoop.andooptabframe.core.TabFrameConfig;
import com.andoop.ctrlf5.bangzhu.data.sp.SPColumns;
import com.andoop.ctrlf5.bangzhu.data.sp.SPUtils;
import com.andoop.ctrlf5.bangzhu.view.BzExchangePager;
import com.andoop.ctrlf5.bangzhu.view.BzIndexPager;
import com.andoop.ctrlf5.bangzhu.view.BzMessagePager;
import com.andoop.ctrlf5.bangzhu.view.BzPersionPager;
import com.andoop.ctrlf5.bangzhu.view.BzSquarePager;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import cn.bmob.imdemo.bean.User;
import cn.bmob.imdemo.event.RefreshEvent;
import cn.bmob.imdemo.util.IMMLeaks;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

import static com.andoop.ctrlf5.bangzhu.R.drawable.user;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabPage();
        initIm();
    }

    //初始化即使通讯
    private void initIm() {

        //connect server
        User user = BmobUser.getCurrentUser(this,User.class);
        BmobIM.connect(user.getObjectId(), new ConnectListener() {
            @Override
            public void done(String uid, BmobException e) {
                if (e == null) {
                    Logger.i("connect success");
                    //服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
                    EventBus.getDefault().post(new RefreshEvent());
                } else {
                    Logger.e(e.getErrorCode() + "/" + e.getMessage());
                }
            }
        });
        //监听连接状态，也可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                Toast.makeText(MainActivity.this,"" + status.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
        //解决leancanary提示InputMethodManager内存泄露的问题
        IMMLeaks.fixFocusedViewLeak(getApplication());
    }

    private void initTabPage() {
        TabFrameConfig tabconfig=new TabFrameConfig.Builder()
                .canScroll(false)
                .tabColorString("#ffffff")
                .build();
        AndoopTabFrame.getInstance().init(tabconfig);
        AndoopTabFrame.getInstance().build(this, R.id.content_main, new AndoopFrameListener() {
            @Override
            public void onReady(AndoopFrame andoopFrame) {
                andoopFrame.addPage(new BzIndexPager(),R.drawable.home_selector,"广场");
                andoopFrame.addPage(new BzExchangePager(),R.drawable.exchage_selector,"发现");
                andoopFrame.addPage(new BzSquarePager(),R.drawable.social_selector,"话题");
               // andoopFrame.addPage(new BzMessagePager(),R.drawable.message_selector,"消息");
                andoopFrame.addPage(new BzPersionPager(),R.drawable.person_selector,"我的");
                andoopFrame.commit();
            }

            @Override
            public void onSelect(AndoopPage andoopPage, int pos) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //断开连接
        BmobIM.getInstance().disConnect();
    }
}
