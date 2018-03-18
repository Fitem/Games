package com.fitem.games.common.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.fitem.games.R;
import com.fitem.games.common.baseapp.AppManager;
import com.fitem.games.common.baserx.RxManager;
import com.fitem.games.common.commonutils.TUtil;
import com.fitem.games.common.commonwidget.StatusBarCompat;
import com.fitem.games.common.helper.I18NHelper;

import butterknife.ButterKnife;

/**
 * 基类
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;
    private long loadStartTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadStartTime = System.currentTimeMillis();
        mRxManager = new RxManager();
        doBeforeSetcontentView();
        // 兼容启动页没有布局的情况
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        // 设置状态栏
        SetStatusBar();
        // 多语言切换
        setLocale();

        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        this.initPresenter();
        this.initView();
    }


    /**
     * 设置语言
     */
    private void setLocale() {
        if(!I18NHelper.isSameLanguage(this)) {
            I18NHelper.setLocale(this);
            I18NHelper.toRestartMainActvity(this);
        }
    }

    /**
     * 设置layout前配置
     */
    protected void doBeforeSetcontentView() {
        //设置昼夜主题
//        initTheme();
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initView();

    // 开始加载页面节点
    public long getStartTime() {
        return loadStartTime;
    }

    /**
     * 设置主题
     */
    private void initTheme() {
//        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBar() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorAccent));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBar(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
        AppManager.getAppManager().removeActivity(this);
    }
}
