package com.fitem.games.ui.news.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseActivity;
import com.fitem.games.common.commonwidget.LoadingTip;
import com.fitem.games.ui.news.bean.GNewsDetail;
import com.fitem.games.ui.news.bean.News;
import com.fitem.games.ui.news.contract.NewsContract;
import com.fitem.games.ui.news.model.NewsModel;
import com.fitem.games.ui.news.presenter.NewsPresenter;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Fitem on 2018/3/20.
 */

public class NewsDetailActivity extends BaseActivity<NewsPresenter, NewsModel> implements NewsContract.View {

    @BindView(R.id.tv_content)
    TextView contentView;
    @BindView(R.id.tv_title)
    TextView titleView;
    @BindView(R.id.loading_tip)
    LoadingTip loadingTip;

    private String newId = "";
    private String newsTitle = "";
    private GNewsDetail newsDetail;

    @OnClick(R.id.iv_back)
    public void toBack() {
        finish();
    }

    @OnClick(R.id.loading_tip)
    public void reload() {
        mPresenter.getGnewsDetailPresenter(newId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        initData();
        initTitle();
        reload();
    }

    private void initTitle() {
        titleView.setText(newsTitle);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newId = bundle.getString(AppConstants.NEWS_ID, "");
            newsTitle = bundle.getString(AppConstants.NEWS_TITLE, getString(R.string.news));
        }
    }

    @Override
    protected void onDestroy() {
        RichText.recycle();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        loadingTip.loading();
    }

    @Override
    public void stopLoading() {
        loadingTip.finish();
    }

    @Override
    public void showErrorTip(int tag) {
        if (tag == AppConstants.NO_NET) {
            loadingTip.netError();
        } else {
            loadingTip.sereverError();
        }
    }

    @Override
    public void returnNews(News news) {

    }

    @Override
    public void returnNewsDetails(GNewsDetail data) {
        newsDetail = data;
        // RichText
        RichText.initCacheDir(this);
        RichText.from(newsDetail.getBody()).into(contentView);
    }
}
