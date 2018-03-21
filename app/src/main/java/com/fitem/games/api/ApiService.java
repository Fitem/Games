package com.fitem.games.api;

import android.support.v4.util.ArrayMap;

import com.fitem.games.ui.grils.bean.Grils;
import com.fitem.games.ui.live.bean.LiveBase;
import com.fitem.games.ui.live.bean.LiveItem;
import com.fitem.games.ui.news.bean.GNewsDetail;
import com.fitem.games.ui.news.bean.News;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * des:ApiService
 * Created by Fitem on 2017/3/14.
 */

public interface ApiService {

    @GET("list/{explore_id}/{offset}-{limit}.html")
    Observable<News> getNewsList(
            @Path("explore_id") String explore_id,
            @Path("offset") int offset,
            @Path("limit") int limit
    );

    @GET("{news_id}/full.html")
    Observable<ArrayMap<String, GNewsDetail>> getNewsDetail(
            @Path("news_id") String news_id
    );

    @GET("{page_size}/{page}")
    Observable<Grils> getGrilsPic(
            @Path("page_size") int ps,
            @Path("page") int pg
    );

    @GET("list")
    Observable<LiveBase<List<LiveItem>>> getLiveList(
            @Query("live_type") String liveType,
            @Query("game_type") String gameType,
            @Query("offset") int offset,
            @Query("limit") int limit
    );
}
