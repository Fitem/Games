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
        // Utils工具初始化
        Utils.init(this);
        // 设置本地化语言, 需要在Utils之后，因为用到LogUtils，否则会报：ExceptionInInitializerError
        I18NHelper.setLocale(this);
    }
}
