package com.example.lonse.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Donvy_y
 * @date 2019/8/7
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;
    private List<String> titles;



    public ViewPagerAdapter(ArrayList<View> viewLists, List<String> titles){
        super();
        this.viewLists = viewLists;
        this.titles = titles;
    }

    /**获取要滑动的页面的数量*/
    @Override
    public int getCount() {
        return viewLists.size();
    }

    /**来判断显示的是否是同一个界面*/
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**PagerAdaPeter只缓存要显示的图片，如果滑动的图片超出了缓存的范围*/
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewLists.get(position));
    }

    /**当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
     * 我们将要显示的View加入到ViewGroup中，然后作为返回值返回即可
     **/
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewGroup,int position) {
        viewGroup.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.size() > 0 ? titles.get(position) : "";
    }
}
