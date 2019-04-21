package com.example.lenovo.day04.model.zhihu;

import com.example.lenovo.day04.base.BaseModel;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.base.HttpService;
import com.example.lenovo.day04.net.HttpUtils;
import com.example.lenovo.day04.net.RxUtils;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialDetailsBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SpecialDetailsModel extends BaseModel {
    public void getData(final CallBack<SpecialDetailsBean> callBack, int id) {
        HttpService httpService = HttpUtils.getInstance().getApiserver(HttpService.dailyNewsUrl, HttpService.class);
        httpService.getSpecialDetailsData(id).compose(RxUtils.<SpecialDetailsBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<SpecialDetailsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpecialDetailsBean specialDetailsBean) {
                        callBack.onSuccess(specialDetailsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
