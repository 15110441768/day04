package com.example.lenovo.day04.ui.v2ex.adapter;

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
import com.example.lenovo.day04.ui.v2ex.bean.ArticleBean;

import java.util.ArrayList;

public class V2exRecyclerViewAdapter extends RecyclerView.Adapter<V2exRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ArticleBean> recyclerviewList;
    Context context;

    public V2exRecyclerViewAdapter(ArrayList<ArticleBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_v2ex_recyclerview, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ArticleBean articleBean = recyclerviewList.get(i);
        Glide.with(context).load("https:"+articleBean.getSrc()).into(viewHolder.src);
        viewHolder.author.setText(articleBean.getAuthor());
        viewHolder.txt.setText(articleBean.getTxt());
        viewHolder.title.setText(articleBean.getTitle());
        viewHolder.node.setText(articleBean.getNode());
        viewHolder.livid.setText(articleBean.getLivid());
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView src;
        private TextView author;
        private TextView txt;
        private TextView title;
        private TextView livid;
        private TextView node;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            src=itemView.findViewById(R.id.src);
            author=itemView.findViewById(R.id.author);
            txt=itemView.findViewById(R.id.txt);
            title=itemView.findViewById(R.id.title);
            livid=itemView.findViewById(R.id.livid);
            node=itemView.findViewById(R.id.node);
        }
    }

    public interface SendDataToFragment{
        void sendData();
    }
}
