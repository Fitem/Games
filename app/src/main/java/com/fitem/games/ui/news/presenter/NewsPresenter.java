package com.fitem.games.ui.news.presenter;

import com.fitem.games.common.baserx.RxSubscriber;
import com.fitem.games.ui.news.bean.News;
import com.fitem.games.ui.news.contract.NewsContract;

import io.reactivex.disposables.Disposable;

/**
 * Created by Fitem on 2018/3/19.
 */

public class NewsPresenter extends NewsContract.Presenter {
    @Override
    public void getNewsPresenter(int offset) {
        final boolean isFirst = offset == 0;
        if (isFirst) mView.showLoading();
        mModel.getNews(offset).subscribe(new RxSubscriber<News>(mContext, false) {
            @Override
            public void onSubscribe(Disposable d) {
                mRxManage.add(d);
            }

            @Override
            protected void _onNext(News news) {
                mView.returnNews(news);
                if (isFirst) mView.stopLoading();
            }

            @Override
            protected void _onError(int tag) {
                if (isFirst) mView.showErrorTip(tag);
            }
        });
    }
}
