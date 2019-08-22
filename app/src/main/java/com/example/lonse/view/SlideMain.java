package com.example.lonse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.core.view.MotionEventCompat;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

public class SlideMain extends SlidingPaneLayout {
    private static final String TAG = "SlideMain";
    private float mInitialMotionX;
    private float mInitialMotionY;
    /** 最小触发事件的划动距离 */
    private float mEdgeSlop;//手滑动的距离

    public SlideMain(Context context) {
        this(context, null);
    }

    public SlideMain(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMain(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //--------  ViewConfiguration 滑动参数设置类 --------//
        ViewConfiguration config = ViewConfiguration.get(context);
        //--------  它获得的是触发移动事件的最短距离，如果小于这个距离就不触发移动控件  --------//
        mEdgeSlop = config.getScaledEdgeSlop();
        Log.d(TAG, "SlideMain:的 " + mEdgeSlop);
    }


    /**
     * 拦截手势事件
     * 避免划动冲突
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN: {
                // 屏幕检测到第一个触点按下
                mInitialMotionX = ev.getX();
                mInitialMotionY = ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                // 触点在屏幕上移动
                final float x = ev.getX();
                final float y = ev.getY();
//                // 避免与其他划动控件冲突
//                Log.d(TAG, "onInterceptTouchEvent: 初始化X： " + mInitialMotionX + " 互动距离： " + mEdgeSlop);
                if (mInitialMotionX > mEdgeSlop && !isOpen() && canScroll(this, false,
                        Math.round(x - mInitialMotionX), Math.round(x), Math.round(y))) {
                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                    return super.onInterceptTouchEvent(cancelEvent);
                }
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
