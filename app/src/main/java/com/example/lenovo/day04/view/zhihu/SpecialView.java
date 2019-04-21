package com.example.lenovo.day04.view.zhihu;

import com.example.lenovo.day04.base.BaseView;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialBean;

public interface SpecialView extends BaseView {
    void onSuccess(SpecialBean specialBean);
    void onFail(String string);
}
