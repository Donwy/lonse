package com.example.lonse.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lonse.R;

/**
 * Created by dk on 2018/1/12.
 */

public class PicChangePopup extends BaseCustomPopup {
    private RelativeLayout album, photo;
    private TextView title;

    public PicChangePopup(Context context) {
        super(context);
    }

    @Override
    protected void initAttributes() {
        setContentView(R.layout.change_headicon_pop,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.popo_show_from_bottom)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.5f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK);
    }

    @Override
    protected void initViews(View view) {
        album = getView(R.id.album_rel);
        photo = getView(R.id.photo_rel);
        title = getView(R.id.chang_pic_title);
    }

    public PicChangePopup albumClick(View.OnClickListener listener) {
        if (listener != null) {
            album.setOnClickListener(listener);
        }
        return this;
    }

    public PicChangePopup photoClick(View.OnClickListener listener) {
        if (listener != null) {
            photo.setOnClickListener(listener);
        }
        return this;
    }
    public PicChangePopup setTitleText(String str) {
        if (!TextUtils.isEmpty(str)) {
            title.setText(str);
        }
        return this;
    }
}
