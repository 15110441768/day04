package com.example.lenovo.day04.ui.gank.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseApp;
import com.example.lenovo.day04.ui.gank.bean.WealBean;
import com.example.lenovo.day04.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class WealAdapter extends RecyclerView.Adapter<WealAdapter.ViewHolder> {
    private ArrayList<WealBean.ResultsBean> recyclerviewList;
    Context context;

    public WealAdapter(ArrayList<WealBean.ResultsBean> recyclerviewList, Context context) {
        this.recyclerviewList = recyclerviewList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_weal_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WealBean.ResultsBean resultsBean = recyclerviewList.get(i);

        //设置图片之前需要重新设置ImageView的宽高
        // imageWidth 根据屏幕的宽算出图片的宽
        int imageWidth = BaseApp.mWidthPixels / 2 - SystemUtil.dp2px(8);

        // LinearLayout.LayoutParams  适配器的item条目的最外层布局是什么LayoutParams前面就是什么
        // layoutParams 可以得到图片的宽高
        LinearLayout.LayoutParams layoutParams =
                (LinearLayout.LayoutParams) viewHolder.url.getLayoutParams();

        // 图片的宽
        layoutParams.width = imageWidth;

        if (resultsBean.getScale()!=0){
            layoutParams.height = (int) (imageWidth/resultsBean.getScale());
        }
        layoutParams.leftMargin = layoutParams.topMargin = SystemUtil.dp2px(4);
        viewHolder.url.setLayoutParams(layoutParams);

        // 给图片设置圆角
        RequestOptions options = new RequestOptions().transform(new RoundedCornersTransformation(SystemUtil.dp2px(6), 0));
        Glide.with(context).load(resultsBean.getUrl()).apply(options).into(viewHolder.url);
    }

    public void setData(WealBean wealBean) {
        List<WealBean.ResultsBean> results = wealBean.getResults();
        recyclerviewList.addAll(results);
        setImageScale();
    }

    //计算图片的宽高比
    private void setImageScale() {
        for (final WealBean.ResultsBean bean : recyclerviewList) {
            if (bean.getScale() == 0) {
                Glide.with(context).load(bean.getUrl()).into(
                        new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource,
                                                        @Nullable Transition<? super Drawable> transition) {
                                //宽高比
                                float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
                                bean.setScale(scale);
                                notifyDataSetChanged();
                            }
                        });
            } else {
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getItemCount() {
        return recyclerviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            url=itemView.findViewById(R.id.url);
        }
    }
}
