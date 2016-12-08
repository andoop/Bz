package com.andoop.ctrlf5.bangzhu.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.modle.FabuBean;
import com.andoop.ctrlf5.bangzhu.presenter.FaBuJnViewPersenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabuActivity extends AppCompatActivity {

    private FaBuJnViewPersenter faBuJnViewPersenter;
    private TextView tv_jn;
    private EditText et_fabu;
    private EditText et_xs;
    String xuanshang="";
    String jn="";
    Map<Integer,View> tags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu);

        tv_jn= (TextView) findViewById(R.id.tv_fabu_requre);
        et_fabu= (EditText) findViewById(R.id.et_fabu_content);
        et_xs= (EditText) findViewById(R.id.et_fabu_shuruxuanshang);
        tags= new HashMap<>();
        faBuJnViewPersenter = new FaBuJnViewPersenter(this);
    }

    public void xuanzejineng(View view){
        view.setSelected(true);
        ((TextView)view).setTextColor(Color.WHITE);
        Toast.makeText(this, "选择技能", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SkillChooseActivity.class);
        Bundle extra=new Bundle();
        extra.putInt("count",1);
        extra.putInt("showtitle",1);
        intent.putExtras(extra);
        startActivityForResult(intent,100);
    }

    public void quxiao(View view){
        finish();
    }

    public void jiaohuan(View view){
        onViewClick(view,"技能交换");
    }
    public void chifan(View view){

        onViewClick(view,"请吃饭");
    }
    public void kafei(View view){
        onViewClick(view,"请咖啡");
    } public void qita(View view){
        String trim = et_xs.getText().toString().trim();
        if(TextUtils.isEmpty(trim)){
            Toast.makeText(this, "请输入悬赏内容", Toast.LENGTH_SHORT).show();
            return;
        }
        onViewClick(view,trim);
    }
    public void wu(View view){
        onViewClick(view,"无");
    }

    public void onViewClick(View view,String tag){
        tags.put(view.getId(),view);
      for(int id:tags.keySet()){
          View view1 = tags.get(id);
          view1.setSelected(false);
      }
        if (view.isSelected()){
            view.setSelected(false);
            ((TextView)view).setTextColor(Color.WHITE);
            xuanshang="";
        }else {
            view.setSelected(true);
            ((TextView)view).setTextColor(Color.parseColor("#9fa0a0"));
            xuanshang=tag;
        }

    }

    public void fasong(View view){
        FabuBean fabuBean = new FabuBean();
        fabuBean.jn=jn;
        String trim = et_fabu.getText().toString().trim();
        if(TextUtils.isEmpty(trim)){
            Toast.makeText(this, "说点什么吧", Toast.LENGTH_SHORT).show();
            return;
        }

        fabuBean.content=trim;
        fabuBean.xuanshang=xuanshang;


        faBuJnViewPersenter.fabu(fabuBean);
    }

    public void showloading(){
        Toast.makeText(this, "发布中", Toast.LENGTH_SHORT).show();
    }
    public void success(){
        Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
    }
    public void showErr(String err){
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==100){
            Bundle extras = data.getExtras();
            if(extras!=null){
                String jn = extras.getString("jn");
                tv_jn.setText(jn);
                this.jn=jn;
            }
        }
    }
}
