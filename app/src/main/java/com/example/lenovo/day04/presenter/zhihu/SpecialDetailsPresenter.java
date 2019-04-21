package com.example.lenovo.day04.presenter.zhihu;

import com.example.lenovo.day04.base.BasePresenter;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.model.zhihu.SpecialDetailsModel;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialDetailsBean;
import com.example.lenovo.day04.view.zhihu.SpecialDetailsView;

public class SpecialDetailsPresenter extends BasePresenter<SpecialDetailsView> {

    private SpecialDetailsModel specialDetailsModel;

    @Override
    protected void initModel() {
        specialDetailsModel = new SpecialDetailsModel();
    }

    public void getData(int id) {
        specialDetailsModel.getData(new CallBack<SpecialDetailsBean>() {
            @Override
            public void onSuccess(SpecialDetailsBean specialDetailsBean) {
                if (specialDetailsBean!=null){
                    if (view!=null){
                        view.onSuccess(specialDetailsBean);
                    }
                }
            }

            @Override
            public void onFail(String string) {
                if (view!=null){
                    view.onFail(string);
                }
            }
        },id);
    }
}
