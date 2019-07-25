package com.example.lonse.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SlideLayout extends FrameLayout {


    private static final String TAG = "SlideLayout";
    private View mContentView;
    private View mMenuView;

    private int mMenuWidth;
    private int mMenuHeight;
    private int mContentWidth;
    private int mContentHeight;

    private Scroller mScroller;

    private float startX;
    private float startY;
    private float downX;
    private float downY;


    private onSlideChangeListen onSlideChangeListen;

    public interface onSlideChangeListen {
        void onMenuOpen(SlideLayout slideLayout);

        void onMenuClose(SlideLayout slideLayout);

        void onClick(SlideLayout slideLayout);
    }

    public void setOnSlideChangeListen(SlideLayout.onSlideChangeListen onSlideChangeListen) {
        this.onSlideChangeListen = onSlideChangeListen;
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        mContentView = getChildAt(0);
        mMenuView = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentWidth = getMeasuredWidth();
        mContentHeight = getMeasuredHeight();
        mMenuWidth = mMenuView.getMeasuredWidth();
        mMenuHeight = mMenuView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed,int left,int top,int right,int bottom){
        super.onLayout(changed, left, top, right, bottom);

        //将menu布局到右侧不可见（屏幕外）
        mMenuView.layout(mContentWidth,0,mContentWidth + mMenuWidth,mMenuHeight);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        /*获取x,y的值*/
        final float x = event.getX();
        final float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = startX = x;
                downY = startY = y;
//                Log.d(TAG, "onTouchEvent:点击事件的这个startX: "+ startX + " 这个startY："+ startY);
//                Log.d(TAG, "onTouchEvent:点击事件的这个x: "+ x + " 这个y："+ y);
                break;
            case MotionEvent.ACTION_MOVE:
                final float dx = (int) (x - startX);                     //x的滑动位移
                final float dy = (int) (startY - y);                     //y的滑动位移
//                Log.d(TAG, "onTouchEvent:滑动事件1的这个startX: "+ startX + " 这个startY："+ startY);
//                Log.d(TAG, "onTouchEvent:滑动事件2的这个x: "+ x + "  这个y："+ y);
//                Log.d(TAG, "onTouchEvent:滑动事件3的这个dx: "+ dx + "  这个dy："+ dy);

                int disX = (int) (getScrollX() - dx);
//                Log.d(TAG, "onTouchEvent:滑动事件4的这个getScrollX: "+ getScrollX());    记录上一次滑动的距离
//                Log.d(TAG, "onTouchEvent:滑动事件5的这个disX: "+ disX );                 每一次滑动的距离
                if(disX <= 0){
                    disX = 0;
                }else if(disX >= mMenuWidth){
                    disX = mMenuWidth;
                }

                scrollTo(disX, getScrollY());                                   //滑动事件

                final float moveX = Math.abs(x - downX);
                final float moveY = Math.abs(y - downY);
                if (moveX > moveY && moveX > 10f) {
                    //剥夺ListView对touch事件的处理权
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_UP:
                if(getScrollX() < mMenuWidth/2){
                    closeMenu();
                }else {
                    openMenu();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        boolean intercept = false;
        final float x = event.getX();
        final float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                if(onSlideChangeListen !=null ){
                    onSlideChangeListen.onClick(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float moveX = Math.abs(x - downX);
                if (moveX > 10f) {
                    //对儿子touch事件进行拦截
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return intercept;
    }

    @Override
    public void computeScroll(){
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public final void openMenu() {
        mScroller.startScroll(getScrollX(), getScrollY(), mMenuWidth - getScrollX(), 0);
        invalidate();
        if(onSlideChangeListen != null){
            onSlideChangeListen.onMenuOpen(this);
        }
    }

    public final void closeMenu() {
        mScroller.startScroll(getScrollX(), getScrollY(), 0 - getScrollX(), 0);
        invalidate();
        if(onSlideChangeListen != null){
            onSlideChangeListen.onMenuOpen(this);
        }
    }
}
