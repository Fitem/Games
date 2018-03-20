package com.fitem.games.ui.news.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Fitem on 2018/3/20.
 */

public class News {
    @SerializedName("T1348654151579")
    private List<GNews> gNewsList;

    public List<GNews> getgNewsList() {
        return gNewsList;
    }

    public void setgNewsList(List<GNews> gNewsList) {
        this.gNewsList = gNewsList;
    }
}
