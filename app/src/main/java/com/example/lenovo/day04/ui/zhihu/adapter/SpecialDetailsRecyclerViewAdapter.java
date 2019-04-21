package com.example.lenovo.day04.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialDetailsBean;

import java.util.ArrayList;

public class SpecialDetailsRecyclerViewAdapter extends RecyclerView.Adapter<SpecialDetailsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<SpecialDetailsBean.StoriesBean> storiesBeans;
    Context context;

    public SpecialDetailsRecyclerViewAdapter(ArrayList<SpecialDetailsBean.StoriesBean> storiesBeans, Context context) {
        this.storiesBeans = storiesBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SpecialDetailsBean.StoriesBean storiesBean = storiesBeans.get(i);
        Glide.with(context).load(storiesBean.getImages().get(0)).into(viewHolder.images);
        viewHolder.title.setText(storiesBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return storiesBeans.size();
    }

    public void setData(SpecialDetailsBean specialDetailsBean) {
        if (specialDetailsBean.getStories()!=null&&specialDetailsBean.getStories().size()>0){
            storiesBeans.clear();
            storiesBeans.addAll(specialDetailsBean.getStories());
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView images;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.images);
            title=itemView.findViewById(R.id.title);
        }
    }
}
