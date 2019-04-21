package com.example.lenovo.day04.presenter.zhihu;

import com.example.lenovo.day04.base.BasePresenter;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.model.zhihu.DailyNewsModel;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsBean;
import com.example.lenovo.day04.view.zhihu.DailyNewsView;

public class DailyNewsPresenter extends BasePresenter<DailyNewsView> {

    private DailyNewsModel dailyNewsModel;

    @Override
    protected void initModel() {
        dailyNewsModel = new DailyNewsModel();
    }

    public void getData() {
        dailyNewsModel.getData(new CallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean dailyNewsBean) {
                if (dailyNewsBean!=null){
                    if (view!=null){
                        view.onSuccess(dailyNewsBean);
                    }
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFail(string);
                }
            }
        });
    }

    public void getBeforeData(String extra) {
        dailyNewsModel.getBeforeData(new CallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean dailyNewsBean) {
                if (dailyNewsBean!=null){
                    if (view!=null){
                        view.onSuccess(dailyNewsBean);
                    }
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFail(string);
                }
            }
        },extra);
    }
}
