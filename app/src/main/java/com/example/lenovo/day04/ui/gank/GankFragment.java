package com.example.lenovo.day04.ui.gank;

/**
 * 杨博钦
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.gank.GankPresenter;
import com.example.lenovo.day04.ui.gank.fragment.ArticleFragment;
import com.example.lenovo.day04.ui.gank.fragment.WealFragment;
import com.example.lenovo.day04.ui.zhihu.adapter.ZhiHuViewPagerAdapter;
import com.example.lenovo.day04.view.gank.GankView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment<GankView, GankPresenter> implements GankView {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> viewpagerList;
    private ZhiHuViewPagerAdapter gankViewPagerAdapter;

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView() {
        super.initView();

        tablayout.addTab(tablayout.newTab().setText("ANDROID"));
        tablayout.addTab(tablayout.newTab().setText("IOS"));
        tablayout.addTab(tablayout.newTab().setText("前端"));
        tablayout.addTab(tablayout.newTab().setText("福利"));

        viewpagerList = new ArrayList<>();
        viewpagerList.add(new ArticleFragment("Android"));
        viewpagerList.add(new ArticleFragment("iOS"));
        viewpagerList.add(new ArticleFragment("前端"));
        viewpagerList.add(new WealFragment());

        gankViewPagerAdapter = new ZhiHuViewPagerAdapter(getChildFragmentManager(), viewpagerList);
        viewpager.setAdapter(gankViewPagerAdapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }
}
