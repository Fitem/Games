package com.fitem.games.ui.grils.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fitem.games.R;
import com.fitem.games.common.helper.GlideHelper;
import com.fitem.games.ui.grils.bean.Grils;

import java.util.List;

/**
 * Created by Fitem on 2018/3/21.
 */

public class GrilsAdapter extends BaseQuickAdapter<Grils.ResultsBean, BaseViewHolder> {
    public GrilsAdapter(int layoutResId, @Nullable List<Grils.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Grils.ResultsBean item) {
        ImageView picView = helper.getView(R.id.iv_pic);
        GlideHelper.loadGrilsPic(mContext, item.getUrl(), picView);
    }
}
