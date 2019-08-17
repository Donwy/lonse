package com.example.lonse.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * @author Donvy_y
 * @date 2019/8/16
 */
public class GesturePassword extends View {

    /**正常显示的颜色*/
    private Paint NORMAL_COLOR;
    /**选择显示的颜色*/
    private Paint SELECT_COLOR;
    /**错误显示的颜色*/
    private Paint ERROR_COLOR;
    /**圆的画笔*/
    private Paint mCirclePaint;
    /**线条的画笔*/
    private Paint mLinePaint;
    private final int row = 3;
    /**九宫格的边长*/
    private int mPatternWidth;
    /**圆的X、Y坐标*/
    private float mCircleX;
    private float mCircleY;
    private float mCircleR;

    private mCirclePoints[][] mCirclePoints = new mCirclePoints[3][3];



    public GesturePassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        Log.d(TAG, "initPaint:  mPatternWidth == :" + mPatternWidth);

    }






    private Paint getPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(70f);
        paint.setColor(color);
        return paint;
    }

    public void initPaint() {
        mCirclePaint = getPaint(Color.parseColor("#000000"));
        SELECT_COLOR = getPaint(Color.parseColor("#aaaaff"));

        mPatternWidth = Math.min( getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        mCircleX = mPatternWidth / 2;
        mCircleY = mPatternWidth / 2;

        mCircleR = mPatternWidth / 10;
        Log.d(TAG, "initPaint: 1:" + mPatternWidth);
        Log.d(TAG, "initPaint: " + mCircleX +", " +mCircleY +", "+mCircleR );


    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleX,mCircleY,mCircleR,mCirclePaint);
    }





    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

}

    class mCirclePoints extends Point {
        private int number;

        public mCirclePoints(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }

