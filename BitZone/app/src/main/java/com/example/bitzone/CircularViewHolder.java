package com.example.bitzone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bitzone.Interface.ItemClickListener;

public class CircularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView circularText , userName , circularHead ,Message ,currentDate , currentDay ;
    public ImageView circularImage ;

    private ItemClickListener itemClickListener ;

    public CircularViewHolder(@NonNull View itemView) {
        super(itemView);

        circularText = itemView.findViewById(R.id.circular_text);
        userName = itemView.findViewById(R.id.user_name);
        circularHead = itemView.findViewById(R.id.circular_head);
        Message = itemView.findViewById(R.id.message);

        currentDay = itemView.findViewById(R.id.current_day);
        circularImage = itemView.findViewById(R.id.circular_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
