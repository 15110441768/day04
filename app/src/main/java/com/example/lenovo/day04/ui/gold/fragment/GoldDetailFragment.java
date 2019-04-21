package com.example.lenovo.day04.ui.gold.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.base.Constants;
import com.example.lenovo.day04.presenter.gold.GoldDetailPresenter;
import com.example.lenovo.day04.view.gold.GoldDetailView;
import com.example.lenovo.day04.view.gold.GoldView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldDetailFragment extends BaseFragment<GoldDetailView, GoldDetailPresenter> implements GoldView{

    private static final String TAG = "GoldDetailFragment";

    @BindView(R.id.tv)
    TextView tv;

    public static GoldDetailFragment newInstance(String text) {
        GoldDetailFragment goldDetailFragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, text);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }

    @Override
    protected GoldDetailPresenter initPresenter() {
        return new GoldDetailPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getArguments();
        String string = bundle.getString(Constants.DATA);
        tv.setText(string);
    }
}
