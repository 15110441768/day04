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
import com.example.lenovo.day04.ui.zhihu.bean.HotBean;

import java.util.ArrayList;

public class HotRecyclerViewAdapter extends RecyclerView.Adapter<HotRecyclerViewAdapter.ViewHolder> {
    private ArrayList<HotBean.RecentBean> recentBeans;
    Context context;

    public HotRecyclerViewAdapter(ArrayList<HotBean.RecentBean> recentBeans, Context context) {
        this.recentBeans = recentBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HotBean.RecentBean recentBean = recentBeans.get(i);
        viewHolder.title.setText(recentBean.getTitle());
        Glide.with(context).load(recentBean.getThumbnail()).into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return recentBeans.size();
    }

    public void setData(HotBean hotBean) {
        if (hotBean.getRecent()!=null&&hotBean.getRecent().size()>0){
            recentBeans.clear();
            recentBeans.addAll(hotBean.getRecent());
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=itemView.findViewById(R.id.thumbnail);
            title=itemView.findViewById(R.id.title);
        }
    }
}
