package com.example.lenovo.day04.view.gank;

import com.example.lenovo.day04.base.BaseView;
import com.example.lenovo.day04.ui.gank.bean.WealBean;

public interface WealView extends BaseView {
    void onSuccess(WealBean wealBean);
    void onFail(String string);
}
