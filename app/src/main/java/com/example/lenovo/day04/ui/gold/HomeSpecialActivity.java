package com.example.lenovo.day04.ui.gold;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseActivity;
import com.example.lenovo.day04.base.Constants;
import com.example.lenovo.day04.presenter.gold.HomeSpecialPresenter;
import com.example.lenovo.day04.ui.gold.adapter.HomeSpecialRecyclerViewAdapter;
import com.example.lenovo.day04.ui.gold.bean.GoldTitleBean;
import com.example.lenovo.day04.view.gold.HomeSpecialView;
import com.example.lenovo.day04.widget.SimpleItemTouchCallBack;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSpecialActivity extends BaseActivity<HomeSpecialView, HomeSpecialPresenter> implements HomeSpecialView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<GoldTitleBean> goldTitleBeans;
    private HomeSpecialRecyclerViewAdapter homeSpecialRecyclerViewAdapter;

    @Override
    protected HomeSpecialPresenter initPresenter() {
        return new HomeSpecialPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_home_special;
    }

    @Override
    protected void initView() {
        super.initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("首页特别展示");

        goldTitleBeans = (ArrayList<GoldTitleBean>) getIntent().getSerializableExtra(Constants.DATA);
        recyclerview.setLayoutManager(new LinearLayoutManager(HomeSpecialActivity.this));
        homeSpecialRecyclerViewAdapter = new HomeSpecialRecyclerViewAdapter(goldTitleBeans, HomeSpecialActivity.this);
        recyclerview.setAdapter(homeSpecialRecyclerViewAdapter);

        /**
         * homeSpecialRecyclerViewAdapter  RecyclerView 的适配器
         * recyclerview                    RecyclerView 控件
         */
        //拖拽移动和左滑删除
        SimpleItemTouchCallBack simpleItemTouchCallBack = new SimpleItemTouchCallBack(homeSpecialRecyclerViewAdapter);
        simpleItemTouchCallBack.setmSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleItemTouchCallBack);
        helper.attachToRecyclerView(recyclerview);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAct();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }

    private void finishAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,goldTitleBeans);
        setResult(RESULT_OK,intent);
        finish();
    }
}
