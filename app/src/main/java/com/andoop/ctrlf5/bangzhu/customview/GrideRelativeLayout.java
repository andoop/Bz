package com.andoop.ctrlf5.bangzhu.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/* * * * * * * * * * * * * * * * * * *
* author :andoop　　　　　　　　　　　
* time   :2016/12/7
* explain：
* * * * * * * * * * * * * * * * * * */

public class GrideRelativeLayout extends FrameLayout {
    List<View> childs;
    public GrideRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public GrideRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        childs=new ArrayList<>();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //super.onLayout(changed,l,t,r,b);
        if(!changed)
            return;
        int measuredWidth = r-l;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
           final int ml=l+(i%4)*(measuredWidth/4);
            final  int mt=t+100*(i/4);
            final  int mr=80+ml;
            final  int mb=80+mt;
            childAt.layout(ml,mt,mr,mb);
            //childAt.layout(200,200,400,400);
        }
    }
}
