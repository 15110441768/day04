package com.example.lenovo.day04.base;

import com.example.lenovo.day04.ui.gank.bean.GankArticleBean;
import com.example.lenovo.day04.ui.gank.bean.WealBean;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsBean;
import com.example.lenovo.day04.ui.zhihu.bean.HotBean;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialBean;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialDetailsBean;
import com.example.lenovo.day04.ui.zhihu.bean.Wbean;

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

    String gankArticleUrl="http://gank.io/api/data/";
    @GET("{tech}/20/1")
    Observable<GankArticleBean> getGankArticleData(@Path("tech") String tech);

    String dailyNewsArticleUrl="http://news-at.zhihu.com/api/4/";
    @GET("news/{id}")
    Observable<Wbean> getDailyNewsArticleData(@Path("id") int id);

    String wealUrl="http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/10/{page}")
    Observable<WealBean> getWealData(@Path("page") int page);
}
