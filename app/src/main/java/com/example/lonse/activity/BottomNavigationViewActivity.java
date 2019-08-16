package com.example.lonse.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lonse.R;
import com.example.lonse.adapter.FragmentAdapter;
import com.example.lonse.fragment.BookFragment;
import com.example.lonse.fragment.HomeFragment;
import com.example.lonse.fragment.LikeFragment;
import com.example.lonse.fragment.PlusFragment;
import com.example.lonse.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Donvy_y
 * @date 2019/8/15
 */
public class BottomNavigationViewActivity extends AppCompatActivity {

    private BottomNavigationView bnView;
    private ViewPager viewPager;
    private List<Fragment> fragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnavigationview);
        bnView =  (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        viewPager = (ViewPager) findViewById(R.id.bn_viewPage);

        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new BookFragment());
        fragments.add(new PlusFragment());
        fragments.add(new LikeFragment());
        fragments.add(new UserFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);

        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                switch (menuId) {
                    case R.id.menu_one:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_two:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_three:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_four:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.menu_five:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //将滑动到的页面对应的 menu 设置为选中状态
                bnView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
