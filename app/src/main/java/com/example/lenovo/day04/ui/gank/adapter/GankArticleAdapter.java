package com.example.lenovo.day04.ui.gank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.gank.bean.GankArticleBean;

import java.util.ArrayList;

public class GankArticleAdapter extends RecyclerView.Adapter<GankArticleAdapter.ViewHolder> {
    private ArrayList<GankArticleBean.ResultsBean> recyclerviewList;
    Context context;

    public GankArticleAdapter(ArrayList<GankArticleBean.ResultsBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gank_article_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GankArticleBean.ResultsBean resultsBean = recyclerviewList.get(i);
        viewHolder.desc.setText(resultsBean.getDesc());
        viewHolder.publishedAt.setText(resultsBean.getPublishedAt().split("T")[0]);
        viewHolder.who.setText(resultsBean.getWho());
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView desc;
        private TextView who;
        private TextView publishedAt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc=itemView.findViewById(R.id.desc);
            who=itemView.findViewById(R.id.who);
            publishedAt=itemView.findViewById(R.id.publishedAt);
        }
    }
}
