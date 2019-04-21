package com.example.lenovo.day04.ui.zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.zhihu.SpecialPresenter;
import com.example.lenovo.day04.ui.zhihu.adapter.SpecialRecyclerViewAdapter;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialBean;
import com.example.lenovo.day04.view.zhihu.SpecialView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialFragment extends BaseFragment<SpecialView, SpecialPresenter> implements SpecialView {

    private static final String TAG = "SpecialFragment";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<SpecialBean.DataBean> dataBeans;
    private SpecialRecyclerViewAdapter specialRecyclerViewAdapter;

    public SpecialFragment() {
        // Required empty public constructor
    }

    @Override
    protected SpecialPresenter initPresenter() {
        return new SpecialPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        dataBeans = new ArrayList<>();
        specialRecyclerViewAdapter = new SpecialRecyclerViewAdapter(dataBeans, getActivity());
        recyclerview.setAdapter(specialRecyclerViewAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData();
    }

    @Override
    public void onSuccess(SpecialBean specialBean) {
        specialRecyclerViewAdapter.setData(specialBean);
    }

    @Override
    public void onFail(String string) {
        Log.e(TAG, "onFail: " + string);
    }
}
