package com.example.lonse.activity;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.example.lonse.R;
import com.example.lonse.view.SlideMenu;

public class SlideMenuActivity extends AppCompatActivity {

    /** 侧划控件 */
    private SlideMenu slidMenuLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_mainlayout);

        slidMenuLayout = (SlideMenu) findViewById(R.id.slidMenuLayout);
        initSlidingPaneLayout();
    }

    /**
     * 初始化左划侧边菜单栏效果
     */
    private void initSlidingPaneLayout(){
//        // 设置侧划后首页上透明，不带颜色，不会变灰色
//        slidMenuLayout.setSliderFadeColor(Color.TRANSPARENT);
        // 拿到左侧菜单栏view
        final View leftView = slidMenuLayout.getChildAt(0);
        slidMenuLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(@NonNull View panel, float slideOffset) {
                // 划动过程中
                // 左侧边栏的变化
                leftView.setPivotX(leftView.getWidth() / 5.0f);
                leftView.setPivotY(leftView.getHeight() / 2.0f);
                leftView.setScaleX(0.8f + 0.2f * slideOffset);//0.8~1
                leftView.setScaleY(0.8f + 0.2f * slideOffset);//0.8~1
                leftView.setAlpha(0.8f + 0.2f * slideOffset);//0.8~1
                // 首页的变化
                panel.findViewById(R.id.ll_main).setScaleX(1f - 0.14f * slideOffset);
                panel.findViewById(R.id.ll_main).setScaleY(1f - 0.14f * slideOffset);
                // 背景阴影图片的变化
//                panel.findViewById(R.id.img_shadow).setScaleX(1f - 0.1f * slideOffset);
//                panel.findViewById(R.id.img_shadow).setScaleY(1f - 0.13f * slideOffset);
//                panel.findViewById(R.id.img_shadow).setAlpha(1f - 0.3f * slideOffset);
            }

            @Override
            public void onPanelOpened(@NonNull View view) {
            }

            @Override
            public void onPanelClosed(@NonNull View panel) {
            }
        });
    }

}