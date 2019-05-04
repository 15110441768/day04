package com.example.lenovo.day04.ui.main;

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
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsArticleBean;

import java.util.ArrayList;

class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder> {
    private ArrayList<DailyNewsArticleBean> recyclerviewList;
    Context context;

    public CollectAdapter(ArrayList<DailyNewsArticleBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DailyNewsArticleBean dailyNewsArticleBean = recyclerviewList.get(i);
        Glide.with(context).load(dailyNewsArticleBean.getImages()).into(viewHolder.images);
        viewHolder.title.setText(dailyNewsArticleBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
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
