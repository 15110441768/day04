package com.example.lenovo.day04.ui.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.lenovo.day04.base.Constants;

import java.util.ArrayList;

public class ZhiHuViewPagerAdapter extends FragmentPagerAdapter{
    private static final String TAG = "ZhiHuViewPagerAdapter";

    private ArrayList<Fragment> viewpagerList;
    public ZhiHuViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> viewpagerList) {
        super(fm);
        this.viewpagerList=viewpagerList;

    }

    @Override
    public Fragment getItem(int i) {
        return viewpagerList.get(i);
    }

    @Override
    public int getCount() {
        return viewpagerList.size();
    }
}
