package com.example.lonse.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lonse.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * @author Donvy_y
 * @date 2019/8/8
 */
public class SelectorListViewAdapter extends BaseAdapter {

    List<String> data;
    private Context mContext;
    public static Map<Integer, Boolean> map = new HashMap<>();
    private LayoutInflater mInflater;



    public SelectorListViewAdapter(Context context,List<String> data) {
        this.mContext = context;
        this.data = data;

    }

    /**返回数据的长度*/
    @Override
    public int getCount() {
        return data.size();
    }

    /**返回当前item的位置*/
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    /**返回当前item的id*/
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        mInflater = LayoutInflater.from(mContext);
        if(convertView == null){
            // 获得ViewHolder对象
            // 导入布局并赋值给convertview
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_selector_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.list_selector_item_tv);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.list_selector_item_cb);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position));

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSelected) {
                if (isSelected == true) {
                    map.put(position, true);
                    Log.d(TAG, "onCheckedChanged: map == " + map);
                }else {
                    map.remove(position);
                }
            }
        });

        if (map!=null && map.containsKey(position)){
            viewHolder.checkBox.setChecked(true);
        }else {
            viewHolder.checkBox.setChecked(false);
        }
        return convertView;
    }



    final class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }
}
