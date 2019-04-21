package com.example.lenovo.day04.ui.zhihu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseActivity;
import com.example.lenovo.day04.presenter.zhihu.SpecialDetailsPresenter;
import com.example.lenovo.day04.ui.zhihu.adapter.SpecialDetailsRecyclerViewAdapter;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialDetailsBean;
import com.example.lenovo.day04.view.zhihu.SpecialDetailsView;

import java.util.ArrayList;

import butterknife.BindView;

public class SpecialDetailsActivity extends BaseActivity<SpecialDetailsView, SpecialDetailsPresenter> implements SpecialDetailsView{

    private static final String TAG = "SpecialDetailsActivity";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<SpecialDetailsBean.StoriesBean> storiesBeans;
    private SpecialDetailsRecyclerViewAdapter specialDetailsRecyclerViewAdapter;
    private int id;

    @Override
    protected SpecialDetailsPresenter initPresenter() {
        return new SpecialDetailsPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_special_details;
    }

    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(name);

        recyclerview.setLayoutManager(new LinearLayoutManager(SpecialDetailsActivity.this));
        storiesBeans = new ArrayList<>();
        specialDetailsRecyclerViewAdapter = new SpecialDetailsRecyclerViewAdapter(storiesBeans, SpecialDetailsActivity.this);
        recyclerview.setAdapter(specialDetailsRecyclerViewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData(id);
    }

    @Override
    public void onSuccess(SpecialDetailsBean specialDetailsBean) {
        specialDetailsRecyclerViewAdapter.setData(specialDetailsBean);
    }

    @Override
    public void onFail(String string) {
        Log.e(TAG, "onFail: " + string);
    }
}
