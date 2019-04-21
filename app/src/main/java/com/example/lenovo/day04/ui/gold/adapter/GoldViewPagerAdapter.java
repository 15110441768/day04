package com.example.lenovo.day04.ui.gold.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 *         生命周期不一样
 *         FragmentStatePagerAdapter:用不着的碎片生命周期,onDetach();取消关联
 *         FragmentPagerAdapter:用不着的碎片生命周期,只会走到onDestoryView();
 *
 */

public class GoldViewPagerAdapter extends FragmentStatePagerAdapter{
    private ArrayList<Fragment> fragments;
    public GoldViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
