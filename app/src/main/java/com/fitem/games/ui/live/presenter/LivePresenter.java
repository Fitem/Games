package com.fitem.games.ui.live.presenter;

import com.fitem.games.common.baserx.RxSubscriber;
import com.fitem.games.ui.live.bean.LiveDetail;
import com.fitem.games.ui.live.bean.LiveItem;
import com.fitem.games.ui.live.contract.LiveContract;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Fitem on 2018/3/21.
 */

public class LivePresenter extends LiveContract.Presenter {
    @Override
    public void getLiveListPresenter(String gameType, int offset) {
        final boolean isFirst = offset == 0;
        if (isFirst) mView.showLoading();
        mModel.getLiveList(gameType, offset).subscribe(new RxSubscriber<List<LiveItem>>(mContext, false) {
            @Override
            protected void _onNext(List<LiveItem> list) {
                mView.returnLiveList(list);
                if (isFirst) mView.stopLoading();
            }

            @Override
            protected void _onError(int tag) {
                if (isFirst) {
                    mView.showErrorTip(tag);
                } else {
                    mView.returnLiveList(null);
                }
            }

            @Override
            public void onSubscribe(Disposable d) {
                mRxManage.add(d);
            }
        });
    }

    @Override
    public void getLiveDetailPresenter(String liveType, String liveId, String gameType) {
        mModel.getLiveDetail(liveType, liveId, gameType).subscribe(new RxSubscriber<LiveDetail>(mContext, false) {
            @Override
            protected void _onNext(LiveDetail detail) {
                mView.returnLiveDetail(detail);
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
