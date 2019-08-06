package com.example.lonse.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lonse.R;
import com.example.lonse.adapter.RecyclerViewAdapter;
import com.example.lonse.entity.ProductEntity;
import com.example.lonse.view.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by Donvy_y on 2019/8/5
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewActivity";
    private RecyclerView recyclerView;
    private List<ProductEntity> productList;
    private RecyclerViewAdapter adapter;

    private final int[] img= new int[] {
            R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
            R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12,
            R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable.img_16, R.drawable.img_17, R.drawable.img_18,
    };

    private final String[] title = new String[] {
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
        initData();
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(10);
        recyclerView.addItemDecoration(decoration);

    }

    private void initData() {
       productList  = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            productList.add(new ProductEntity(img[i],title[i]));
            Log.d(TAG, "initData: " + productList );
        }
        adapter = new RecyclerViewAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }
}
