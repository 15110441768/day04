package com.example.lenovo.day04.ui.main;

import android.support.v4.app.Fragment;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.MainPresenter;
import com.example.lenovo.day04.presenter.v2ex.V2exPresenter;
import com.example.lenovo.day04.view.MainView;
import com.example.lenovo.day04.view.v2ex.V2exView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends BaseFragment<MainView,MainPresenter> implements MainView{

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_collect;
    }

}
