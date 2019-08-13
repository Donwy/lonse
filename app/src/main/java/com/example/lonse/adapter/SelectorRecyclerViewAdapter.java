package com.example.lonse.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lonse.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * @author Donvy_y
 * @date 2019/8/12
 */
public class SelectorRecyclerViewAdapter extends RecyclerView.Adapter<SelectorRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mLists;
    public Map<Integer, Boolean> map = new HashMap<>();

    public SelectorRecyclerViewAdapter(Context context, List<String> mLists){
        this.mContext = context;
        this.mLists = mLists;
    }

    @NonNull
    @Override
    public SelectorRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_selector_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {


        viewHolder.tv.setText(mLists.get(position));
        viewHolder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSelected) {
                if(isSelected == true) {
                    map.put(position, true);

                } else {
                    map.remove(position);
                }
            }
        });
        Log.d(TAG, "onBindViewHolder: map == " + map);
        if(map != null && map.containsKey(position)) {
            viewHolder.chk.setChecked(true);

        } else {
            viewHolder.chk.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        CheckBox chk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.recycle_selector_item_tv);
            chk = (CheckBox) itemView.findViewById(R.id.recycle_selector_item_cb);
        }
    }
}
