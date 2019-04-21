package com.example.lenovo.day04.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.zhihu.HotPresenter;
import com.example.lenovo.day04.ui.zhihu.adapter.HotRecyclerViewAdapter;
import com.example.lenovo.day04.ui.zhihu.bean.HotBean;
import com.example.lenovo.day04.view.zhihu.HotView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<HotView, HotPresenter> implements HotView {

    private static final String TAG = "HotFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<HotBean.RecentBean> recentBeans;
    private HotRecyclerViewAdapter hotRecyclerViewAdapter;

    public HotFragment() {
        // Required empty public constructor
    }

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recentBeans = new ArrayList<>();
        hotRecyclerViewAdapter = new HotRecyclerViewAdapter(recentBeans, getActivity());
        recyclerview.setAdapter(hotRecyclerViewAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData();
    }

    @Override
    public void onSuccess(HotBean hotBean) {
        hotRecyclerViewAdapter.setData(hotBean);
    }

    @Override
    public void onFail(String string) {
        Log.e(TAG, "onFail: " + string);
    }
}
