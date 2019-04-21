package com.example.lenovo.day04.model.zhihu;

import com.example.lenovo.day04.base.BaseModel;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.base.HttpService;
import com.example.lenovo.day04.net.HttpUtils;
import com.example.lenovo.day04.net.RxUtils;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SpecialModel extends BaseModel {
    public void getData(final CallBack<SpecialBean> callBack) {
        HttpService httpService = HttpUtils.getInstance().getApiserver(HttpService.dailyNewsUrl, HttpService.class);
        httpService.getSpecialData().compose(RxUtils.<SpecialBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<SpecialBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpecialBean specialBean) {
                        callBack.onSuccess(specialBean);
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
