package com.fitem.games.common.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fitem.games.R;

/**
 * des:加载页面内嵌提示
 * Created by xsf
 * on 2017.07
 */
public class LoadingTip extends LinearLayout {

    private ImageView img_tip_logo;
    private ProgressBar progress;
    private TextView tv_tips;
    private TextView tv_reloading_tip;
    private onReloadListener onReloadListener;


    public LoadingTip(Context context) {
        super(context);
        initView(context);
    }

    public LoadingTip(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadingTip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.layout_loading_tip, this);
        img_tip_logo = (ImageView) findViewById(R.id.img_tip_logo);
        progress = (ProgressBar) findViewById(R.id.progress);
        tv_tips = (TextView) findViewById(R.id.tv_tips);
        tv_reloading_tip = (TextView) findViewById(R.id.tv_reloading_tip);
        // 重新尝试
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onReloadListener != null) {
                    onReloadListener.reload();
                }
            }
        });
        setVisibility(View.GONE);
    }

    public void setTips(String tips) {
        if (tv_tips != null) {
            tv_tips.setText(tips);
        }
    }

    public boolean isLoading() {
        return getVisibility() == VISIBLE;
    }

    /**
     * 空数据
     */
    public void empty() {
        setVisibility(VISIBLE);
        img_tip_logo.setVisibility(VISIBLE);
        progress.setVisibility(GONE);
        tv_tips.setVisibility(VISIBLE);
        tv_reloading_tip.setVisibility(VISIBLE);
        tv_tips.setText(R.string.empty);
    }

    /**
     * 服务器返回错误
     */
    public void sereverError() {
        setVisibility(View.VISIBLE);
        img_tip_logo.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        tv_tips.setVisibility(VISIBLE);
        tv_reloading_tip.setVisibility(VISIBLE);
        tv_tips.setText(R.string.loading_error);
        img_tip_logo.setImageResource(R.mipmap.ic_loading_error);
    }

    /**
     * 错误
     */
    public void netError() {
        setVisibility(View.VISIBLE);
        img_tip_logo.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        tv_tips.setVisibility(VISIBLE);
        tv_reloading_tip.setVisibility(VISIBLE);
        tv_tips.setText(R.string.no_network_tip);
        img_tip_logo.setImageResource(R.mipmap.ic_no_network);
    }

    /**
     * 加载中
     */
    public void loading() {
        setVisibility(View.VISIBLE);
        img_tip_logo.setVisibility(GONE);
        progress.setVisibility(VISIBLE);
        tv_tips.setVisibility(GONE);
        tv_reloading_tip.setVisibility(GONE);
    }

    /**
     * 加载结束
     */
    public void finish() {
        setVisibility(View.GONE);
    }


    public void setOnReloadListener(onReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    /**
     * 重新尝试接口
     */
    public interface onReloadListener {
        void reload();
    }


}

