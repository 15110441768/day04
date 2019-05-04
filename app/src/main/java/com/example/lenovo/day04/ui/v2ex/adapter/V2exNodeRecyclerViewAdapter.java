package com.example.lenovo.day04.ui.v2ex.adapter;

/**
 * 杨博钦
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.v2ex.bean.NodeBean;
import com.example.lenovo.day04.util.ToastUtil;
import com.example.lenovo.day04.widget.FlowLayout;

import java.util.ArrayList;

public class V2exNodeRecyclerViewAdapter extends RecyclerView.Adapter<V2exNodeRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "V2exNodeRecyclerViewAda";

    private ArrayList<NodeBean> recyclerviewList;
    Context context;

    public V2exNodeRecyclerViewAdapter(ArrayList<NodeBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;
        Log.d("TAG+", "V2exNodeRecyclerViewAdapter: " + recyclerviewList.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_v2ex_node_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NodeBean nodeBean = recyclerviewList.get(i);
        Log.e(TAG, "onBindViewHolder: " + recyclerviewList.size());
        ArrayList<String> list = nodeBean.getList();
        viewHolder.fl.removeAllViews();
        for (int j = 0; j < list.size(); j++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            TextView label = (TextView) View.inflate(context, R.layout.item_label, null);
            //获取数据
            final String data = list.get(j);
            //绑定数据
            label.setText(data);

            label.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast(data);
                }
            });

            //加到容器中,parent是FlowLayout
            viewHolder.fl.addView(label);
        }

    }

    private void showToast(String data) {
        ToastUtil.showShort(data);
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FlowLayout fl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.fl);
        }
    }
}
