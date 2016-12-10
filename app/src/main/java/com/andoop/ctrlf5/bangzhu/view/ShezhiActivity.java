package com.andoop.ctrlf5.bangzhu.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.MainActivity;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.data.sp.SPColumns;
import com.andoop.ctrlf5.bangzhu.data.sp.SPUtils;

import cn.bmob.imdemo.model.UserModel;
import cn.bmob.imdemo.ui.LoginActivity;
import cn.bmob.newim.BmobIM;

public class ShezhiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        TextView title= (TextView) findViewById(R.id.tv_title);
        title.setText("设置");
        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void signout(View view){
        Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
        UserModel.getInstance().logout();
        //可断开连接
        BmobIM.getInstance().disConnect();
        startActivity(new Intent(this,LoginActivity.class));
        new SPUtils(this, SPColumns.SP_NAME).clear();
        MainActivity.finishm();
        finish();
    }
    public void about(View view){
        Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
    }
    public void feedback(View view){
        Toast.makeText(this, "反馈", Toast.LENGTH_SHORT).show();
    }
}
