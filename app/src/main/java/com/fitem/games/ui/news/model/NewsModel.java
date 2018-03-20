package com.fitem.games.ui.news.model;

import com.fitem.games.api.Api;
import com.fitem.games.api.ApiConstants;
import com.fitem.games.api.HostType;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.baserx.RxSchedulers;
import com.fitem.games.ui.news.bean.News;
import com.fitem.games.ui.news.contract.NewsContract;

import io.reactivex.Observable;

/**
 * Created by Fitem on 2018/3/20.
 */

public class NewsModel implements NewsContract.Model {
    @Override
    public Observable<News> getNews(int offset) {
        return Api.getDefault(HostType.HOST).getNewsList(ApiConstants.NEWS_GAMES_TYPE, offset, AppConstants.PAGE_SIZE)
                .compose(RxSchedulers.<News>io_main());
    }
}
