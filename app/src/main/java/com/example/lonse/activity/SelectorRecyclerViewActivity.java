package com.example.lonse.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lonse.R;
import com.example.lonse.adapter.SelectorRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Donvy_y
 * @date 2019/8/9
 */
public class SelectorRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mLists;
    private SelectorRecyclerViewAdapter selectorRecyclerViewAdapter;
    private int num = 0;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.select_recyclerview);
        btn1 = (Button) findViewById(R.id.bt_recycler_selectAll);
        btn2 = (Button) findViewById(R.id.bt_recycler_select_reverse);
        btn3 = (Button) findViewById(R.id.bt_recycler_select_cancel);
        btn4 = (Button) findViewById(R.id.bt_recycler_select_edit);

        init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        selectorRecyclerViewAdapter = new SelectorRecyclerViewAdapter(this, mLists);
        recyclerView.setAdapter(selectorRecyclerViewAdapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < mLists.size(); i++) {
                    selectorRecyclerViewAdapter.map.put(i, true);
                }
                num = mLists.size();
                dataChanged();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < mLists.size();i++) {
                    if(selectorRecyclerViewAdapter.map.containsKey(i)) {
                        selectorRecyclerViewAdapter.map.remove(i);
                    } else {
                        selectorRecyclerViewAdapter.map.put(i, true);
                    }
                }
                num = selectorRecyclerViewAdapter.map.size();
                dataChanged();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectorRecyclerViewAdapter.map.clear();
                num = 0;
                dataChanged();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.GONE);
            }
        });
    }

    public void init() {
        mLists = new ArrayList<>();
        for (int i= 0; i < 20; i++) {
            mLists.add("item" + i);
        }
    }
    public void  dataChanged() {
        selectorRecyclerViewAdapter.notifyDataSetChanged();
        Toast toast = Toast.makeText(this, "选中了 " + num, Toast.LENGTH_SHORT);
        toast.show();
    }
}
