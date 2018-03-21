package com.fitem.games.ui.live.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fitem.games.R;
import com.fitem.games.common.helper.GlideHelper;
import com.fitem.games.ui.live.bean.LiveItem;

import java.util.List;

/**
 * Created by Fitem on 2018/3/21.
 */

public class LiveListAdapter extends BaseQuickAdapter<LiveItem, BaseViewHolder> {
    public LiveListAdapter(int layoutResId, @Nullable List<LiveItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveItem item) {
        helper.setText(R.id.tv_title, item.getLive_title());
        helper.setText(R.id.tv_liver_name, item.getLive_nickname());
        helper.setText(R.id.tv_people_num, String.valueOf(item.getLive_online()));
        ImageView liveImgView = helper.getView(R.id.iv_pic);
        ImageView userImgView = helper.getView(R.id.iv_user_pic);
        GlideHelper.loadCirclePic(mContext, item.getLive_userimg(), userImgView, R.drawable.ic_avatar_default);
        GlideHelper.loadPic(mContext, item.getLive_img(), liveImgView, R.drawable.ic_live_placeholder);
    }
}
