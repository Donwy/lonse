package com.example.lonse.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lonse.R;
import com.example.lonse.adapter.RecyclerViewAdapter;
import com.example.lonse.entity.ProductEntity;
import com.example.lonse.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Donvy_y on 2019/8/5
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewActivity";
    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ProductEntity> productList;

    private RecyclerViewAdapter adapter;

    private final int[] img = {
            R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
            R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12,
            R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable.img_16, R.drawable.img_17, R.drawable.img_18,
    };

    private final String[] title = {
            "EZ", "阿木木", "瑞兹", "盖伦", "乌鸦", "锤石",
            "艾希", "维鲁斯", "奥恩", "大树", "女警", "烬",
            "皇子", "龙龟", "亚索", "千珏", "机器人", "牛头"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);


        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        initProductList();
        adapter = new RecyclerViewAdapter(recyclerView.getContext(), productList);
        final Context context= recyclerView.getContext();
//        Log.d(TAG, "text: == 1  " + System.identityHashCode(productList) + productList);
        recyclerView.setAdapter(adapter);
//        Log.d(TAG, "text: == 1.1  " + System.identityHashCode(adapter)+ adapter);

        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(10);
        recyclerView.addItemDecoration(decoration);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                productList.clear();
                addProductList();                   //为什么不用initProductList？因为adapter需要对同一个productList进行通知状态改变了，
                                                    // 使用initProducList会重新new一个producList（使用System.identityHashCode(object),可以发现其新旧productList内存地址是不一样的
                adapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1500);
            }

        });



        //设置转圈圈的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN);
        //设置触发的距离
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        //设置滑动距离
        swipeRefreshLayout.setSlingshotDistance(400);

    }
    private void addProductList() {

        for (int i = 0; i < img.length; i++) {
            productList.add(new ProductEntity(img[i],title[i]));
        }
        Collections.shuffle(productList);
    }

    /**初始化数据*/
    private void initProductList() {
        productList  = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            productList.add(new ProductEntity(img[i],title[i]));
        }
    }


}
