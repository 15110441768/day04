package com.example.lenovo.day04.ui.main;

import android.support.v4.app.Fragment;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.MainPresenter;
import com.example.lenovo.day04.view.MainView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment<MainView,MainPresenter> implements MainView{

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_about;
    }

}
