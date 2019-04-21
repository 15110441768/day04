package com.example.lenovo.day04.presenter.zhihu;

import com.example.lenovo.day04.base.BasePresenter;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.model.zhihu.SpecialModel;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialBean;
import com.example.lenovo.day04.view.zhihu.SpecialView;

public class SpecialPresenter extends BasePresenter<SpecialView> {

    private SpecialModel specialModel;

    @Override
    protected void initModel() {
        specialModel = new SpecialModel();
    }

    public void getData() {
        specialModel.getData(new CallBack<SpecialBean>() {
            @Override
            public void onSuccess(SpecialBean specialBean) {
                if (specialBean!=null){
                    if (view!=null){
                        view.onSuccess(specialBean);
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
