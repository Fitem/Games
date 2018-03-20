package com.fitem.games.ui.news.model;

import android.support.v4.util.ArrayMap;

import com.fitem.games.api.Api;
import com.fitem.games.api.ApiConstants;
import com.fitem.games.api.HostType;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.baserx.RxSchedulers;
import com.fitem.games.ui.news.bean.GNewsDetail;
import com.fitem.games.ui.news.bean.News;
import com.fitem.games.ui.news.contract.NewsContract;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Fitem on 2018/3/20.
 */

public class NewsModel implements NewsContract.Model {
    @Override
    public Observable<News> getNews(int offset) {
        return Api.getDefault(HostType.HOST).getNewsList(ApiConstants.NEWS_GAMES_TYPE, offset, AppConstants.PAGE_SIZE)
                .compose(RxSchedulers.<News>io_main());
    }

    @Override
    public Observable<GNewsDetail> getNewsDetails(final String newsId) {
        return Api.getDefault(HostType.HOST).getNewsDetail(newsId).map(new Function<ArrayMap<String, GNewsDetail>, GNewsDetail>() {
            @Override
            public GNewsDetail apply(ArrayMap<String, GNewsDetail> map) throws Exception {
                GNewsDetail newsDetail = map.get(newsId);
                return newsDetail;
            }
        }).compose(RxSchedulers.<GNewsDetail>io_main());
    }
}
