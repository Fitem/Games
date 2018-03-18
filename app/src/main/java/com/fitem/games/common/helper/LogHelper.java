package com.fitem.games.common.helper;

import com.blankj.utilcode.util.LogUtils;
import com.fitem.games.BuildConfig;
import com.fitem.games.app.AppConstants;

/**
 * Created by Fitem on 2018/3/16.
 */

public class LogHelper {

    /* 设置log开关，仅在DEBUG模式下显示 */
    static {
        LogUtils.Config config = LogUtils.getConfig();
        config.setLogSwitch(BuildConfig.LOG_DEBUG) // 设置总开关
                .setGlobalTag(AppConstants.TAG);   // 设置全局TAG
    }

    public static void logd(String contents) {
        LogUtils.d(contents);
    }

    public static void logdTag(String tag, String contents) {
        LogUtils.dTag(tag, contents);
    }

    public static void loge(String contents) {
        LogUtils.e(contents);
    }
}
