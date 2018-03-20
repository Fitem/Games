package com.fitem.games.ui.grils.contract;

import com.fitem.games.common.base.BaseModel;
import com.fitem.games.common.base.BasePresenter;
import com.fitem.games.common.base.BaseView;
import com.fitem.games.ui.grils.bean.Grils;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Fitem on 2018/3/21.
 */

public interface GrilsContract {
    interface Model extends BaseModel {
        Observable<List<Grils.ResultsBean>> getGrilsList(int pg);
    }

    interface View extends BaseView {
        void returnGrilsList(List<Grils.ResultsBean> list);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getGrilsListPresenter(int pg);
    }
}
