package com.example.lenovo.day04.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter> extends AppCompatActivity{

    protected P basePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        ButterKnife.bind(this);
        basePresenter = initPresenter();
        if (basePresenter!=null){
            basePresenter.bind((V) this);
        }
        initView();
        initListener();
        initData();
    }

    protected abstract P initPresenter();

    protected void initView() {

    }

    protected void initListener() {

    }

    protected void initData() {

    }

    protected abstract int initLayoutId();
}
