package com.example.lenovo.day04.ui.gank.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.HttpService;
import com.example.lenovo.day04.ui.gank.adapter.GankArticleAdapter;
import com.example.lenovo.day04.ui.gank.bean.GankArticleBean;
import com.example.lenovo.day04.util.SystemUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {


    @BindView(R.id.iv_tech_blur)
    ImageView ivTechBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView ivTechOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView tvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    String mUrl = "https://ws1.sinaimg.cn/large/610dc034ly1fiednrydq8j20u011itfz.jpg";
    private ArrayList<GankArticleBean.ResultsBean> recyclerviewList;
    private GankArticleAdapter gankArticleAdapter;

    public ArticleFragment() {
        // Required empty public constructor
    }

    String tech;

    @SuppressLint("ValidFragment")
    public ArticleFragment(String tech) {
        this.tech = tech;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {
        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation());
        Glide.with(getContext()).load(mUrl).apply(options).into(ivTechBlur);
        Glide.with(getContext()).load(mUrl).into(ivTechOrigin);
        techAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //图片随着滑动变模糊,
                //有两张图片,后面一张是高斯模糊过的,前面一张是原图,
                // 随着滑动原图改变透明度实现上面的效果
                //透明度 0-1,0完全透明,1,完全不透明
                //verticalOffset *1.0f / SystemUtil.dp2px(256) -1到0
                //以两倍的速度减小透明度,可以更快的看到模糊效果
                float rate = 1 + verticalOffset * 2.0f / SystemUtil.dp2px(256);
                if (rate >= 0)
                    ivTechOrigin.setAlpha(rate);
            }
        });

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewList = new ArrayList<>();
        gankArticleAdapter = new GankArticleAdapter(recyclerviewList, getActivity());
        recyclerview.setAdapter(gankArticleAdapter);
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.gankArticleUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HttpService httpService = retrofit.create(HttpService.class);

        httpService.getGankArticleData(tech)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankArticleBean gankArticleBean) {
                        if (gankArticleBean.getResults()!=null&&gankArticleBean.getResults().size()>0){
                            recyclerviewList.addAll(gankArticleBean.getResults());
                            gankArticleAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
