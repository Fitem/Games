package com.fitem.games.ui.news.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.baserx.RxSubscriber;
import com.fitem.games.ui.news.bean.GNewsDetail;
import com.fitem.games.ui.news.bean.News;
import com.fitem.games.ui.news.contract.NewsContract;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

    @Override
    public void getGnewsDetailPresenter(final String newsId) {
        mView.showLoading();
        mModel.getNewsDetails(newsId)
                .doOnNext(new Consumer<GNewsDetail>() {
                    @Override
                    public void accept(GNewsDetail gNewsDetail) throws Exception {
                        handleRichTextWithImg(gNewsDetail);
                    }
                })
                .subscribe(new RxSubscriber<GNewsDetail>(mContext, false) {
                    @Override
                    protected void _onNext(GNewsDetail gNewsDetail) {
                        mView.returnNewsDetails(gNewsDetail);
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

    /**
     * 处理富文本包含图片的情况
     *
     * @param data 原始数据
     */
    private void handleRichTextWithImg(GNewsDetail data) {
        List<GNewsDetail.ImgBean> list = data.getImg();
        if (list != null && !list.isEmpty()) {
            String body = data.getBody();
            for (GNewsDetail.ImgBean bean : list) {
                String ref = bean.getRef();
                String src = bean.getSrc();
                String img = AppConstants.HTML_IMG_TEMPALE.replace("http", src);
                body = body.replaceAll(ref, img);
            }
            data.setBody(body);
            LogUtils.d(body);
        }
    }
}
