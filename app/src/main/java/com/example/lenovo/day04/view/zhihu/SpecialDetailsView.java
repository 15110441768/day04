package com.example.lenovo.day04.view.zhihu;

import com.example.lenovo.day04.base.BaseView;
import com.example.lenovo.day04.ui.zhihu.bean.SpecialDetailsBean;

public interface SpecialDetailsView extends BaseView {
    void onSuccess(SpecialDetailsBean specialDetailsBean);
    void onFail(String string);
}
