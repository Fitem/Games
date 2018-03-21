package com.fitem.games.ui.grils.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseActivity;
import com.fitem.games.common.helper.GlideHelper;
import com.fitem.games.common.utils.MyUtils;
import com.fitem.games.common.utils.SystemUiVisibilityUtil;
import com.fitem.games.common.widget.PullBackLayout;
import com.fitem.games.common.widget.StatusBarCompat;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Fitem on 2018/3/21.
 */

public class GrilsActivity extends BaseActivity implements PullBackLayout.Callback {

    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.pull_back_layout)
    PullBackLayout pullBackLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView titleView;
    @BindView(R.id.rl_title)
    RelativeLayout titleLayout;

    private static final int BACKGROUND_COLOR = Color.BLACK;
    private String url = "";
    private boolean mIsStatusBarHidden;
    private ColorDrawable mBackground;
    private boolean mIsToolBarHidden;

    @OnClick(R.id.iv_back)
    public void back() {
        supportFinishAfterTransition();
    }

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
        initBackground();
        initTitle();
        toolBarFadeIn();
        GlideHelper.loadGrilsDtlPic(this, url, photoView);
        pullBackLayout.setCallback(this);
    }

    private void initTitle() {
        titleView.setText(R.string.grils);
        titleLayout.setBackground(new ColorDrawable(BACKGROUND_COLOR));
    }

    private void initBackground() {
        mBackground = new ColorDrawable(BACKGROUND_COLOR);
        MyUtils.getRootView(this).setBackgroundDrawable(mBackground);
    }

    @Override
    protected void SetStatusBar() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.black_gray));
    }

    private void initData() {
        url = getIntent().getStringExtra(AppConstants.GRILS_URL);
    }

    protected void hideOrShowToolbar() {
        toolbar.animate()
                .alpha(mIsToolBarHidden ? 1.0f : 0.0f)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsToolBarHidden = !mIsToolBarHidden;
    }

    private void hideOrShowStatusBar() {
        if (mIsStatusBarHidden) {
            SystemUiVisibilityUtil.enter(this);
        } else {
            SystemUiVisibilityUtil.exit(this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;
    }

    private void toolBarFadeIn() {
        mIsToolBarHidden = true;
        hideOrShowToolbar();
    }

    private void toolBarFadeOut() {
        mIsToolBarHidden = false;
        hideOrShowToolbar();
    }

    @Override
    public void onPullStart() {
        toolBarFadeOut();
        mIsStatusBarHidden = true;
        hideOrShowStatusBar();
    }

    @Override
    public void onPull(float progress) {
        progress = Math.min(1f, progress * 3f);
        mBackground.setAlpha((int) (0xff/*255*/ * (1f - progress)));
    }

    @Override
    public void onPullCancel() {
        toolBarFadeIn();
    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
    }
}
