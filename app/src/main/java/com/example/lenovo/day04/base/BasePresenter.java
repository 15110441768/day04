package com.example.lenovo.day04.base;

public abstract class BasePresenter<V extends BaseView> {
    protected V view;

    public void bind(V view) {
        this.view=view;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

}
