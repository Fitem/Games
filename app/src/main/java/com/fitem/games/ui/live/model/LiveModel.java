package com.fitem.games.ui.live.model;

import com.fitem.games.api.Api;
import com.fitem.games.api.HostType;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.baserx.RxSchedulers;
import com.fitem.games.ui.live.bean.LiveBase;
import com.fitem.games.ui.live.bean.LiveItem;
import com.fitem.games.ui.live.contract.LiveContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Fitem on 2018/3/21.
 */

public class LiveModel implements LiveContract.Model {
    @Override
    public Observable<List<LiveItem>> getLiveList(String gameType, int offset) {
        return Api.getDefault(HostType.LIVE_HOST).getLiveList(null, gameType, offset, AppConstants.PAGE_SIZE)
                .map(new Function<LiveBase<List<LiveItem>>, List<LiveItem>>() {
                    @Override
                    public List<LiveItem> apply(LiveBase<List<LiveItem>> listLiveBase) throws Exception {
                        return listLiveBase.getResult();
                    }
                })
                .compose(RxSchedulers.<List<LiveItem>>io_main());
    }
}
