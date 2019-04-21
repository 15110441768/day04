package com.example.lenovo.day04.view.zhihu;

import com.example.lenovo.day04.base.BaseView;
import com.example.lenovo.day04.ui.zhihu.bean.HotBean;

public interface HotView extends BaseView {
    void onSuccess(HotBean hotBean);
    void onFail(String string);
}
