package com.fitem.games.ui.live.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fitem.games.R;
import com.fitem.games.app.AppConstants;
import com.fitem.games.common.base.BaseActivity;
import com.fitem.games.common.baserx.RxSchedulers;
import com.fitem.games.common.baserx.RxSubscriber;
import com.fitem.games.common.helper.ToastHelper;
import com.fitem.games.ui.live.bean.LiveDetail;
import com.fitem.games.ui.live.bean.LiveItem;
import com.fitem.games.ui.live.contract.LiveContract;
import com.fitem.games.ui.live.model.LiveModel;
import com.fitem.games.ui.live.presenter.LivePresenter;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by Fitem on 2018/3/22.
 */

public class LiveDtlActivity extends BaseActivity<LivePresenter, LiveModel> implements PLMediaPlayer.OnVideoSizeChangedListener, LiveContract.View {
    // 用于显示播放画面
    @BindView(R.id.surfaceview)
    SurfaceView surfaceView;
    @BindView(R.id.danmuview)
    DanmakuView danmuView;
    // 横屏控件
    @BindView(R.id.iv_back_landscape)
    ImageView iv_back_landscape;
    @BindView(R.id.tv_roomname_landscape)
    TextView tv_roomname_landscape;
    @BindView(R.id.btn_stream_1080p_landscape)
    Button btn_stream_1080p_landscape;
    @BindView(R.id.btn_stream_360p_landscape)
    Button btn_stream_360p_landscape;
    @BindView(R.id.iv_play_pause_landscape)
    ImageView iv_play_pause_landscape;
    @BindView(R.id.iv_refresh_landscape)
    ImageView iv_refresh_landscape;
    @BindView(R.id.et_danmu_landscape)
    EditText et_danmu_landscape;
    @BindView(R.id.btn_send_landscape)
    Button btn_send_landscape;
    @BindView(R.id.iv_danmu_visible_landscape)
    ImageView iv_danmu_visible_landscape;
    @BindView(R.id.iv_fullscreen_exit_landscape)
    ImageView iv_fullscreen_exit_landscape;
    @BindView(R.id.layout_landscape)
    RelativeLayout layout_landscape;
    // 竖屏控件
    @BindView(R.id.iv_back_portrait)
    ImageView iv_back_portrait;
    @BindView(R.id.iv_danmu_visible_portrait)
    ImageView iv_danmu_visible_portrait;
    @BindView(R.id.iv_fullscreen_portrait)
    ImageView iv_fullscreen_portrait;
    @BindView(R.id.layout_portrait)
    RelativeLayout layout_portrait;
    @BindView(R.id.progressbar)
    FrameLayout progressbar;
    @BindView(R.id.layout_top)
    FrameLayout layout_top;
    // 底部Layout相关
    @BindView(R.id.layout_bottom)
    LinearLayout layout_bottom;

    private static final int HANDLER_CONTROLLER_TIME = 5;//MediaController显示时间
    private final String[] indicatorText = new String[]{"聊天", "主播"};
    private final int[] normalResId = new int[]{R.drawable.ic_danmaku_on_normal_dark, R.drawable.ic_avatar_normal};
    private final int[] pressedResId = new int[]{R.drawable.ic_danmaku_on_pressed, R.drawable.ic_avatar_pressed};
    private final BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private final List<String> titleList = new ArrayList<>();

    private boolean isSurfaceViewInit = false;         //SurfaceView初始化标志位
    private boolean isVideoPrepared = false;         //Video加载标志位，用于显示隐藏ProgreeBar
    private boolean isPause = false;         //直播暂停标志位
    private boolean isFullscreen = false;   //全屏标志位
    private boolean isControllerHiden = false;   //MediaController显示隐藏标志位
    private String live_type;   //直播平台
    private String live_id;     //直播房间号ID
    private String game_type;   //直播游戏类型
    private String live_url;   //直播url
    private int surfacePortraitWidth;
    private int surfacePortraitHeight;
    private int videoWidth;
    private int videoHeight;
    private int playWidth;
    private int playHeight;
    private List<LiveDetail.StreamListBean> streamList = new ArrayList<>();//直播流列表
    private boolean isShowDanmu = false;// 弹幕显示标志位
    private DanmakuContext danmakuContext;
    private int current;
    private PLMediaPlayer mediaPlayer;  //媒体控制器

