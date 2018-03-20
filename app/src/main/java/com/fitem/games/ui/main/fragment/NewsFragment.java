package com.fitem.games.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseFragment;
import com.fitem.games.common.commonwidget.DividerItemDecoration;
import com.fitem.games.common.commonwidget.LoadingTip;
import com.fitem.games.ui.news.activity.NewsDetailActivity;
import com.fitem.games.ui.news.adapter.NewsAdapter;
import com.fitem.games.ui.news.bean.GNews;
import com.fitem.games.ui.news.bean.GNewsDetail;
import com.fitem.games.ui.news.bean.News;
import com.fitem.games.ui.news.contract.NewsContract;
import com.fitem.games.ui.news.model.NewsModel;
import com.fitem.games.ui.news.presenter.NewsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Fitem on 2018/3/18.
 */

public class NewsFragment extends BaseFragment<NewsPresenter, NewsModel> implements SwipeRefreshLayout.OnRefreshListener, NewsContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_title)
    TextView titleView;
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.loading_tip)
    LoadingTip loadingTip;

    private List<GNews> GNewsList;
    private NewsAdapter newsAdapter;
    private int offset;

    @OnClick(R.id.loading_tip)
    public void reload() {
        lazyFetchData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_news;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected boolean isHideFragment() {
        return true;
    }

    @Override
    protected void initView() {
        initTitle();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        initNewsAdapter();
    }

    private void initTitle() {
        titleView.setText(R.string.news);
    }

    private void initNewsAdapter() {
        newsAdapter = new NewsAdapter(R.layout.news_item, GNewsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        newsAdapter.setOnLoadMoreListener(this, recyclerView);
        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GNews news = newsAdapter.getData().get(position);
                String title = news.getTitle();
                String docid = news.getDocid();
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.NEWS_ID, docid);
                bundle.putString(AppConstants.NEWS_TITLE, title);
                startActivity(NewsDetailActivity.class, bundle);
            }
        });
    }

    @Override
    protected void lazyFetchData() {
        offset = 0;
        mPresenter.getNewsPresenter(offset);
    }

    @Override
    public void onRefresh() {
        newsAdapter.setEnableLoadMore(false);
        lazyFetchData();
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
        swipeRefreshLayout.setEnabled(true);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        newsAdapter.setEnableLoadMore(true);
        List<GNews> list = news.getgNewsList();
        boolean isFirst = offset == 0;
        if (list == null) {

        } else if (isFirst) {
            newsAdapter.setNewData(list);
        } else {
            newsAdapter.addData(list);
        }

        if (list == null) {
            newsAdapter.loadMoreFail();
        } else if (list.size() < AppConstants.PAGE_SIZE) {
            newsAdapter.loadMoreEnd();
        } else {
            newsAdapter.loadMoreComplete();
            offset = newsAdapter.getData().size();
        }
    }

    @Override
    public void returnNewsDetails(GNewsDetail gNewsDetail) {

    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setEnabled(false);
        mPresenter.getNewsPresenter(offset);
    }
}
