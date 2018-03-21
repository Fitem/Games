package com.fitem.games.ui.live.contract;

import com.fitem.games.common.base.BaseModel;
import com.fitem.games.common.base.BasePresenter;
import com.fitem.games.common.base.BaseView;
import com.fitem.games.ui.live.bean.LiveItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Fitem on 2018/3/21.
 */

public interface LiveContract {
    interface Model extends BaseModel {
        Observable<List<LiveItem>> getLiveList(String gameType,int offset);
    }

    interface View extends BaseView {
        void returnLiveList(List<LiveItem> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getLiveListPresenter(String gameType, int offset);
    }
}
