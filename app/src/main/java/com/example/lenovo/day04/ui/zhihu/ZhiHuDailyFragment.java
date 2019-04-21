package com.example.lenovo.day04.ui.zhihu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.zhihu.ZhiHuDailyPresenter;
import com.example.lenovo.day04.ui.zhihu.adapter.ZhiHuViewPagerAdapter;
import com.example.lenovo.day04.ui.zhihu.fragment.DailyNewsFragment;
import com.example.lenovo.day04.ui.zhihu.fragment.HotFragment;
import com.example.lenovo.day04.ui.zhihu.fragment.SpecialFragment;
import com.example.lenovo.day04.view.zhihu.ZhiHuDailyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHuDailyFragment extends BaseFragment<ZhiHuDailyView, ZhiHuDailyPresenter> implements ZhiHuDailyView {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> viewpagerList;
    private ZhiHuViewPagerAdapter zhiHuViewPagerAdapter;

    @Override
    protected ZhiHuDailyPresenter initPresenter() {
        return new ZhiHuDailyPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_zhi_hu_daily;
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData();
    }

    @Override
    public void setData(String data) {

    }

    @Override
    protected void initView() {
        super.initView();
        tablayout.addTab(tablayout.newTab().setText(R.string.dailyNews));
        tablayout.addTab(tablayout.newTab().setText(R.string.special));
        tablayout.addTab(tablayout.newTab().setText(R.string.hot));
        viewpagerList = new ArrayList<>();
        viewpagerList.add(new DailyNewsFragment());
        viewpagerList.add(new SpecialFragment());
        viewpagerList.add(new HotFragment());
        zhiHuViewPagerAdapter = new ZhiHuViewPagerAdapter(getChildFragmentManager(), viewpagerList);
        viewpager.setAdapter(zhiHuViewPagerAdapter);

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
