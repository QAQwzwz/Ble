package com.srtianxia.bleattendance.ui.teacher.query;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

import com.srtianxia.bleattendance.R;

public class PopWindow extends PopupWindow {

    private View view;
    private boolean clickOutSide = false;//用于判断是否点击过外部
    public PopWindow(Context mContext,View parent,int resId)
    {
        super(mContext);
        view = View.inflate(mContext,resId,null);
        //弹窗初始化
        setContentView(view);
        setWidth(247);
        setHeight(400);
        //自定义弹窗
        setBackgroundDrawable(new ColorDrawable(0xF0FFFFFF));//设置背景
        this.setAnimationStyle(R.style.popwin_style);
        setFocusable(true);
        setOutsideTouchable(true);//多点触碰
        update();
    }

    public void isClickOutSide(boolean is)
    {
        clickOutSide = is;
    }

    public void setClickMiss()
    {
        view.setOnClickListener((v) ->
        {
            if(!isAboveAnchor())
            {
                dismiss();
            }
        });
    }

    public View getView()
    {
        return view;
    }
}
