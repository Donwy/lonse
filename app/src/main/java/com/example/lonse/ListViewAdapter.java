package com.example.lonse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lonse.view.SlideLayout;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListViewAdapter extends BaseAdapter {

    private ArrayList<ContactEntity> mData;
    private SlideLayout mslideLayout;

    ListViewAdapter(ArrayList<ContactEntity> data){
        this.mData = data;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
       ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_list_item_content, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.civPhoto.setImageResource(mData.get(position).getmPhoto());
            holder.tvName.setText(mData.get(position).getmName());
            holder.tvMessage.setText(mData.get(position).getmMessage());

            holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(parent.getContext(), "" + mData.get(position).getmName(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(position);
                    ListViewAdapter.this.notifyDataSetChanged();
                }
            });


            mslideLayout = (SlideLayout) convertView;
            mslideLayout.setOnSlideChangeListen(new SlideLayout.onSlideChangeListen() {
                @Override
                public void onMenuOpen(SlideLayout slideLayout) {
                    mslideLayout = slideLayout;
                }

                @Override
                public void onMenuClose(SlideLayout slideLayout) {
                    if (mslideLayout != null) {
                        mslideLayout = null;
                    }
                }

                @Override
                public void onClick(SlideLayout slideLayout) {
                    if (mslideLayout != null) {
                        mslideLayout.closeMenu();
                    }
                }
            });
        return convertView;
    }
    
    static class ViewHolder {
        CircleImageView civPhoto;
        TextView tvName;
        TextView tvMessage;
        TextView tvDelete;

        ViewHolder(View itemView) {
            civPhoto = itemView.findViewById(R.id.item_image);
            tvName = itemView.findViewById(R.id.item_name);
            tvMessage = itemView.findViewById(R.id.item_message);
            tvDelete = itemView.findViewById(R.id.menu_delete);
        }
    }
}
