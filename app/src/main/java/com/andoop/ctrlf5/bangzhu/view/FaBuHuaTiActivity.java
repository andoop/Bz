package com.andoop.ctrlf5.bangzhu.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andoop.ctrlf5.bangzhu.R;

public class FaBuHuaTiActivity extends AppCompatActivity {

    private FaBuHuaTiViewPresenter faBuHuaTiViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_hua_ti);
        faBuHuaTiViewPresenter = new FaBuHuaTiViewPresenter(this);
    }
}
