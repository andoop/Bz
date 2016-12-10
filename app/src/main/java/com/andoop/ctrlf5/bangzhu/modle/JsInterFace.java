package com.andoop.ctrlf5.bangzhu.modle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.view.BzIndexPager;
import com.andoop.ctrlf5.bangzhu.view.QiangDanActivity;

/**
 * Created by domob on 2016/12/9.
 */

public class JsInterFace {

    private Context context;

    public JsInterFace(Context context){
        this.context = context;
    }
    @JavascriptInterface
    public void toPersonMsg(String uid){
        Toast.makeText(context, "进入个人信息页" + uid, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void toRquireDetail(String reid){
        Intent intent = new Intent(context, QiangDanActivity.class);
        Bundle extra=new Bundle();
        extra.putString("qiangdanid",reid);
        intent.putExtras(extra);
        context.startActivity(intent);
    }


}
