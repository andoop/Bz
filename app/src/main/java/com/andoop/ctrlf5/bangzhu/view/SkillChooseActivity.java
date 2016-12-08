package com.andoop.ctrlf5.bangzhu.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.MainActivity;
import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.customview.MTextView;
import com.andoop.ctrlf5.bangzhu.modle.ChooseDataBean;
import com.andoop.ctrlf5.bangzhu.presenter.SkillChooseViewPresenter;

import java.util.ArrayList;
import java.util.List;

public class SkillChooseActivity extends AppCompatActivity {
    LinearLayout ll_content;
    private SkillChooseViewPresenter presenter;
    private List<String> chooseskills;
    private EditText editText;
    //默认只能选择三个技能
    private int chooseCount=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_choose);
        ll_content= (LinearLayout) findViewById(R.id.skill_ll_content);
        editText= (EditText) findViewById(R.id.skill_et);
        presenter = new SkillChooseViewPresenter(this);
        ll_content.requestFocus();

        initData();
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**隐藏软键盘-一般是EditText.getWindowToken()
     * @param token
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void initData() {
        chooseskills=new ArrayList<>();
        presenter.loadChooseData();

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            int showtitle = extras.getInt("showtitle");
            chooseCount=extras.getInt("count")<=0?chooseCount:extras.getInt("count");

            if(showtitle==1){
                findViewById(R.id.skill_fl_title).setVisibility(View.VISIBLE);

                findViewById(R.id.tv_skill_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

                findViewById(R.id.tv_skill_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(chooseCount==1){
                            //只是在发布技能的时候选择一个而已，不用更新
                            Intent intent = new Intent();
                            Bundle extra=new Bundle();
                            extra.putString("jn",chooseskills.get(0));
                            intent.putExtras(extra);
                            setResult(100,intent);
                            finish();
                        }else {
                            presenter.updateSkills(chooseskills);
                        }
                    }
                });

                findViewById(R.id.tv_skill_enter).setVisibility(View.GONE);
                findViewById(R.id.skil_title).setVisibility(View.GONE);
            }
        }
    }

    public void showData(List<ChooseDataBean> datas){
        for(ChooseDataBean dataBean:datas){
            final TextView textView = new TextView(this);
            textView.setText(dataBean.title);
            ll_content.addView(textView);

            LinearLayout linearLayout0=null;
            for (int i = 0; i <dataBean.cats.length; i++) {
                String cat=dataBean.cats[i];
                if(i%4==0){
                    Log.e("----->" + "SkillChtivity", "showData:" + i%4);
                    linearLayout0 = new LinearLayout(this);
                    linearLayout0.setOrientation(LinearLayout.HORIZONTAL);
                    ll_content.addView(linearLayout0);
                }
                MTextView view = new MTextView(this);
                view.setTag(cat);
                view.setText(cat);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.weight=1;
                view.setPadding(10,10,10,10);
                view.setGravity(Gravity.CENTER);
                layoutParams.leftMargin=15;
                layoutParams.rightMargin=15;
                layoutParams.topMargin=15;
                layoutParams.bottomMargin=15;
                view.setBackgroundResource(R.drawable.skill_choose_selector);
                linearLayout0.addView(view,layoutParams);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(chooseskills.size()>=chooseCount){
                            Toast.makeText(SkillChooseActivity.this, "只能选择"+chooseCount+"个技能", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(v.isSelected()){
                            ((TextView) v).setTextColor(Color.BLACK);
                            v.setSelected(false);
                            chooseskills.remove(v.getTag().toString());
                        }else {
                            v.setSelected(true);
                            ((TextView) v).setTextColor(Color.WHITE);
                            chooseskills.add(v.getTag().toString());
                        }
                        Toast.makeText(SkillChooseActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void showError(String err){
        Toast.makeText(this,err, Toast.LENGTH_SHORT).show();
    }
    public void entrymain(View view){
        if(chooseskills.size()<=0){
            Toast.makeText(this, "请选择技能", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "进入首页", Toast.LENGTH_SHORT).show();
        presenter.postData(chooseskills);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void chooseother(View v){
        String trim = editText.getText().toString().trim();
        if(TextUtils.isEmpty(trim)){
            Toast.makeText(this, "请输入补充内容", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "选择其他", Toast.LENGTH_SHORT).show();
        if(v.isSelected()){
            ((TextView) v).setTextColor(Color.BLACK);
            v.setSelected(false);
            chooseskills.remove(trim);
        }else {
            v.setSelected(true);
            ((TextView) v).setTextColor(Color.WHITE);
            chooseskills.add(trim);
        }
    }
}
