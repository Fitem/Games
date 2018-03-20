package com.fitem.games.ui.main.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseFragment;
import com.fitem.games.common.commonwidget.LoadingTip;
import com.fitem.games.ui.grils.adapter.GrilsAdapter;
import com.fitem.games.ui.grils.bean.Grils;
import com.fitem.games.ui.grils.contract.GrilsContract;
import com.fitem.games.ui.grils.model.GrilsModel;
import com.fitem.games.ui.grils.presenter.GrilsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Fitem on 2018/3/18.
 */

public class GrilsFragment extends BaseFragment<GrilsPresenter, GrilsModel> implements SwipeRefreshLayout.OnRefreshListener, GrilsContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_title)
    TextView titleView;
    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.loading_tip)
    LoadingTip loadingTip;
    private int pg;
    private List<Grils.ResultsBean> list;
    private GrilsAdapter grilsAdapter;

    @OnClick(R.id.loading_tip)
    public void reload() {
        lazyFetchData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_grils;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        initTitle();
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        initNewsAdapter();
    }

    @Override
    protected void lazyFetchData() {
        pg = 0;
        mPresenter.getGrilsListPresenter(pg);
    }

    private void initNewsAdapter() {
        grilsAdapter = new GrilsAdapter(R.layout.grils_item, list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(grilsAdapter);
        grilsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        grilsAdapter.setOnLoadMoreListener(this, recyclerView);
    }

    private void initTitle() {
        titleView.setText(R.string.grils);
    }


    @Override
    protected boolean isHideFragment() {
        return true;
    }


    @Override
    public void onRefresh() {
        grilsAdapter.setEnableLoadMore(false);
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
    public void returnGrilsList(List<Grils.ResultsBean> list) {
        swipeRefreshLayout.setEnabled(true);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        grilsAdapter.setEnableLoadMore(true);
        boolean isFirst = pg == 0;
        if (list == null) {

        } else if (isFirst) {
            grilsAdapter.setNewData(list);
        } else {
            grilsAdapter.addData(list);
        }

        if (list == null) {
            grilsAdapter.loadMoreFail();
        } else if (list.size() < AppConstants.PAGE_SIZE) {
            grilsAdapter.loadMoreEnd();
        } else {
            grilsAdapter.loadMoreComplete();
            pg++;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setEnabled(false);
        mPresenter.getGrilsListPresenter(pg);
    }
}
