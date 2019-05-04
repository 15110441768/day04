package com.example.lenovo.day04.ui.gank.fragment;

/**
 * 杨博钦
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.base.BaseView;
import com.example.lenovo.day04.presenter.gank.WealPresenter;
import com.example.lenovo.day04.ui.gank.adapter.WealAdapter;
import com.example.lenovo.day04.ui.gank.bean.WealBean;
import com.example.lenovo.day04.view.gank.WealView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WealFragment extends BaseFragment<WealView, WealPresenter> implements WealView{

    private static final String TAG = "WealFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<WealBean.ResultsBean> recyclerviewList;
    private WealAdapter wealAdapter;
    int page=1;

    public WealFragment() {
        // Required empty public constructor
    }

    @Override
    protected WealPresenter initPresenter() {
        return new WealPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_weal;
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止图片在上下滑动的时候移动.
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerview.setLayoutManager(manager);

        recyclerviewList = new ArrayList<>();
        wealAdapter = new WealAdapter(recyclerviewList, getActivity());
        recyclerview.setAdapter(wealAdapter);

        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                recyclerviewList.clear();
                initData();
            }
        });
    }

    @Override
    protected void initData() {
        basePresenter.getWealData(page);
    }

    @Override
    public void onSuccess(WealBean wealBean) {
        wealAdapter.setData(wealBean);
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }

    @Override
    public void onFail(String string) {
        Log.e(TAG, "onFail: " + string);
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
    }
}
