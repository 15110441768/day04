package com.example.lenovo.day04.view.zhihu;

import com.example.lenovo.day04.base.BaseView;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsBean;

public interface DailyNewsView extends BaseView {
    void onSuccess(DailyNewsBean dailyNewsBean);

    void onFail(String string);
}
