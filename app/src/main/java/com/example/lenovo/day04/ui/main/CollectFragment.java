package com.example.lenovo.day04.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.MainPresenter;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsArticleBean;
import com.example.lenovo.day04.util.DbUtil;
import com.example.lenovo.day04.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends BaseFragment<MainView, MainPresenter> implements MainView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<DailyNewsArticleBean> recyclerviewList;
    private CollectAdapter collectAdapter;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewList = new ArrayList<>();
        collectAdapter = new CollectAdapter(recyclerviewList, getActivity());
        recyclerview.setAdapter(collectAdapter);
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        if (isVisibleToUser){
//            recyclerviewList.clear();
//            initData();
//        }
//    }

    @Override
    protected void initData() {
        List<DailyNewsArticleBean> dailyNewsArticleBeans = DbUtil.getDbUtil().queryAll();
        recyclerviewList.clear();
        recyclerviewList.addAll(dailyNewsArticleBeans);
        collectAdapter.notifyDataSetChanged();
    }

    private boolean isGetData = false;

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
            initData();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }
}
