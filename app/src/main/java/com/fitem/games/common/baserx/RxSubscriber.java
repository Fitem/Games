package com.fitem.games.common.baserx;

import android.app.Activity;
import android.content.Context;

import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.commonutils.NetWorkUtils;
import com.fitem.games.common.commonwidget.LoadingDialog;

import io.reactivex.Observer;

/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

public abstract class RxSubscriber<T> implements Observer<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
        if (showDialog)
            LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
    }

    public RxSubscriber(Context context) {
        this(context, context.getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, context.getString(R.string.loading), showDialog);
    }

    @Override
    public void onComplete() {
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (showDialog)
            LoadingDialog.cancelDialogForLoading();

        if (!NetWorkUtils.isNetConnected(mContext)) {       //网络
            _onError(AppConstants.NO_NET);
        } else if (e instanceof ServerException) {          //服务器
            _onError(AppConstants.SERVER_EXCEPTION);
        } else {                                            //其它
            _onError(AppConstants.OHTER_EXCEPTION);
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(int tag);

}