    @Override
    public int getLayoutId() {
        return R.layout.activity_live_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        initData();
        initMediaPlayer();
        mPresenter.getLiveDetailPresenter(live_type, live_id, game_type);
    }

    private void initMediaPlayer() {
        /***设置其他View***/
        tv_roomname_landscape.setSelected(true);

        //SurfaceView监听回调
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                progressbar.setVisibility(View.VISIBLE);
                prepareMediaPlayer();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (!isSurfaceViewInit) {
                    //竖屏
                    surfacePortraitWidth = width;
                    surfacePortraitHeight = height;

                    isSurfaceViewInit = true;
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer != null) {
                    mediaPlayer.setDisplay(null);
                }
            }
        });

        et_danmu_landscape.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        //弹幕烈焰使
        iv_danmu_visible_landscape.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
        iv_danmu_visible_portrait.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);

        danmuView.enableDanmakuDrawingCache(true);//打开绘图缓存，提升绘制效率
        danmuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                try {
                    isShowDanmu = true;
                    danmuView.start();
                    iv_danmu_visible_landscape.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    iv_danmu_visible_portrait.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDuplicateMergingEnabled(true);//设置合并重复弹幕
        danmuView.prepare(parser, danmakuContext);

    }

    private void handlerController() {
        Observable.timer(HANDLER_CONTROLLER_TIME, TimeUnit.SECONDS).compose(RxSchedulers.<Long>io_main())
                .subscribe(new RxSubscriber<Long>(mContext, false) {
                    @Override
                    protected void _onNext(Long aLong) {
                        //hide controller
                        layout_landscape.setVisibility(View.GONE);
                        layout_portrait.setVisibility(View.GONE);
                        isControllerHiden = true;
                    }

                    @Override
                    protected void _onError(int tag) {
                        _onNext(0L);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mRxManager.add(d);
                    }
                });
    }

    private void initData() {
        //得到传入的参数
        Intent intent = getIntent();
        live_type = intent.getStringExtra(AppConstants.LIVE_TYPE);
        live_id = intent.getStringExtra(AppConstants.LIVE_ID);
        game_type = intent.getStringExtra(AppConstants.GAME_TYPE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (mediaPlayer != null && isPause && !TextUtils.isEmpty(live_url)) {
            try {
//                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

                mediaPlayer.reset();
                mediaPlayer.setDataSource(live_url);
                mediaPlayer.prepareAsync();
                isPause = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (danmuView != null && danmuView.isPrepared() && danmuView.isPaused()) {
            danmuView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPause = true;
//            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            audioManager.abandonAudioFocus(null);
        }
        if (danmuView != null && danmuView.isPrepared()) {
            danmuView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
//            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            audioManager.abandonAudioFocus(null);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        progressbar.setVisibility(isVideoPrepared ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (isFullscreen) {
            exitFullscreen();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 配置MediaPlayer相关参数
     */
    private void prepareMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.setDisplay(surfaceView.getHolder());
            return;
        }
        try {
            AVOptions avOptions = new AVOptions();
            avOptions.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);  //直播流：1->是 0->否
            avOptions.setInteger(AVOptions.KEY_MEDIACODEC, 0);      //解码类型 1->硬解 0->软解
            avOptions.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);//缓冲结束后自动播放
            avOptions.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
            avOptions.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
            avOptions.setInteger(AVOptions.KEY_BUFFER_TIME, 10 * 1000);
            avOptions.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
            avOptions.setInteger(AVOptions.KEY_CACHE_BUFFER_DURATION, 10 * 1000);
            avOptions.setInteger(AVOptions.KEY_MAX_CACHE_BUFFER_DURATION, 15 * 1000);

//            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            audioManager.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

            mediaPlayer = new PLMediaPlayer(this, avOptions);

            mediaPlayer.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(PLMediaPlayer plMediaPlayer) {
                    progressbar.setVisibility(isVideoPrepared ? View.GONE : View.VISIBLE);
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(PLMediaPlayer plMediaPlayer) {

                }
            });
            mediaPlayer.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {
                    switch (what) {
                        case PLMediaPlayer.MEDIA_INFO_BUFFERING_START://开始缓冲
                            isVideoPrepared = false;
                            Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_BUFFERING_START");
                            break;
                        case PLMediaPlayer.MEDIA_INFO_BUFFERING_END://缓冲结束
                            Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_BUFFERING_END");
                            break;
                        case PLMediaPlayer.MEDIA_INFO_BUFFERING_BYTES_UPDATE:
                            Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_BUFFERING_BYTES_UPDATE");
                            break;
                        case PLMediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                            Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_NOT_SEEKABLE");
                            break;
                        case PLMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                            Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_VIDEO_ROTATION_CHANGED");
                            break;
                        case PLMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                            Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_AUDIO_RENDERING_START");
                            break;
                        case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START://视频缓冲完成可播放
                            progressbar.setVisibility(View.GONE);
                            isVideoPrepared = true;
                            isPause = false;
                            iv_play_pause_landscape.setImageResource(isPause ? R.drawable.selector_btn_play : R.drawable.selector_btn_pause);
                            Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_VIDEO_RENDERING_START");
                            break;
                        default:
                            Log.d("PLMediaPlayer", "onInfo: " + what);
                            break;
                    }
                    return true;
                }
            });
            mediaPlayer.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
