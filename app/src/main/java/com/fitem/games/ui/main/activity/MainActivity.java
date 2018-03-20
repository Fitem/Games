package com.fitem.games.ui.main.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseActivity;
import com.fitem.games.common.basebean.TabEntity;
import com.fitem.games.ui.main.fragment.GrilsFragment;
import com.fitem.games.ui.main.fragment.LiveFragment;
import com.fitem.games.ui.main.fragment.MineFragment;
import com.fitem.games.ui.main.fragment.NewsFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.pgyersdk.update.PgyUpdateManager;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;

    public static final int NEWS_POSITION = 0;
    public static final int LIVE_POSITION = 1;
    public static final int GRILS_POSITION = 2;
    public static final int MINE_POSITION = 3;
    private String[] mTitles;
    private int[] mIconUnselectIds;
    private int[] mIconSelectIds;
    private ArrayList<CustomTabEntity> tabEntities;
    private NewsFragment newsFragment;
    private LiveFragment liveFragment;
    private GrilsFragment grilsFragment;
    private MineFragment mineFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initTab();
        checkUpgrade();
    }

    private void checkUpgrade() {
        PgyUpdateManager.setIsForced(false); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
        PgyUpdateManager.register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化frament
        initFragment(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        PgyUpdateManager.unregister();
        super.onDestroy();
    }

    private void initFragment(Bundle savedInstanceState) {
        FragmentManager mManager = getSupportFragmentManager();
        int mCurrentTabPosition = LIVE_POSITION;
        if (savedInstanceState != null) {
            newsFragment = (NewsFragment) mManager.findFragmentByTag(AppConstants.NEWS);
            liveFragment = (LiveFragment) mManager.findFragmentByTag(AppConstants.LIVE);
            grilsFragment = (GrilsFragment) mManager.findFragmentByTag(AppConstants.GRILS);
            mineFragment = (MineFragment) mManager.findFragmentByTag(AppConstants.MINE);
            mCurrentTabPosition = savedInstanceState.getInt(AppConstants.HOME_CURRENT_TAB_POSITION, mCurrentTabPosition);
        } else {
            FragmentTransaction mTransaction = mManager.beginTransaction();
            newsFragment = new NewsFragment();
            liveFragment = new LiveFragment();
            grilsFragment = new GrilsFragment();
            mineFragment = new MineFragment();
            mTransaction.add(R.id.fl_body, newsFragment, AppConstants.NEWS);
            mTransaction.add(R.id.fl_body, liveFragment, AppConstants.LIVE);
            mTransaction.add(R.id.fl_body, grilsFragment, AppConstants.GRILS);
            mTransaction.add(R.id.fl_body, mineFragment, AppConstants.MINE);
            mTransaction.commit();
        }
        switchTo(mCurrentTabPosition);
        tabLayout.setCurrentTab(mCurrentTabPosition);
    }

    private void switchTo(int position) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            // 新闻
            case NEWS_POSITION:
                mTransaction.show(newsFragment);
                mTransaction.hide(liveFragment);
                mTransaction.hide(grilsFragment);
                mTransaction.hide(mineFragment);
                mTransaction.commitAllowingStateLoss();
                break;
            // 直播
            case LIVE_POSITION:
                mTransaction.hide(newsFragment);
                mTransaction.show(liveFragment);
                mTransaction.hide(grilsFragment);
                mTransaction.hide(mineFragment);
                mTransaction.commitAllowingStateLoss();
                break;
            // 美女
            case GRILS_POSITION:
                mTransaction.hide(newsFragment);
                mTransaction.hide(liveFragment);
                mTransaction.show(grilsFragment);
                mTransaction.hide(mineFragment);
                mTransaction.commitAllowingStateLoss();
                break;
            // 我的
            case MINE_POSITION:
                mTransaction.hide(newsFragment);
                mTransaction.hide(liveFragment);
                mTransaction.hide(grilsFragment);
                mTransaction.show(mineFragment);
                mTransaction.commitAllowingStateLoss();
                break;
            default:
                mTransaction.show(newsFragment);
                mTransaction.hide(liveFragment);
                mTransaction.hide(grilsFragment);
                mTransaction.hide(mineFragment);
                mTransaction.commitAllowingStateLoss();
                break;
        }
    }

    private void initTab() {
        initTabData();
        tabLayout.setTabData(tabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initTabData() {
        mTitles = new String[]{getString(R.string.news), getString(R.string.live), getString(R.string.grils), getString(R.string.mine)};
        mIconUnselectIds = new int[]{R.mipmap.ic_news_normal, R.mipmap.ic_score_normal, R.mipmap.ic_group_normal, R.mipmap.ic_mine_normal};
        mIconSelectIds = new int[]{R.mipmap.ic_news_selected, R.mipmap.ic_score_selected, R.mipmap.ic_group_selected, R.mipmap.ic_mine_selected};
        tabEntities = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            tabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
    }
}
