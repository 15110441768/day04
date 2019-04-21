package com.example.lenovo.day04.presenter.zhihu;

import com.example.lenovo.day04.base.BasePresenter;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.model.zhihu.HotModel;
import com.example.lenovo.day04.ui.zhihu.bean.HotBean;
import com.example.lenovo.day04.view.zhihu.HotView;

public class HotPresenter extends BasePresenter<HotView> {

    private HotModel hotModel;

    @Override
    protected void initModel() {
        hotModel = new HotModel();
    }

    public void getData() {
        hotModel.getData(new CallBack<HotBean>() {
            @Override
            public void onSuccess(HotBean hotBean) {
                if (hotBean!=null){
                    if (view!=null){
                        view.onSuccess(hotBean);
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
}
