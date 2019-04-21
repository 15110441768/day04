package com.example.lenovo.day04.ui.gold;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.base.Constants;
import com.example.lenovo.day04.presenter.gold.GoldPresenter;
import com.example.lenovo.day04.ui.gold.adapter.GoldViewPagerAdapter;
import com.example.lenovo.day04.ui.gold.bean.GoldTitleBean;
import com.example.lenovo.day04.ui.gold.fragment.GoldDetailFragment;
import com.example.lenovo.day04.ui.zhihu.adapter.ZhiHuViewPagerAdapter;
import com.example.lenovo.day04.view.gold.GoldView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldFragment extends BaseFragment<GoldView, GoldPresenter> implements GoldView {

    private static final String TAG = "GoldFragment";

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<GoldTitleBean> goldTitleBeans;
    private ArrayList<Fragment> fragments;
    private ZhiHuViewPagerAdapter zhiHuViewPagerAdapter;
    private GoldViewPagerAdapter goldViewPagerAdapter;

    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitles();
        initFragments();
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeSpecialActivity.class);
                intent.putExtra(Constants.DATA,goldTitleBeans);
                startActivityForResult(intent,100);
            }
        });
    }

    private void initTitles() {
        goldTitleBeans = new ArrayList<>();
        goldTitleBeans.add(new GoldTitleBean(R.string.android,true));
        goldTitleBeans.add(new GoldTitleBean(R.string.ios,true));
        goldTitleBeans.add(new GoldTitleBean(R.string.leading,true));
        goldTitleBeans.add(new GoldTitleBean(R.string.after,true));
        goldTitleBeans.add(new GoldTitleBean(R.string.design,true));
        goldTitleBeans.add(new GoldTitleBean(R.string.product,true));
        goldTitleBeans.add(new GoldTitleBean(R.string.read,true));
        goldTitleBeans.add(new GoldTitleBean(R.string.resources,true));
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        tablayout.removeAllTabs();

        for (int i = 0; i < goldTitleBeans.size(); i++) {
            GoldTitleBean goldTitleBean = goldTitleBeans.get(i);
            if (goldTitleBean.isChecked){
                tablayout.addTab(tablayout.newTab().setText(getResources().getString(goldTitleBean.title)));
                fragments.add(GoldDetailFragment.newInstance(getResources().getString(goldTitleBean.title)));
            }
        }

//        zhiHuViewPagerAdapter = new ZhiHuViewPagerAdapter(getChildFragmentManager(), fragments);
//        viewpager.setAdapter(zhiHuViewPagerAdapter);

        goldViewPagerAdapter = new GoldViewPagerAdapter(getChildFragmentManager(), fragments);
        viewpager.setAdapter(goldViewPagerAdapter);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode==100&&resultCode== Activity.RESULT_OK){
                goldTitleBeans= (ArrayList<GoldTitleBean>) data.getSerializableExtra(Constants.DATA);
                initFragments();
            }
        }
    }
}
