package com.example.lonse.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.lonse.R;
import com.example.lonse.adapter.CarouselAdapter;

import java.util.ArrayList;


/**
 * @author Donvy_y
 * @date 2019/9/18
 */
public class CarouselActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager carouselPager;
    private ArrayList<View> viewLists;
    private CarouselAdapter carouselAdapter;
    private LinearLayout dotLinearLayout;
    private int mCurrentIndex = 0;
    private ImageView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);
        carouselPager = findViewById(R.id.carousel);
        dotLinearLayout = findViewById(R.id.dot_indicator);
        initView();
    }

    private void initView() {
        getDot();
        viewLists = new ArrayList<>();
        LayoutInflater layoutInflater = getLayoutInflater();
        viewLists.add(layoutInflater.inflate(R.layout.carousel_card_one, null));
        viewLists.add(layoutInflater.inflate(R.layout.carousel_card_one, null));
        viewLists.add(layoutInflater.inflate(R.layout.carousel_card_one, null));
        carouselAdapter = new CarouselAdapter(viewLists);
        carouselPager.setAdapter(carouselAdapter);
        dotLinearLayout.getChildAt(0).setEnabled(true);
        carouselPager.addOnPageChangeListener(this);
    }

    private void getDot() {
        for (int i = 0; i < 3; i++) {
            view = new ImageView(CarouselActivity.this);
            view.setBackgroundResource(R.drawable.dot_indicator);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30,30);
            if (i == 0) {
                view.setEnabled(true);

            }else {
                view.setEnabled(false);
                layoutParams.leftMargin = 10;
            }
            dotLinearLayout.addView(view,layoutParams);
        }
    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        dotLinearLayout.getChildAt(mCurrentIndex).setEnabled(false);
        dotLinearLayout.getChildAt(position).setEnabled(true);
        mCurrentIndex = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
