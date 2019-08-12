package com.example.lonse.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lonse.R;
import com.example.lonse.adapter.SelectorListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Donvy_y
 * @date 2019/8/8
 */
public class SelectorListViewActivity extends AppCompatActivity {
    private static final String TAG = "SelectorListViewActivit";
    private ListView selectorListView;
    private SelectorListViewAdapter mAdapter;
    private TextView textView;
    private CheckBox checkBox;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private List<String> mDataList;
    private int num = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_lisview);
        selectorListView = (ListView) findViewById(R.id.select_listView);
        textView = (TextView) findViewById(R.id.list_selector_item_tv);
        checkBox = (CheckBox) findViewById(R.id.list_selector_item_cb);
        button1 = (Button) findViewById(R.id.bt_selectAll);
        button2 = (Button) findViewById(R.id.bt_select_reverse);
        button3 = (Button) findViewById(R.id.bt_select_cancel);
        button4 = (Button) findViewById(R.id.bt_select_edit);

        //初始化数据
        initData();
        mAdapter = new SelectorListViewAdapter(this, mDataList);
        selectorListView.setAdapter(mAdapter);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < mDataList.size(); i++) {
                    mAdapter.map.put(i, true);
                }
                num = mDataList.size();
                dataChanged();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i < mDataList.size(); i++) {
                    if (mAdapter.map.containsKey(i)) {
                        mAdapter.map.remove(i);
                    }else {
                        mAdapter.map.put(i, true);
                    }
                }
                num = mAdapter.map.size();
                dataChanged();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < mDataList.size(); i++) {
                    if (mAdapter.map.containsKey(i)) {
                        mAdapter.map.remove(i);
                    }
                }
                num = 0;
                dataChanged();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.GONE);
            }
        });
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            mDataList.add("item" + i);
        }
    }

    private void dataChanged() {
        mAdapter.notifyDataSetChanged();
        Toast toast = Toast.makeText(this, "选中了 " + num, Toast.LENGTH_SHORT);
        toast.show();
    }
}
