package com.example.bitzone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.LinkViewHolder> {


    private ArrayList<StudentLinkItem> mlinkItems;

    public LinkAdapter(ArrayList<StudentLinkItem> linkItems) {
        mlinkItems = linkItems;
    }


    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_item,parent,false);
        LinkViewHolder linkViewHolder = new LinkViewHolder(view);
        return linkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder linkViewHolder, int i) {
        StudentLinkItem currentitem = mlinkItems.get(i);
        linkViewHolder.textView.setText(currentitem.getLink());
    }



    @Override
    public int getItemCount() {
        return mlinkItems.size();
    }

    public  static  class LinkViewHolder extends com.example.bitzone.LinkViewHolder{
      public TextView textView;

        public LinkViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.LinkText);
        }
    }

}
