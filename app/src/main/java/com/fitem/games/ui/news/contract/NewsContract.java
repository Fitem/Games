package com.fitem.games.ui.news.contract;

import com.fitem.games.common.base.BaseModel;
import com.fitem.games.common.base.BasePresenter;
import com.fitem.games.common.base.BaseView;
import com.fitem.games.ui.news.bean.GNewsDetail;
import com.fitem.games.ui.news.bean.News;

import io.reactivex.Observable;

/**
 * Created by Fitem on 2018/3/19.
 */

public interface NewsContract {
    interface Model extends BaseModel {
        Observable<News> getNews(int offset);

        Observable<GNewsDetail> getNewsDetails(String newsId);
    }

    interface View extends BaseView {
        void returnNews(News news);

        void returnNewsDetails(GNewsDetail gNewsDetail);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getNewsPresenter(int offset);

        public abstract void getGnewsDetailPresenter(String newsId);
    }
}
