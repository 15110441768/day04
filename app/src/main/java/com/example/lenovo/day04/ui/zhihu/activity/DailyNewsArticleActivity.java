package com.example.lenovo.day04.ui.zhihu.activity;

/**
 * 杨博钦
 */

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.HttpService;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsArticleBean;
import com.example.lenovo.day04.ui.zhihu.bean.Wbean;
import com.example.lenovo.day04.util.DbUtil;
import com.example.lenovo.day04.util.HtmlUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyNewsArticleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DailyNewsArticleActivit";

    private Toolbar toolBar;
    private CollapsingToolbarLayout ctl;
    private AppBarLayout appBar;
    private NestedScrollView nsv;
    private WebView webview;
    private int id;
    private ImageView img;
    private FloatingActionButton fab;
    private boolean isLike=false;
    private Wbean newWbean;
    DbUtil dbUtil = DbUtil.getDbUtil();
    private DailyNewsArticleBean queryOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_news_article);
        id = getIntent().getIntExtra("id", 0);
        initView();
        initToolbar();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.dailyNewsArticleUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        HttpService httpService = retrofit.create(HttpService.class);

        httpService.getDailyNewsArticleData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Wbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Wbean wbean) {
                        newWbean=wbean;

                        queryOne = dbUtil.queryOne(wbean.getTitle());
                        if (queryOne !=null){
                            isLike=true;
                            fab.setImageResource(R.mipmap.ic_toolbar_like_p);
                        }

                        ctl.setTitle(wbean.getTitle());
                        Glide.with(DailyNewsArticleActivity.this).load(wbean.getImage()).into(img);
                        WebSettings settings = webview.getSettings();
                        settings.setJavaScriptEnabled(true);
                        settings.setLoadWithOverviewMode(true);
                        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                        settings.setSupportZoom(true);
                        webview.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                view.loadUrl(url);
                                return true;
                            }
                        });
                        String htmlData = HtmlUtil.createHtmlData(wbean.getBody(), wbean.getCss(), (List<String>) wbean.getJs());
                        webview.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initToolbar() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
    }

    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        nsv = (NestedScrollView) findViewById(R.id.nsv);
        img = (ImageView) findViewById(R.id.img);

        //滑动偏移监听事件
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: " + verticalOffset);
            }
        });
        ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl);

        //标题必须在CollapsingToolbarLayout设置
        ctl.setTitle("");
        //扩张时候的title颜色
        ctl.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
        //收缩后显示title的颜色
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        webview = (WebView) findViewById(R.id.webview);

//        webview.loadUrl("http://news-at.zhihu.com/api/4/news/"+id);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                    if (isLike){
                        isLike=false;
                        fab.setImageResource(R.mipmap.ic_toolbar_like_n);
                        dbUtil.delete(queryOne);
                        Snackbar.make(v,"取消收藏",Snackbar.LENGTH_SHORT)
                                .setAction("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                                .show();
                    }else {
                        isLike=true;
                        fab.setImageResource(R.mipmap.ic_toolbar_like_p);
                        DailyNewsArticleBean dailyNewsArticleBean = new DailyNewsArticleBean();
                        dailyNewsArticleBean.setBody(newWbean.getBody());
                        dailyNewsArticleBean.setCss(newWbean.getCss().get(0));
                        dailyNewsArticleBean.setGa_prefix(newWbean.getGa_prefix());
                        dailyNewsArticleBean.setId(newWbean.getId());
                        dailyNewsArticleBean.setImage(newWbean.getImage());
                        dailyNewsArticleBean.setImage_source(newWbean.getImage_source());
                        dailyNewsArticleBean.setImages(newWbean.getImages().get(0));
                        dailyNewsArticleBean.setShare_url(newWbean.getShare_url());
                        dailyNewsArticleBean.setTitle(newWbean.getTitle());
                        dailyNewsArticleBean.setType(newWbean.getType());
                        DbUtil.getDbUtil().insert(dailyNewsArticleBean);
                        Snackbar.make(v,"收藏成功",Snackbar.LENGTH_SHORT)
                                .setAction("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                                .show();
                    }
                break;
        }
    }
}
