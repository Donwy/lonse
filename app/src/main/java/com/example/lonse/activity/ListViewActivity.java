package com.example.lonse.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lonse.entity.ContactEntity;
import com.example.lonse.R;
import com.example.lonse.adapter.ListViewAdapter;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private ListView  lvContact;

    private ArrayList<ContactEntity> mData;
    private ListViewAdapter mAdapter;

    private final int[] PHOTO = new int[]{
            R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6,
            R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12,
            R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable.img_16, R.drawable.img_17, R.drawable.img_18,
    };

    private final String[] NAME = new String[]{
            "EZ", "阿木木", "瑞兹", "盖伦", "乌鸦", "锤石",
            "艾希", "维鲁斯", "奥恩", "大树", "女警", "烬",
            "皇子", "龙龟", "亚索", "千珏", "机器人", "牛头"
    };

    private final String[] MESSAGE = new String[]{
            "ez@qq.com", "amumu@qq.com", "ruizi@qq.com", "gailun@qq.com", "wuya@qq.com", "chuishi@qq.com",
            "aixi@qq.com", "weilusi@qq.com", "aoen@qq.com", "tree@qq.com", "police@qq.com" , "jin@qq.com",
            "huangzi@qq.com", "guigui@qq.com", "tuoersuo@qq.com", "qianjue@qq.com", "android@qq.com", "cow@qq.com"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lvContact = (ListView) findViewById(R.id.lv_contact);
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < NAME.length; i++) {
            mData.add(new ContactEntity(PHOTO[i], NAME[i], MESSAGE[i]));
        }
        mAdapter = new ListViewAdapter(mData);
        lvContact.setAdapter(mAdapter);
    }


}
