package com.fitem.games.ui.live.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseFragment;
import com.fitem.games.common.widget.LoadingTip;
import com.fitem.games.ui.live.adapter.LiveListAdapter;
import com.fitem.games.ui.live.bean.LiveItem;
import com.fitem.games.ui.live.contract.LiveContract;
import com.fitem.games.ui.live.model.LiveModel;
import com.fitem.games.ui.live.presenter.LivePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Fitem on 2018/3/21.
 */

public class LiveItemFragment extends BaseFragment<LivePresenter, LiveModel> implements LiveContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.loading_tip)
    LoadingTip loadingTip;
    private int offset;
    private List<LiveItem> list;
    private LiveListAdapter liveListAdapter;
    private String gType = AppConstants.LOL;

    @OnClick(R.id.loading_tip)
    public void reload() {
        lazyFetchData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_live_item;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void lazyFetchData() {
        offset = 0;
        mPresenter.getLiveListPresenter(gType, offset);
    }

    @Override
    protected void initView() {
        initData();
        initAdapter();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        Bundle bundle = getArguments();
        gType = bundle.getString(AppConstants.GAME_TYPE, AppConstants.LOL);
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        liveListAdapter = new LiveListAdapter(R.layout.live_list_item, list);
        recyclerView.setAdapter(liveListAdapter);
        liveListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        liveListAdapter.setOnLoadMoreListener(this, recyclerView);
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
    public void returnLiveList(List<LiveItem> list) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        swipeRefreshLayout.setEnabled(true);
        liveListAdapter.setEnableLoadMore(true);
        boolean isFirst = offset == 0;
        if (list == null) {

        } else if (isFirst) {
            liveListAdapter.setNewData(list);
        } else {
            liveListAdapter.addData(list);
        }

        if (list == null) {
            liveListAdapter.loadMoreFail();
        } else if (list.size() < AppConstants.PAGE_SIZE) {
            liveListAdapter.loadMoreEnd();
        } else {
            liveListAdapter.loadMoreComplete();
            offset = liveListAdapter.getData().size();
        }
    }

    @Override
    public void onRefresh() {
        liveListAdapter.setEnableLoadMore(false);
        lazyFetchData();
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setEnabled(false);
        mPresenter.getLiveListPresenter(gType, offset);
    }
}
