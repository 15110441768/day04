package com.example.lenovo.day04.presenter.zhihu;

import com.example.lenovo.day04.base.BasePresenter;
import com.example.lenovo.day04.view.zhihu.ZhiHuDailyView;

public class ZhiHuDailyPresenter extends BasePresenter<ZhiHuDailyView> {
    @Override
    protected void initModel() {

    }

    public void getData() {
        String data="2";
        view.setData(data);
    }
}
