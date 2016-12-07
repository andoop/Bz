package com.andoop.ctrlf5.bangzhu.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.customview.MTextView;
import com.andoop.ctrlf5.bangzhu.presenter.SearchViewPresenter;

import java.util.List;

public class BzSearchActivity extends AppCompatActivity {

    LinearLayout ll_recommand;
    private SearchViewPresenter searchViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bz_search);
        ll_recommand= (LinearLayout) findViewById(R.id.ll_search_recommand);
        initData();
    }

    private void initData() {
        searchViewPresenter = new SearchViewPresenter(this);
        //加载推荐数据
        searchViewPresenter.loadRecommandData();
    }

    public void showRecommand(List<String> datas){
        LinearLayout linearLayout0=null;
        for (int i = 0; i <datas.size(); i++) {
            String cat=datas.get(i);
            if(i%4==0){
                linearLayout0 = new LinearLayout(this);
                linearLayout0.setOrientation(LinearLayout.HORIZONTAL);
                ll_recommand.addView(linearLayout0);
            }
            MTextView view = new MTextView(this);
            view.setTag(cat);
            view.setText(cat);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight=1;
            view.setPadding(20,20,20,20);
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
                    searchViewPresenter.search(v.getTag().toString());
                    Toast.makeText(BzSearchActivity.this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void showLoading(){
        Toast.makeText(this, "加载推荐数据", Toast.LENGTH_SHORT).show();
    }
    public void showError(String err){
        Toast.makeText(this,err, Toast.LENGTH_SHORT).show();
    }
}
