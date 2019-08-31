package com.example.lonse.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * @author Donvy_y
 * @date 2019/8/16
 */
public class GesturePassword extends View {


    /**圆的画笔*/
    private Paint mCirclePaint;
    /**选中时的笔*/
    private Paint mSelectedPaint;
    /**线条的画笔*/
    private Paint mLinePaint;
    /**九宫格的边长*/
    private int mPatternWidth;
    /**圆的X、Y坐标*/
    private float mCircleX;
    private float mCircleY;
    private float mCircleR;
    //存储未被选中的圆点
    private List<CirclePoints> mCirclePointList;
    //存储选中的原点
    private Set<CirclePoints> mSelectedPointSet;
    //密码
    private LinkedHashSet<CirclePoints> mPasswordSet;
    //滑动状态
    private final int NORMAL = 1;
    private final int MOVE = 3;
    private int mStatus = NORMAL;
    //初始密码
    private final String INIT_PASSWORD = "12589";
    private boolean unlock = false;






    @RequiresApi(api = Build.VERSION_CODES.M)
    public GesturePassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Paint getPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(70f);
        paint.setColor(color);
        return paint;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initPaint() {
        mCirclePaint = getPaint(Color.parseColor("#aaeeff"));
        mSelectedPaint = getPaint(Color.parseColor("#FFD700"));
        mLinePaint = getPaint(Color.parseColor("#FFD700"));
        mLinePaint.setStrokeWidth(10);

        mPatternWidth = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        mCircleX = mPatternWidth / 2;
        mCircleY = mPatternWidth / 2;
        mCircleR = mPatternWidth / 15;

        mCirclePointList = new ArrayList<>();
        mSelectedPointSet =  new ArraySet<>();
        mPasswordSet = new LinkedHashSet<>();


        Log.d(TAG, "initPaint: 1: ==" + mPatternWidth);
        Log.d(TAG, "initPaint: ==" + mCircleX +", " + mCircleY + ", " + mCircleR );
        addNinePoints();

    }
    public void addNinePoints() {

        CirclePoints mCirclePoints = null;

        for (int i = 1; i <= 3; i++) {

            float x2 = mCircleX / 3;
            float y = mCircleY / 2 * i;
            mCirclePoints = new CirclePoints(1 + (i - 1) * 3);
            mCirclePoints.set(x2, y);
            mCirclePointList.add(mCirclePoints);

            float x1 = mCircleX;
            mCirclePoints = new CirclePoints(2 + (i - 1) * 3);
            mCirclePoints.set(x1, y);
            mCirclePointList.add(mCirclePoints);

            float x3 = mPatternWidth - mCircleX / 3;
            mCirclePoints = new CirclePoints(3 + (i - 1) * 3);
            mCirclePoints.set(x3, y);
            mCirclePointList.add(mCirclePoints);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mStatus) {
            case NORMAL:
                drawCircle(canvas);
                break;
            case MOVE:
                drawCircle(canvas);
                if (mSelectedPointSet.size() > 0) {
                    for (CirclePoints pointList : mSelectedPointSet) {
                        canvas.drawCircle(pointList.x, pointList.y, mCircleR, mSelectedPaint);
                        mPasswordSet.add(pointList);

                    }
                    CirclePoints pointA = mPasswordSet.iterator().next();
                    for (CirclePoints point : mPasswordSet) {
                            canvas.drawLine(pointA.x, pointA.y, point.x, point.y, mLinePaint);
                            pointA = point;
                        }
                }
                break;
        }
    }



    public void drawCircle(Canvas canvas) {
        if (unlock) {
            mCirclePaint.setColor(Color.parseColor("#00ff00"));
        } else {
            mCirclePaint = getPaint(Color.parseColor("#aaeeff"));
        }
        for (CirclePoints pointList : mCirclePointList) {
            canvas.drawCircle(pointList.x, pointList.y, mCircleR, mCirclePaint);
        }
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float downX = event.getX();
        float downY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float minR = mCircleR * 2;
                for (CirclePoints point : mCirclePointList) {
                    if (minR > Math.sqrt(Math.pow(downX - point.x, 2) + Math.pow(downY - point.y, 2))) {
                        minR = (float) Math.sqrt(Math.pow(downX - point.x, 2) + Math.pow(downY - point.y, 2));
                    }
                }
                //如果点击的点落在9宫格的任意点内，则屏蔽掉父组件viewpager的事件获取（解决横向滑动冲突）。
                if (mCircleR > minR) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for (CirclePoints point : mCirclePointList) {
                    mStatus = MOVE;
                    float circleX = point.x;
                    float circleY = point.y;
                    if (isInsideCircle(downX, downY, circleX, circleY)) {
                        mSelectedPointSet.add(point);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mStatus = NORMAL;
                StringBuffer password = new StringBuffer();
                for (CirclePoints point : mPasswordSet) {
                    password.append(point.getNumber());
                }
                if (INIT_PASSWORD.equals(password.toString())) {
                    unlock = true;
                }
                mSelectedPointSet.clear();
                mPasswordSet.clear();
                break;
        }
        invalidate();
        return true;
    }

    private boolean isInsideCircle(float downX, float downY, float CircleX, float circleY) {
        return mCircleR > Math.sqrt(Math.pow(downX - CircleX, 2) + Math.pow(downY - circleY, 2));
    }


    class CirclePoints extends PointF {
        private int number;

        public CirclePoints(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }
}

