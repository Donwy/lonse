package com.example.lonse.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.lonse.R;
import com.example.lonse.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Donvy_y
 * @date 2019/8/12
 */
public class ViewPageActivity extends AppCompatActivity {

    private ArrayList<View> viewLists;
    private List<String> titles;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        viewPager = (ViewPager) findViewById(R.id.viewPage);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        viewLists = new ArrayList<>();
        LayoutInflater layoutInflater = getLayoutInflater();
        viewLists.add(layoutInflater.inflate(R.layout.viewpage_one, null,false));
        viewLists.add(layoutInflater.inflate(R.layout.viewpage_two, null,false));
        viewLists.add(layoutInflater.inflate(R.layout.viewpage_three, null,false));

        titles = new ArrayList<>();
        titles.add("one");
        titles.add("two");
        titles.add("three");
        viewPagerAdapter = new ViewPagerAdapter(viewLists , titles);

        for (String title :titles) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);

    }
}
