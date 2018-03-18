package com.fitem.games.common.helper;

import android.support.annotation.StringRes;

import com.blankj.utilcode.util.ToastUtils;

/**
 * Toast帮助类，统一管理
 * Created by Fitem on 2017/9/26.
 */

public class ToastHelper {

    public static void showShort(CharSequence text) {
        ToastUtils.showShort(text);
    }

    public static void showShort(@StringRes int resId) {
        ToastUtils.showShort(resId);
    }

    public static void showLong(CharSequence text) {
        ToastUtils.showLong(text);
    }

    public static void showLong(@StringRes int resId) {
        ToastUtils.showLong(resId);
    }
}
