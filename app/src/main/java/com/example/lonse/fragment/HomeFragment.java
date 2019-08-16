package com.example.lonse.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lonse.R;
import com.example.lonse.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Donvy_y
 * @date 2019/8/7
 */
public class HomeFragment extends Fragment {

    private ArrayList<View> viewLists;
    private List<String> titles;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_home, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewPage);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

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
