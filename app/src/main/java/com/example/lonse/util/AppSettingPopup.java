package com.example.lonse.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lonse.R;
import com.example.lonse.view.BaseCustomPopup;

/**
 * Created by dk on 2018/1/16.
 */

@SuppressWarnings("UnusedReturnValue")
public class AppSettingPopup extends BaseCustomPopup {
    private LinearLayout content1;
    private TextView sure, cancel, popTitle, content2, permissionTips;
    private Context context;

    private boolean isClick = false;

    public AppSettingPopup(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.app_setting_pop_layout,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.popo_show_from_center)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(false)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.5f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK);
    }

    @Override
    protected void initViews(View view) {
        sure = getView(R.id.sure);
        cancel = getView(R.id.cancel);
        popTitle = getView(R.id.pop_title);
        content1 = getView(R.id.content1);
        content2 = getView(R.id.content2);
        permissionTips = getView(R.id.permiss_tip);
        content2.setMovementMethod(ScrollingMovementMethod.getInstance());
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public AppSettingPopup sureTextOnClickListener(View.OnClickListener listener) {
        if (listener != null) {
            sure.setOnClickListener(listener);
        }
        return this;
    }

    public AppSettingPopup cancelTextOnClickListener(View.OnClickListener listener) {
        if (listener != null) {
            cancel.setOnClickListener(listener);
        }
        return this;
    }

    public AppSettingPopup setTitleText(String str) {
        if (!TextUtils.isEmpty(str)) {
            popTitle.setText(str);
        }
        return this;
    }

    public AppSettingPopup noTitle(){
        if (popTitle != null){
            popTitle.setText("");
            popTitle.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public AppSettingPopup setSureText(String str) {
        if (!TextUtils.isEmpty(str)) {
            sure.setText(str);
        }
        return this;
    }

    public AppSettingPopup setContentText(String str) {
        if (!TextUtils.isEmpty(str)) {
            content1.setVisibility(View.GONE);
            content2.setVisibility(View.VISIBLE);
            content2.setText(str);
        }
        return this;
    }

//    public AppSettingPopup scroll(){
//        if (content2 != null){
//            content2.setMovementMethod(ScrollingMovementMethod.getInstance());
//        }
//        return this;
//    }

    public AppSettingPopup setPermissText(String str) {
        if (!TextUtils.isEmpty(str)) {
            permissionTips.setText(str);
        }
        return this;
    }
    public AppSettingPopup setOneButton(boolean b) {
        if (b){
            cancel.setVisibility(View.GONE);
        }else {
            cancel.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public AppSettingPopup agreement(){
        if (popTitle != null){
            popTitle.setVisibility(View.GONE);
        }
        if (content1 != null){
            content1.setVisibility(View.GONE);
        }
        if (content2 != null){
            content2.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public TextView getContentTextView(){
        return content2;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }
}
