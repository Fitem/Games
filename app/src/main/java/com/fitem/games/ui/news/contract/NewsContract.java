package com.fitem.games.ui.news.contract;

import com.fitem.games.common.base.BaseModel;
import com.fitem.games.common.base.BasePresenter;
import com.fitem.games.common.base.BaseView;
import com.fitem.games.ui.news.bean.News;

import io.reactivex.Observable;

/**
 * Created by Fitem on 2018/3/19.
 */

public interface NewsContract {
    interface Model extends BaseModel {
        Observable<News> getNews(int offset);
    }

    interface View extends BaseView {
        void returnNews(News news);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getNewsPresenter(int offset);
    }
}
