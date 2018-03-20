package com.fitem.games.ui.news.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fitem.games.R;
import com.fitem.games.common.helper.GlideHelper;
import com.fitem.games.ui.news.bean.GNews;

import java.util.List;

/**
 * Created by Fitem on 2018/3/19.
 */

public class NewsAdapter extends BaseQuickAdapter<GNews, BaseViewHolder> {

    public NewsAdapter(int layoutResId, @Nullable List<GNews> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GNews item) {
        String url = item.getImgsrc();
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_date, item.getMtime());
        helper.setText(R.id.tv_source, item.getSource());
        ImageView picView = helper.getView(R.id.iv_pic);
        GlideHelper.loadNewsPic(mContext, url, picView);
    }
}
