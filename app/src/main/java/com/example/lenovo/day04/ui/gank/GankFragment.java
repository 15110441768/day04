package com.example.lenovo.day04.ui.gank;

import android.support.v4.app.Fragment;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.gank.GankPresenter;
import com.example.lenovo.day04.presenter.v2ex.V2exPresenter;
import com.example.lenovo.day04.view.gank.GankView;
import com.example.lenovo.day04.view.v2ex.V2exView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment<GankView,GankPresenter> implements GankView{

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_gank;
    }

}
