package com.example.lenovo.day04.ui.gold.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.gold.bean.GoldTitleBean;
import com.example.lenovo.day04.widget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

public class HomeSpecialRecyclerViewAdapter extends RecyclerView.Adapter<HomeSpecialRecyclerViewAdapter.ViewHolder> implements TouchCallBack{
    private ArrayList<GoldTitleBean> goldTitleBeans;
    Context context;

    public HomeSpecialRecyclerViewAdapter(ArrayList<GoldTitleBean> goldTitleBeans, Context context) {
        this.goldTitleBeans = goldTitleBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_special, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final GoldTitleBean goldTitleBean = goldTitleBeans.get(i);
        viewHolder.tv.setText(context.getResources().getString(goldTitleBean.title));
        viewHolder.sc.setChecked(goldTitleBean.isChecked);

        viewHolder.sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goldTitleBean.isChecked=isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return goldTitleBeans.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换集合中两个数据的位置
        Collections.swap(goldTitleBeans,fromPosition,toPosition);
        //刷新界面,局部刷新,索引会混乱
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        goldTitleBeans.remove(position);
        //局部刷新,索引会混乱+集合越界
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private SwitchCompat sc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
            sc=itemView.findViewById(R.id.sc);
        }
    }
}
