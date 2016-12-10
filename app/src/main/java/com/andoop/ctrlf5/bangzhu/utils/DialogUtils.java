package com.andoop.ctrlf5.bangzhu.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.andoop.ctrlf5.bangzhu.R;
import com.andoop.ctrlf5.bangzhu.customview.ProgressWheel;

/**
 * Created by domob on 2016/12/8.
 */

public class DialogUtils {

    public static Dialog showLoadingView(Context context){
        Dialog dialog = new Dialog(context, R.style.MyDialog);
        View inflate = View.inflate(context, R.layout.loading_dialog_layout, null);
        ProgressWheel progressWheel= (ProgressWheel) inflate.findViewById(R.id.wheel);
        progressWheel.spin();
        progressWheel.setBarColor(R.color.colorPrimary);
        progressWheel.setBarWidth(10);
        dialog.setContentView(inflate);
        dialog.show();
        return dialog;
    }
}
