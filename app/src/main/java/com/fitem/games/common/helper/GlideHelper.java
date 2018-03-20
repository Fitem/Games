package com.fitem.games.common.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fitem.games.R;


/**
 * Created by Fitem on 2017/5/23.
 */

public class GlideHelper {

    // 加载新闻图片
    public static void loadNewsPic(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.mipmap.ic_news_prepare)
                .error(R.mipmap.ic_news_prepare)
                .centerCrop()
//                .crossFade()
                .dontAnimate()
                .into(view);
    }

    // 加载用户头像
    public static void loadUserPic(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.mipmap.visitor_picture)
                .error(R.mipmap.visitor_picture)
                .apply(RequestOptions.circleCropTransform())
//                .centerCrop()
//                .crossFade()
                .dontAnimate()
                .into(view);
    }
}
