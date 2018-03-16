package com.fitem.games.app;

import com.blankj.utilcode.util.Utils;
import com.fitem.games.common.baseapp.BaseApplication;
import com.fitem.games.common.helper.I18NHelper;

/**
 * Created by Fitem on 2018/3/16.
 */

public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        // 设置本地化语言
        I18NHelper.setLocale(this);
        // Utils工具初始化
        Utils.init(this);
    }
}