//                    showError(new MediaException(errorCode).getMessage());
                    return true;
                }
            });

            mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
//            mediaPlayer.setDataSource(live_url);
            mediaPlayer.setDisplay(surfaceView.getHolder());
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.surfaceview, R.id.iv_back_landscape, R.id.btn_stream_1080p_landscape, R.id.btn_stream_360p_landscape,
            R.id.iv_play_pause_landscape, R.id.iv_refresh_landscape, R.id.btn_send_landscape, R.id.iv_danmu_visible_landscape,
            R.id.iv_fullscreen_exit_landscape, R.id.iv_back_portrait, R.id.iv_danmu_visible_portrait,
            R.id.iv_fullscreen_portrait})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.surfaceview:
                if (isFullscreen) {
                    if (isControllerHiden) {//全屏&&隐藏
                        layout_landscape.setVisibility(View.VISIBLE);
                        layout_portrait.setVisibility(View.GONE);
                        handlerController();
                        isControllerHiden = false;
                    } else if (!isControllerHiden) {//全屏&&显示
                        handlerController();
                    }
                } else if (!isFullscreen) {
                    if (isControllerHiden) {//非全屏&&隐藏
                        layout_landscape.setVisibility(View.GONE);
                        layout_portrait.setVisibility(View.VISIBLE);
                        handlerController();
                        isControllerHiden = false;
                    } else if (!isControllerHiden) {//非全屏&&显示
                        handlerController();
                    }
                }
                break;

            //全屏Back
            case R.id.iv_back_landscape:
                onBackPressed();
                break;

            //直播流连接切换（超清）
            case R.id.btn_stream_1080p_landscape:
                handlerController();
                updateLandscapeUI(true);
                for (LiveDetail.StreamListBean stream : streamList) {
                    if (stream.getType().equals(getString(R.string.live_1080p))) {
                        live_url = stream.getUrl();
                    }
                }
                mediaPlayer.reset();
                //显示ProgressBar
                isVideoPrepared = false;
                progressbar.setVisibility(isVideoPrepared ? View.GONE : View.VISIBLE);
                try {
                    mediaPlayer.setDataSource(live_url);//加载直播链接进行播放
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.prepareAsync();
                break;

            //直播流连接切换（普清）
            case R.id.btn_stream_360p_landscape:
                handlerController();
                updateLandscapeUI(false);

                for (LiveDetail.StreamListBean stream :
                        streamList) {
                    if (stream.getType().equals(getString(R.string.live_360p))) {
                        live_url = stream.getUrl();
                    }
                }
                mediaPlayer.reset();
                //显示ProgressBar
                isVideoPrepared = false;
                progressbar.setVisibility(isVideoPrepared ? View.GONE : View.VISIBLE);
                try {
                    mediaPlayer.setDataSource(live_url);//加载直播链接进行播放
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.prepareAsync();
                break;

            //播放&暂停
            case R.id.iv_play_pause_landscape:
                if (isPause) {
                    onResume();
                } else if (!isPause) {
                    onPause();
                }
                iv_play_pause_landscape.setImageResource(isPause ? R.drawable.selector_btn_play : R.drawable.selector_btn_pause);
                handlerController();
                break;

            //重新加载
            case R.id.iv_refresh_landscape:
                try {
                    mediaPlayer.reset();

                    isVideoPrepared = false;
                    progressbar.setVisibility(isVideoPrepared ? View.GONE : View.VISIBLE);

                    mediaPlayer.setDataSource(live_url);//加载直播链接进行播放
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handlerController();
                break;

            //横屏发送弹幕
            case R.id.btn_send_landscape:
                handlerController();

                String danmu = et_danmu_landscape.getText().toString();
                if (TextUtils.isEmpty(danmu)) {
                    ToastHelper.showShort(R.string.danmaku_empty_tip);
                    return;
                }

                et_danmu_landscape.setText(null);
                break;

            //横屏弹幕显示&隐藏
            case R.id.iv_danmu_visible_landscape:

                if (isShowDanmu) {//已开启弹幕
                    isShowDanmu = false;
                    iv_danmu_visible_landscape.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    iv_danmu_visible_portrait.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    ToastHelper.showShort(R.string.danmuka_closed);
                } else if (!isShowDanmu) {//已关闭弹幕
                    isShowDanmu = true;
                    iv_danmu_visible_landscape.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    iv_danmu_visible_portrait.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    ToastUtils.showShort(R.string.danmuka_opened);
                }

                handlerController();
                break;

            //退出全屏
            case R.id.iv_fullscreen_exit_landscape:
                exitFullscreen();
                break;

            //竖屏Back
            case R.id.iv_back_portrait:
                onBackPressed();
                break;

            //竖屏弹幕显示隐藏
            case R.id.iv_danmu_visible_portrait:
                if (isShowDanmu) {//已开启弹幕
                    isShowDanmu = false;
                    iv_danmu_visible_landscape.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    iv_danmu_visible_portrait.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    ToastHelper.showShort(R.string.danmuka_closed);
                } else if (!isShowDanmu) {//已关闭弹幕
                    isShowDanmu = true;
                    iv_danmu_visible_landscape.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    iv_danmu_visible_portrait.setImageResource(isShowDanmu ? R.drawable.selector_danmaku_on : R.drawable.selector_danmaku_off);
                    ToastHelper.showShort(R.string.danmuka_opened);
                }

                handlerController();
                break;

            case R.id.iv_fullscreen_portrait://进入全屏
                enterFullscreen();
                break;
        }
    }

    /**
     * 更新画质按钮
     *
     * @param is1080p
     */
    private void updateLandscapeUI(boolean is1080p) {
        btn_stream_1080p_landscape.setBackground(ContextCompat.getDrawable(this, is1080p ? R.drawable.background_btn_stream_pressed : R.drawable.background_btn_stream_normal));
        btn_stream_360p_landscape.setBackground(ContextCompat.getDrawable(this, is1080p ? R.drawable.background_btn_stream_normal : R.drawable.background_btn_stream_pressed));
        btn_stream_1080p_landscape.setTextColor(ContextCompat.getColor(this, is1080p ? R.color.color_primary : R.color.white));
        btn_stream_360p_landscape.setTextColor(ContextCompat.getColor(this, is1080p ? R.color.white : R.color.color_primary));
    }

    /**
     * 进入全屏
     */
    private void enterFullscreen() {
        layout_top.removeView(surfaceView);
        layout_top.removeView(progressbar);
        layout_top.removeView(danmuView);
        layout_top.removeView(layout_landscape);
        layout_top.removeView(layout_portrait);

        ScreenUtils.setLandscape(this);

        isFullscreen = true;
        isControllerHiden = false;
        handlerController();

        layout_top.addView(surfaceView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(progressbar, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(danmuView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(layout_landscape, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(layout_portrait, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //全屏隐藏状态栏
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(lp);

        layout_bottom.setVisibility(View.GONE);
//        progressbar.setVisibility(isVideoPrepared == true ? View.GONE : View.VISIBLE);
        layout_landscape.setVisibility(View.VISIBLE);
        layout_portrait.setVisibility(View.GONE);
        iv_play_pause_landscape.setImageResource(isPause ? R.drawable.selector_btn_play : R.drawable.selector_btn_pause);

    }

    /**
     * 退出全屏
     */
    private void exitFullscreen() {
        layout_top.removeView(surfaceView);
        layout_top.removeView(progressbar);
        layout_top.removeView(danmuView);
        layout_top.removeView(layout_landscape);
        layout_top.removeView(layout_portrait);

        ScreenUtils.setPortrait(this);

        isFullscreen = false;
        isControllerHiden = false;
        handlerController();

        layout_top.addView(surfaceView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(progressbar, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(danmuView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(layout_landscape, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout_top.addView(layout_portrait, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        onVideoSizeChanged(mediaPlayer, videoWidth, videoHeight, 0, 0);

        //显示状态栏
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(lp);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        layout_bottom.setVisibility(View.VISIBLE);
//        progressbar.setVisibility(isVideoPrepared == true ? View.GONE : View.VISIBLE);
        layout_landscape.setVisibility(View.GONE);
        layout_portrait.setVisibility(View.VISIBLE);
    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int width, int height, int i2, int i3) {
        videoWidth = width;
        videoHeight = height;

        if (videoWidth != 0 && videoHeight != 0) {
            float ratioW = (float) videoWidth / (float) (isFullscreen ? ScreenUtils.getScreenWidth() : surfacePortraitWidth);
            float ratioH = (float) videoHeight / (float) (isFullscreen ? ScreenUtils.getScreenHeight() : surfacePortraitHeight);
            float ratio = Math.max(ratioW, ratioH);
            playWidth = (int) Math.ceil((float) videoWidth / ratio);
            playHeight = (int) Math.ceil((float) videoHeight / ratio);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(playWidth, playHeight);
            lp.gravity = Gravity.CENTER;
            surfaceView.setLayoutParams(lp);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(int tag) {

    }

    @Override
    public void returnLiveList(List<LiveItem> list) {

    }

    @Override
    public void returnLiveDetail(LiveDetail detail) {
        try {
            tv_roomname_landscape.setText(detail.getLive_title());
            streamList = detail.getStream_list();
            LiveDetail.StreamListBean stream = streamList.get(streamList.size() - 1);
            live_url = stream.getUrl();
            if (streamList.size() == 1) {
                if (stream.getType().equals(getString(R.string.live_1080p))) {
                    btn_stream_360p_landscape.setVisibility(View.GONE);
                }
                if (stream.getType().equals(getString(R.string.live_360p))) {
                    btn_stream_1080p_landscape.setVisibility(View.GONE);
                }
            }
            if (stream.getType().equals(getString(R.string.live_1080p))) {
                updateLandscapeUI(true);
            }
            if (stream.getType().equals(getString(R.string.live_360p))) {
                updateLandscapeUI(false);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            live_url = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        }
        try {
            mediaPlayer.setDataSource(live_url);//加载直播链接进行播放
            mediaPlayer.prepareAsync();
            handlerController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
