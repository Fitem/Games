package com.fitem.games.common.helper;

import com.blankj.utilcode.util.NetworkUtils;

/**
 * 网络帮助类
 * Created by Fitem on 2017/7/17.
 */

public class NetworkHelper {

    /**
     * 判断是否有网络
     * @return
     */
    public static boolean isConnected() {
        return NetworkUtils.isConnected();
    }

    /**
     * 数据加载失败提示
     */
    public static void errorTip() {
        if(isConnected()) {
//            ToastHelper.showShort(R.string.loading_error);
        } else {
//            ToastHelper.showShort(R.string.no_network_tip);
        }
    }
}
