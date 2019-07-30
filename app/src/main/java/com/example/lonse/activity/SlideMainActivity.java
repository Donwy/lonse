package com.example.lonse.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.example.lonse.R;
import com.example.lonse.view.SlideMain;

public class SlideMainActivity extends AppCompatActivity {
    private static final String TAG = "SlideMainActivity";
    /** 侧划主页 */
    private SlideMain slideMainLayout;
    private SlideMain slideMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_mainlayout);

        slideMainLayout = (SlideMain) findViewById(R.id.slidMainLayout);
        slideMenuLayout = (SlideMain) findViewById(R.id.slideMenuLayout);
        initSlidingPaneLayout();
    }

    /**
     * 初始化左划侧边菜单栏效果
     */
    private void initSlidingPaneLayout(){
//        设置侧划后首页上透明，不带颜色，不会变灰色
//        slideMainLayout.setSliderFadeColor(Color.TRANSPARENT);
//        拿到左侧菜单栏view
        final View leftView = slideMainLayout.getChildAt(0);
//        Log.d(TAG, "initSlidingPaneLayout: " + leftView);

//        主页滑动监听
        slideMainLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(@NonNull View panel, float slideOffset) {
                // 划动过程中
                // 左侧边栏的变化
                Log.d(TAG, "onPanelSlide等于: " + slideOffset);
                leftView.setPivotX(leftView.getWidth() / 5.0f);
                leftView.setPivotY(leftView.getHeight() / 2.0f);
                leftView.setScaleX(0.8f + 0.2f * slideOffset);//0.8~1
                leftView.setScaleY(0.8f + 0.2f * slideOffset);//0.8~1
                leftView.setAlpha(0.8f + 0.2f * slideOffset);//0.8~1
                /**
                 * 首页的变化
                 * setScaleX:代表view整体以X轴缩放,以view的中心坐标作为参考锚点
                 * setScaleY:代表view整体以Y轴缩放,以view的中心坐标作为参考锚点
                 */
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

//        侧栏滑动监听
        slideMenuLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(@NonNull View panel, float slideOffset) {

            }

            /**
             * Called when a sliding pane becomes slid completely open. The pane may or may not
             * be interactive at this point depending on how much of the pane is visible.
             *
             * @param panel The child view that was slid to an open position, revealing other panes
             */
            @Override
            public void onPanelOpened(@NonNull View panel) {

            }

            /**
             * Called when a sliding pane becomes slid completely closed. The pane is now guaranteed
             * to be interactive. It may now obscure other views in the layout.
             *
             * @param panel The child view that was slid to a closed position
             */
            @Override
            public void onPanelClosed(@NonNull View panel) {

            }

        });
    }

}