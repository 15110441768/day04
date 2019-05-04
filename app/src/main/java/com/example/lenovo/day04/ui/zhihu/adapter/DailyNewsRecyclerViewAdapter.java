package com.example.lenovo.day04.ui.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.day04.R;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class DailyNewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<DailyNewsBean.StoriesBean> storiesBeans;
    private ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans;
    Context context;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    private String mDate = "今日要闻";

    public DailyNewsRecyclerViewAdapter(ArrayList<DailyNewsBean.StoriesBean> storiesBeans, ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans, Context context) {
        this.storiesBeans = storiesBeans;
        this.topStoriesBeans = topStoriesBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (i == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.item_banner, null);
            BannerVH bannerVH = new BannerVH(view);
            return bannerVH;
        } else if (i == TYPE_TIME) {
            View view = inflater.inflate(R.layout.item_time, null);
            TimeVH timeVH = new TimeVH(view);
            return timeVH;
        } else {
            View view = inflater.inflate(R.layout.item_news, null);
            NewsVH newsVH = new NewsVH(view);
            return newsVH;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof BannerVH) {
            BannerVH bannerVH = (BannerVH) viewHolder;
            bannerVH.banner.setImages(topStoriesBeans);
            bannerVH.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DailyNewsBean.TopStoriesBean topStoriesBean = (DailyNewsBean.TopStoriesBean) path;
                    Glide.with(context).load(topStoriesBean.getImage()).into(imageView);
                }
            });
            bannerVH.banner.start();
        }
        if (viewHolder instanceof TimeVH) {
            TimeVH timeVH = (TimeVH) viewHolder;
            timeVH.time.setText(mDate);
        }
        if (viewHolder instanceof NewsVH) {
            NewsVH newsVH = (NewsVH) viewHolder;
            int newPosition = i-1;
            if (topStoriesBeans.size()>0){
                newPosition=i-1-1;
            }
            final DailyNewsBean.StoriesBean storiesBean = storiesBeans.get(newPosition);
            newsVH.title.setText(storiesBean.getTitle());
            Glide.with(context).load(storiesBean.getImages().get(0)).into(newsVH.images);

            newsVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sendDataToFragment!=null){
                        sendDataToFragment.sendData(storiesBean);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (topStoriesBeans.size() > 0) {
            return storiesBeans.size() + 1 + 1;
        } else {
            return storiesBeans.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (topStoriesBeans.size() > 0) {
            if (position == 0) {
                return TYPE_BANNER;
            } else if (position == 1) {
                return TYPE_TIME;
            } else {
                return TYPE_NEWS;
            }
        } else {
            if (position == 0) {
                return TYPE_TIME;
            } else {
                return TYPE_NEWS;
            }
        }
    }

    public void setData(DailyNewsBean dailyNewsBean) {
        mDate=dailyNewsBean.getDate();
        List<DailyNewsBean.StoriesBean> stories = dailyNewsBean.getStories();
        List<DailyNewsBean.TopStoriesBean> top_stories = dailyNewsBean.getTop_stories();
        storiesBeans.clear();
        if (stories!=null&&stories.size()>0){
            storiesBeans.addAll(stories);
        }
        topStoriesBeans.clear();
        if (top_stories!=null&&top_stories.size()>0){
            topStoriesBeans.addAll(top_stories);
        }
        notifyDataSetChanged();
    }

    class BannerVH extends RecyclerView.ViewHolder {

        private Banner banner;

        public BannerVH(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    class TimeVH extends RecyclerView.ViewHolder {

        private TextView time;

        public TimeVH(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
        }
    }

    class NewsVH extends RecyclerView.ViewHolder {


        private ImageView images;
        private TextView title;

        public NewsVH(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.images);
            title = itemView.findViewById(R.id.title);
        }
    }

    SendDataToFragment sendDataToFragment;

    public void setSendDataToFragment(SendDataToFragment sendDataToFragment) {
        this.sendDataToFragment = sendDataToFragment;
    }

    public interface SendDataToFragment{
        void sendData(DailyNewsBean.StoriesBean storiesBean);
    }
}
