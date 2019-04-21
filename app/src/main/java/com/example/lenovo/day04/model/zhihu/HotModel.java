package com.example.lenovo.day04.model.zhihu;

import com.example.lenovo.day04.base.BaseModel;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.base.HttpService;
import com.example.lenovo.day04.net.HttpUtils;
import com.example.lenovo.day04.net.RxUtils;
import com.example.lenovo.day04.ui.zhihu.bean.HotBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HotModel extends BaseModel {
    public void getData(final CallBack<HotBean> callBack) {
        HttpService httpService = HttpUtils.getInstance().getApiserver(HttpService.dailyNewsUrl, HttpService.class);
        httpService.getHotData().compose(RxUtils.<HotBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        callBack.onSuccess(hotBean);
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
