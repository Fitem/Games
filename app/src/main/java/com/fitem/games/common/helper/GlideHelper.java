package com.fitem.games.common.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.fitem.games.R;


/**
 * Created by Fitem on 2017/5/23.
 */

public class GlideHelper {

    // 加载新闻图片
    public static void loadNewsPic(Context context, String url, ImageView view) {
        loadPic(context, url, view, R.mipmap.ic_news_prepare);
    }

    // 加载美女图片
    public static void loadGrilsPic(Context context, String url, final ImageView view) {
        GlideApp.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.mipmap.ic_news_prepare)
                .error(R.mipmap.ic_news_prepare)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (view == null) {
                            return false;
                        }
                        if (view.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            view.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = view.getLayoutParams();
                        int vw = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + view.getPaddingTop() + view.getPaddingBottom();
                        view.setLayoutParams(params);
                        return false;
                    }
                })
                .fitCenter()
//                .crossFade()
                .dontAnimate()
                .into(view);
    }

    // 加载美女详情图片
    public static void loadGrilsDtlPic(Activity context, String url, ImageView view) {
        GlideApp.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                .placeholder(R.mipmap.ic_news_prepare)
                .error(R.mipmap.ic_news_prepare)
                .fitCenter()
                .transition(new DrawableTransitionOptions().crossFade())
//                .dontAnimate()
                .into(view);
    }

    // 加载用户头像
    public static void loadUserPic(Context context, String url, ImageView view) {
        loadCirclePic(context, url, view, R.mipmap.visitor_picture);
    }

    // 加载图片
    public static void loadPic(Context context, String url, ImageView view, @DrawableRes int placeId) {
        GlideApp.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(placeId)
                .error(placeId)
                .centerCrop()
//                .crossFade()
                .dontAnimate()
                .into(view);
    }

    // 加载圆图
    public static void loadCirclePic(Context context, String url, ImageView view, @DrawableRes int placeId) {
        GlideApp.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(placeId)
                .error(placeId)
                .apply(RequestOptions.circleCropTransform())
//                .centerCrop()
//                .crossFade()
                .dontAnimate()
                .into(view);
    }
}
