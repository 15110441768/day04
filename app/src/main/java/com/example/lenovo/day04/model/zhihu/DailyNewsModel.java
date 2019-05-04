package com.example.lenovo.day04.model.zhihu;

import com.example.lenovo.day04.base.BaseModel;
import com.example.lenovo.day04.base.CallBack;
import com.example.lenovo.day04.base.HttpService;
import com.example.lenovo.day04.net.HttpUtils;
import com.example.lenovo.day04.net.RxUtils;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyNewsModel extends BaseModel {

    public void getData(final CallBack<DailyNewsBean> callBack) {
        HttpService httpService = HttpUtils.getInstance().getApiserver(HttpService.dailyNewsUrl, HttpService.class);
        httpService.getDailyNewsData().compose(RxUtils.<DailyNewsBean>rxObservableSchedulerHelper())
                .subscribe(new Observer<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(HttpService.dailyNewsUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        HttpService httpService = retrofit.create(HttpService.class);
//
//        httpService.getDailyNewsData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<DailyNewsBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(DailyNewsBean dailyNewsBean) {
//                        callBack.onSuccess(dailyNewsBean);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callBack.onFail(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    public void getBeforeData(final CallBack<DailyNewsBean> callBack,String date) {
        int i = Integer.parseInt(date);
        HttpService httpService = HttpUtils.getInstance().getApiserver(HttpService.dailyNewsUrl, HttpService.class);
        httpService.getBeforeDailyNewsData(i+1).compose(RxUtils.<DailyNewsBean>rxObservableSchedulerHelper())
                .subscribe(new Observer<DailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
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
