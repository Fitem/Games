package com.fitem.games.ui.grils.activity;

import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseActivity;
import com.fitem.games.common.helper.GlideHelper;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;

/**
 * Created by Fitem on 2018/3/21.
 */

public class GrilsActivity extends BaseActivity {

    @BindView(R.id.photo_view)
    PhotoView photoView;
    private String url = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_grils;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initData();
        GlideHelper.loadGrilsDtlPic(this, url, photoView);
    }

    private void initData() {
            url = getIntent().getStringExtra(AppConstants.GRILS_URL);
    }
}
