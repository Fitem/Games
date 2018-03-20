package com.fitem.games.ui.grils.presenter;

import com.fitem.games.common.baserx.RxSubscriber;
import com.fitem.games.ui.grils.bean.Grils;
import com.fitem.games.ui.grils.contract.GrilsContract;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Fitem on 2018/3/21.
 */

public class GrilsPresenter extends GrilsContract.Presenter {
    @Override
    public void getGrilsListPresenter(int pg) {
        boolean isFirst = pg == 1;
        if (isFirst) mView.showLoading();
        mModel.getGrilsList(pg).subscribe(new RxSubscriber<List<Grils.ResultsBean>>(mContext, false) {
            @Override
            protected void _onNext(List<Grils.ResultsBean> resultsBeans) {
                mView.returnGrilsList(resultsBeans);
                mView.stopLoading();
            }

            @Override
            protected void _onError(int tag) {
                mView.showErrorTip(tag);
            }

            @Override
            public void onSubscribe(Disposable d) {
                mRxManage.add(d);
            }
        });
    }
}
