package com.fitem.games.ui.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseFragment;
import com.fitem.games.common.base.BaseFragmentAdapter;
import com.fitem.games.ui.live.fragment.LiveItemFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Fitem on 2018/3/18.
 */

public class LiveFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<Fragment> fragmentList;
    private BaseFragmentAdapter fragmentAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_live;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        initTab();
    }

    private void initTab() {
        String[] titles = new String[] {getString(R.string.lol),getString(R.string.ow),getString(R.string.hs),getString(R.string.data2),getString(R.string.csgo)};
        initFragment();
        fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(titles.length - 1);
        tabLayout.setViewPager(viewPager, titles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        String[] types = new String[]{AppConstants.LOL, AppConstants.OW, AppConstants.HS, AppConstants.DATA2, AppConstants.CSGO};
        LiveItemFragment fragment;
        Bundle bundle;
        for (String type : types) {
            fragment = new LiveItemFragment();
            bundle = new Bundle();
            bundle.putString(AppConstants.GAME_TYPE, type);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
    }

    @Override
    protected boolean isHideFragment() {
        return true;
    }
}
