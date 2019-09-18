package com.example.lonse.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author Donvy_y
 * @date 2019/8/21
 */
public class WaveView extends View {
    //定义波浪画笔
    private Paint mPaint;
    //测试红点画笔
    private Paint mCyclePaint;

    //波浪Path类
    private Path mPath;
    //波浪Path类
    private Path mPath2;
    //一个波浪长度
    private int mWaveLength = 500;
    private  int mWaveCount;

    //平移偏移量
    private int mOffset;
    //波纹的中间轴
    private int mCenterY;

    //屏幕高度
    private int mScreenHeight;
    //屏幕宽度
    private int mScreenWidth;






    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setOnClickListener(this);
    }

    public void init() {
        mPath = new Path();
        mPath2 = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);


        //用来绘制测试红点
        mCyclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCyclePaint.setColor(Color.RED);
        mCyclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        mScreenHeight = h;
        mScreenWidth = w;
        //加1.5：至少保证波纹有2个，至少2个才能实现平移效果
        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);
        mCenterY = mScreenHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();

//        //移到屏幕外最左边
        mPath.moveTo(-mScreenWidth, mCenterY);
        mPath2.moveTo(0, mCenterY);
        mPath2.quadTo((mWaveLength * 1 / 4),mCenterY - 60,(mWaveLength/2),  mCenterY);


        canvas.drawPath(mPath2, mPaint);

        for (int i = 0; i < mWaveCount; i++) {
            //正弦曲线
            mPath.quadTo((-mWaveLength * 3 / 4) + (i * mWaveLength) + mOffset, mCenterY + 60, (-mWaveLength / 2) + (i * mWaveLength) + mOffset, mCenterY);
            mPath.quadTo((-mWaveLength / 4) + (i * mWaveLength) + mOffset, mCenterY - 60, i * mWaveLength + mOffset, mCenterY);
//            //贝塞尔坐标，测试红点
//            canvas.drawCircle(i * mWaveLength + mOffset, mCenterY, 5, mCyclePaint);
//            canvas.drawCircle((-mWaveLength * 3 / 4) + (i * mWaveLength) + mOffset, mCenterY + 60, 5, mCyclePaint);
//            canvas.drawCircle((-mWaveLength / 2) + (i * mWaveLength) + mOffset, mCenterY, 5, mCyclePaint);
//            canvas.drawCircle((-mWaveLength / 4) + (i * mWaveLength) + mOffset, mCenterY - 60, 5, mCyclePaint);

        }
            //填充矩形
            mPath.lineTo(mScreenWidth, mScreenHeight - 150);
            mPath.lineTo(0, mScreenHeight - 150);
            mPath.close();
            canvas.drawPath(mPath, mPaint);
    }

    public void  setOnClickListener(View view) {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }


}
