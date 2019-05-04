package com.example.lenovo.day04.presenter.gank;

import com.example.lenovo.day04.base.BasePresenter;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.model.gank.WealModel;
import com.example.lenovo.day04.ui.gank.bean.WealBean;
import com.example.lenovo.day04.view.gank.WealView;

public class WealPresenter extends BasePresenter<WealView> {

    private WealModel wealModel;

    @Override
    protected void initModel() {
        wealModel = new WealModel();
    }

    public void getWealData(int page) {
        wealModel.getWealData(new CallBack<WealBean>() {
            @Override
            public void onSuccess(WealBean wealBean) {
                if (wealBean!=null&&!wealBean.isError()){
                    if (view!=null){
                        view.onSuccess(wealBean);
                    }
                }else {
                    if (view!=null){
                        view.onFail("加载失败");
                    }
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFail(string);
                }
            }
        },page);
    }
}
