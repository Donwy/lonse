package com.example.lonse.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Donvy_y on 2019/8/6
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    int space;
    public SpacesItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.top = space;
        outRect.right = space;
        outRect.bottom = space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
