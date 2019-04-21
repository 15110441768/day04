package com.example.lenovo.day04.ui.v2ex;

/**
 *  杨博钦
 */

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
import com.example.lenovo.day04.presenter.v2ex.V2exPresenter;
import com.example.lenovo.day04.ui.gold.adapter.GoldViewPagerAdapter;
import com.example.lenovo.day04.ui.v2ex.activity.FadeActivity;
import com.example.lenovo.day04.ui.v2ex.bean.TabsBean;
import com.example.lenovo.day04.ui.v2ex.fragment.V2exArticleFragment;
import com.example.lenovo.day04.view.v2ex.V2exView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class V2exFragment extends BaseFragment<V2exView, V2exPresenter> implements V2exView {

    private static final String TAG = "V2exFragment";

    private String mUrl="https://www.v2ex.com/";

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<TabsBean> tabsBeans;
    private ArrayList<Fragment> fragments;
    private GoldViewPagerAdapter v2exViewPagerAdapter;

    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected void initView() {
        super.initView();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FadeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Element tabs = doc.select("div#Tabs").first();
                    Elements alltabs = tabs.select("a[href]");

                    tabsBeans = new ArrayList<>();

                    fragments = new ArrayList<>();

                    for (Element element:alltabs) {
                        // 获取href属性
                        String linkHref = element.attr("href");
                        // 获取标签里面的文本
                        String linkText = element.text();
//                        Log.e(TAG, "run: " + linkText+"            "+linkHref);
                        tabsBeans.add(new TabsBean(linkHref,linkText));
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < tabsBeans.size(); i++) {
                                String linkText = tabsBeans.get(i).getLinkText();
                                String linkHref = tabsBeans.get(i).getLinkHref();
                                tablayout.addTab(tablayout.newTab().setText(linkText));
                                fragments.add(new V2exArticleFragment(linkHref));
                            }

                            v2exViewPagerAdapter = new GoldViewPagerAdapter(getChildFragmentManager(), fragments);
                            viewpager.setAdapter(v2exViewPagerAdapter);

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
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
