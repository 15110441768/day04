package com.example.lenovo.day04.ui.zhihu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.base.Constants;
import com.example.lenovo.day04.presenter.zhihu.DailyNewsPresenter;
import com.example.lenovo.day04.ui.zhihu.CalendarActivity;
import com.example.lenovo.day04.ui.zhihu.activity.DailyNewsArticleActivity;
import com.example.lenovo.day04.ui.zhihu.adapter.DailyNewsRecyclerViewAdapter;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsBean;
import com.example.lenovo.day04.view.zhihu.DailyNewsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyNewsFragment extends BaseFragment<DailyNewsView, DailyNewsPresenter> implements DailyNewsView {

    private static final String TAG = "DailyNewsFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.fab_btn)
    FloatingActionButton fabBtn;

    private ArrayList<DailyNewsBean.StoriesBean> storiesBeans;
    private ArrayList<DailyNewsBean.TopStoriesBean> topStoriesBeans;
    private DailyNewsRecyclerViewAdapter dailyNewsRecyclerViewAdapter;
    private String date;
    private String extra;

    public DailyNewsFragment() {
        // Required empty public constructor
    }

    @Override
    protected DailyNewsPresenter initPresenter() {
        return new DailyNewsPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initView() {
        super.initView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        storiesBeans = new ArrayList<>();
        topStoriesBeans = new ArrayList<>();
        dailyNewsRecyclerViewAdapter = new DailyNewsRecyclerViewAdapter(storiesBeans, topStoriesBeans, getActivity());
        recyclerview.setAdapter(dailyNewsRecyclerViewAdapter);

        dailyNewsRecyclerViewAdapter.setSendDataToFragment(new DailyNewsRecyclerViewAdapter.SendDataToFragment() {
            @Override
            public void sendData(DailyNewsBean.StoriesBean storiesBean) {
                Intent intent = new Intent(getActivity(), DailyNewsArticleActivity.class);
                intent.putExtra("id",storiesBean.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        if (extra!=null){
            basePresenter.getBeforeData(extra);
        }else {
            basePresenter.getData();

        }
    }

    @Override
    public void onSuccess(DailyNewsBean dailyNewsBean) {
        date = dailyNewsBean.getDate();
        dailyNewsRecyclerViewAdapter.setData(dailyNewsBean);
    }

    @Override
    public void onFail(String string) {
        Log.e(TAG, "onFail: " + string);
    }

    @OnClick(R.id.fab_btn)
    public void onViewClicked() {
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                intent.putExtra(Constants.DATE,date);
                startActivityForResult(intent,200);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200&&resultCode==RESULT_OK){
            extra = data.getStringExtra(Constants.DATE);
            initData();
        }
    }
}
