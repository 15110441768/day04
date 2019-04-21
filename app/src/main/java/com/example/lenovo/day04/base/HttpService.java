package com.example.lenovo.day04.base;

import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsBean;
import com.example.lenovo.day04.ui.zhihu.bean.HotBean;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialBean;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialDetailsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HttpService {
    String dailyNewsUrl="http://news-at.zhihu.com/api/4/";
    @GET("news/latest")
    Observable<DailyNewsBean> getDailyNewsData();

    @GET("news/before/{date}")
    Observable<DailyNewsBean> getBeforeDailyNewsData(@Path("date") int date);

    @GET("sections")
    Observable<SpecialBean> getSpecialData();

    @GET("section/{id}")
    Observable<SpecialDetailsBean> getSpecialDetailsData(@Path("id") int id);

    @GET("news/hot")
    Observable<HotBean> getHotData();
}
