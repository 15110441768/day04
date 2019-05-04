package com.example.lenovo.day04.model.gank;

import com.example.lenovo.day04.base.BaseModel;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.base.HttpService;
import com.example.lenovo.day04.ui.gank.bean.WealBean;
import com.example.lenovo.day04.util.HttpUtils;
import com.example.lenovo.day04.util.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WealModel extends BaseModel {

    public void getWealData(final CallBack<WealBean> callBack, int page) {
        HttpService apiserver = HttpUtils.getInstance().getApiserver(HttpService.wealUrl, HttpService.class);
        apiserver.getWealData(page)
                .compose(RxUtils.<WealBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<WealBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WealBean wealBean) {
                        callBack.onSuccess(wealBean);
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
