package com.fitem.games.ui.live.contract;

import com.fitem.games.common.base.BaseModel;
import com.fitem.games.common.base.BasePresenter;
import com.fitem.games.common.base.BaseView;
import com.fitem.games.ui.live.bean.LiveDetail;
import com.fitem.games.ui.live.bean.LiveItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Fitem on 2018/3/21.
 */

public interface LiveContract {
    interface Model extends BaseModel {
        Observable<List<LiveItem>> getLiveList(String gameType,int offset);

        Observable<LiveDetail> getLiveDetail(String liveType, String liveId, String gameType);
    }

    interface View extends BaseView {
        void returnLiveList(List<LiveItem> list);

        void returnLiveDetail(LiveDetail detail);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getLiveListPresenter(String gameType, int offset);

        public abstract void getLiveDetailPresenter(String liveType, String liveId, String gameType);
    }
}
