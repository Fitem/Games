package com.fitem.games.ui.main.activity;

import com.fitem.games.common.base.BaseActivity;

/**
 * Created by Fitem on 2018/3/18.
 */

public class SplashActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        startActivity(MainActivity.class);
    }
}
