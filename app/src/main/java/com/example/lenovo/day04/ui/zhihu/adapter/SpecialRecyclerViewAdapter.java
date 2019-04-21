package com.example.lenovo.day04.ui.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.zhihu.SpecialDetailsActivity;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialBean;

import java.util.ArrayList;

public class SpecialRecyclerViewAdapter extends RecyclerView.Adapter<SpecialRecyclerViewAdapter.ViewHolder> {
    private ArrayList<SpecialBean.DataBean> dataBeans;
    Context context;
    private Intent intent;

    public SpecialRecyclerViewAdapter(ArrayList<SpecialBean.DataBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        intent = new Intent(context, SpecialDetailsActivity.class);
        View view = LayoutInflater.from(context).inflate(R.layout.item_special, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final SpecialBean.DataBean dataBean = dataBeans.get(i);
        RoundedCorners roundedCorners = new RoundedCorners(20);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(context).load(dataBean.getThumbnail()).apply(requestOptions).into(viewHolder.thumbnail);
        viewHolder.name.setText(dataBean.getName());
        viewHolder.description.setText(dataBean.getDescription());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id",dataBean.getId());
                intent.putExtra("name",dataBean.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public void setData(SpecialBean specialBean) {
        dataBeans.clear();
        if (specialBean.getData()!=null&&specialBean.getData().size()>0){
            dataBeans.addAll(specialBean.getData());
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;
        private TextView description;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail=itemView.findViewById(R.id.thumbnail);
            description=itemView.findViewById(R.id.description);
            name=itemView.findViewById(R.id.name);
        }
    }
}
