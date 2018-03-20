package com.fitem.games.api;

import com.fitem.games.ui.news.bean.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
}
