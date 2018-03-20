package com.fitem.games.ui.grils.model;

import com.fitem.games.api.Api;
import com.fitem.games.api.HostType;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.baserx.RxSchedulers;
import com.fitem.games.ui.grils.bean.Grils;
import com.fitem.games.ui.grils.contract.GrilsContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Fitem on 2018/3/21.
 */

public class GrilsModel implements GrilsContract.Model{
    @Override
    public Observable<List<Grils.ResultsBean>> getGrilsList(int pg) {
        return Api.getDefault(HostType.GRILS_HOST).getGrilsPic(AppConstants.PAGE_SIZE, pg)
                .map(new Function<Grils, List<Grils.ResultsBean>>() {
                    @Override
                    public List<Grils.ResultsBean> apply(Grils grils) throws Exception {
                        return grils.getResults();
                    }
                }).compose(RxSchedulers.<List<Grils.ResultsBean>>io_main());
    }
}
