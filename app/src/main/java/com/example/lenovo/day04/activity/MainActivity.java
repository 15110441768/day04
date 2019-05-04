package com.example.lenovo.day04.activity;

/**
 * 杨博钦
 */

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseActivity;
import com.example.lenovo.day04.base.Constants;
import com.example.lenovo.day04.presenter.MainPresenter;
import com.example.lenovo.day04.ui.gank.GankFragment;
import com.example.lenovo.day04.ui.gold.GoldFragment;
import com.example.lenovo.day04.ui.main.AboutFragment;
import com.example.lenovo.day04.ui.main.CollectFragment;
import com.example.lenovo.day04.ui.main.SettingsFragment;
import com.example.lenovo.day04.ui.v2ex.V2exFragment;
import com.example.lenovo.day04.ui.wechat.WeChatFragment;
import com.example.lenovo.day04.ui.zhihu.ZhiHuDailyFragment;
import com.example.lenovo.day04.util.SpUtil;
import com.example.lenovo.day04.util.ToastUtil;
import com.example.lenovo.day04.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    private static final String TAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private FragmentManager manager;
    private ArrayList<Integer> titles;
    private ArrayList<Fragment> fragments;

    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_V2EX = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_GANK = 4;
    private final int TYPE_COLLECT = 5;
    public static final int TYPE_SETTINGS = 6;
    private final int TYPE_ABOUT = 7;

    private int lastFragmentPosition = 0;
    private MenuItem mSearchItem;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initView() {
        super.initView();
        manager = getSupportFragmentManager();

        initToolbar();
        initTitles();
        initFragments();
        addZhiHuDailyFragment();
        initNavigationView();
    }

    private void addZhiHuDailyFragment() {
        //根据保存的碎片位置显示对应碎片
        int type = (int) SpUtil.getParam(Constants.NIGHT_CURRENT_FRAG_POS, TYPE_ZHIHU);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, fragments.get(type));
        transaction.commit();
        toolbar.setTitle(titles.get(type));
        nv.setCheckedItem(R.id.zhihu);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new ZhiHuDailyFragment());
        fragments.add(new WeChatFragment());
        fragments.add(new V2exFragment());
        fragments.add(new GoldFragment());
        fragments.add(new GankFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingsFragment());
        fragments.add(new AboutFragment());
    }

    private void initTitles() {
        titles = new ArrayList<>();
        titles.add(R.string.zhihu);
        titles.add(R.string.wechat);
        titles.add(R.string.v2ex);
        titles.add(R.string.gold);
        titles.add(R.string.gank);
        titles.add(R.string.collect);
        titles.add(R.string.settings);
        titles.add(R.string.about);
    }

    private void initNavigationView() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.zhihu:
                        switchFragments(TYPE_ZHIHU);
                        break;
                    case R.id.wechat:
                        switchFragments(TYPE_WECHAT);
                        break;
                    case R.id.gank:
                        switchFragments(TYPE_GANK);
                        break;
                    case R.id.gold:
                        switchFragments(TYPE_GOLD);
                        break;
                    case R.id.v2ex:
                        switchFragments(TYPE_V2EX);
                        break;
                    case R.id.collect:
                        switchFragments(TYPE_COLLECT);
                        break;
                    case R.id.settings:
                        switchFragments(TYPE_SETTINGS);
                        break;
                    case R.id.about:
                        switchFragments(TYPE_ABOUT);
                        break;
                }
                dl.closeDrawer(Gravity.LEFT);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交搜索内容时的监听
                //ToastUtil.showShort("提交的内容:"+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //文本发生改变的监听
                //ToastUtil.showShort(newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展开
                ToastUtil.showShort("展开");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框关闭
                ToastUtil.showShort("关闭");
            }
        });

        //显示提示信息
        //searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
    }

    private void switchFragments(int type) {
        Fragment fragment = fragments.get(type);

        Fragment fragment1 = fragments.get(lastFragmentPosition);

        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.frame, fragment);
        }
        transaction.hide(fragment1);
        toolbar.setTitle(titles.get(type));
        transaction.show(fragment);
        transaction.commit();

        lastFragmentPosition = type;

        //显示隐藏搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK) {
            mSearchItem.setVisible(true);
        } else {
            mSearchItem.setVisible(false);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void initToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, dl, toolbar, R.string.app_name, R.string.app_name);
        //设置旋转开关颜色
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.c_ffffff));
        dl.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        mSearchItem = menu.findItem(R.id.action_search);
        mSearchItem.setVisible(false);
        searchView.setMenuItem(mSearchItem);
        return true;
    }

    /**
     * 按回退键会调用这个方法
     */
    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
//            super.onBackPressed();
            new AlertDialog.Builder(this)
                    .setMessage("是否退出应用")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS, TYPE_ZHIHU);
                            finish();
                        }
                    }).setNegativeButton("否", null)
                    .show();

            SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS, TYPE_ZHIHU);
        }


        Log.e(TAG, "onBackPressed: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
