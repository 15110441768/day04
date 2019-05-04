package com.example.lenovo.day04.ui.main;

/**
 * 杨博钦
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.activity.MainActivity;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.base.Constants;
import com.example.lenovo.day04.presenter.MainPresenter;
import com.example.lenovo.day04.util.SpUtil;
import com.example.lenovo.day04.util.UIModeUtil;
import com.example.lenovo.day04.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment<MainView, MainPresenter> implements MainView {

    @BindView(R.id.sc)
    SwitchCompat sc;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initView() {
        //if (sp里面的模式是否为夜间){
        int mode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        if (mode == AppCompatDelegate.MODE_NIGHT_YES){
            sc.setChecked(true);
        }else {
            sc.setChecked(false);
        }
    }

    @Override
    protected void initListener() {
        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //切换模式,
                //切换夜间模式的时候,Activiyt会重新创建,新建的switchCompat默认是false,会
                //调用这个回调,去掉非人为的回调
                if (buttonView.isPressed()){
                    UIModeUtil.changeModeUI((MainActivity)getActivity());

                    //保存设置碎片的位置,再次进来之后直接显示设置Fragmnet
                    SpUtil.setParam(Constants.NIGHT_CURRENT_FRAG_POS,MainActivity.TYPE_SETTINGS);
                }
            }
        });
    }
}
