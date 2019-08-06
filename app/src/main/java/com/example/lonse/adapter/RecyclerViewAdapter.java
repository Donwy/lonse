package com.example.lonse.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lonse.R;
import com.example.lonse.entity.ProductEntity;

import java.util.List;

/**
 * Created by Donvy_y on 2019/8/5
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<ProductEntity> mProductList;

    public RecyclerViewAdapter(Context context, List<ProductEntity> productList){
        Log.d("test == ", "productList == " + productList.toString());
        this.context = context;
        this.mProductList = productList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d("test == ", "viewHolder == " + viewHolder.toString());
        Glide.with(context).load(mProductList.get(position).getImg()).into(viewHolder.imageView);
        viewHolder.textView.setText(mProductList.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.recycle_item_img);
            textView = (TextView) itemView.findViewById(R.id.recycle_item_title);
        }
    }

}
