package com.fitem.games.common.base;

/**
 * des:baseview
 * Created by xsf
 * on 2016.07.11:53
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading();
    void stopLoading();
    void showErrorTip(int tag);
}
