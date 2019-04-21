package com.example.lenovo.day04.ui.wechat;

import android.support.v4.app.Fragment;

import com.example.lenovo.day04.R;
import com.example.lenovo.day04.base.BaseFragment;
import com.example.lenovo.day04.presenter.wechat.WeChatPresenter;
import com.example.lenovo.day04.presenter.zhihu.ZhiHuDailyPresenter;
import com.example.lenovo.day04.view.wechat.WeChatView;
import com.example.lenovo.day04.view.zhihu.ZhiHuDailyView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeChatFragment extends BaseFragment<WeChatView,WeChatPresenter>  implements  WeChatView {

    @Override
    protected WeChatPresenter initPresenter() {
        return new WeChatPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_we_chat;
    }

}
