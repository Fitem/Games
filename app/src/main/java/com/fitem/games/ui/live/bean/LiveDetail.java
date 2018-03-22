package com.fitem.games.ui.live.bean;

import java.util.List;

/**
 * Created by Fitem on 2018/3/22.
 */

public class LiveDetail {

    /**
     * enable : 1
     * game_type : lol
     * is_followed : 0
     * live_id : 4025818
     * live_img : http://snap.quanmin.tv/4025818-1521692549-134.jpg?imageView2/2/w/390/
     * live_name : quanmin
     * live_nickname : 全民丶蜘蛛馍
     * live_online : 265710
     * live_title : 蜘蛛馍：影流-邀请上车~陪你包赢不输主播
     * live_type : quanmin
     * live_userimg : http://a.img.shouyintv.cn/sRIC303-normal
     * stream_list : [{"type":"标清","url":"http://liveal.quanmin.tv/live/4025818_quanmin576.flv"},{"type":"高清","url":"http://liveal.quanmin.tv/live/4025818_quanmin720.flv"}]
     */

    private int enable;
    private String game_type;
    private int is_followed;
    private String live_id;
    private String live_img;
    private String live_name;
    private String live_nickname;
    private long live_online;
    private String live_title;
    private String live_type;
    private String live_userimg;
    private List<StreamListBean> stream_list;

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public int getIs_followed() {
        return is_followed;
    }

    public void setIs_followed(int is_followed) {
        this.is_followed = is_followed;
    }

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public String getLive_img() {
        return live_img;
    }

    public void setLive_img(String live_img) {
        this.live_img = live_img;
    }

    public String getLive_name() {
        return live_name;
    }

    public void setLive_name(String live_name) {
        this.live_name = live_name;
    }

    public String getLive_nickname() {
        return live_nickname;
    }

    public void setLive_nickname(String live_nickname) {
        this.live_nickname = live_nickname;
    }

    public long getLive_online() {
        return live_online;
    }

    public void setLive_online(long live_online) {
        this.live_online = live_online;
    }

    public String getLive_title() {
        return live_title;
    }

    public void setLive_title(String live_title) {
        this.live_title = live_title;
    }

    public String getLive_type() {
        return live_type;
    }

    public void setLive_type(String live_type) {
        this.live_type = live_type;
    }

    public String getLive_userimg() {
        return live_userimg;
    }

    public void setLive_userimg(String live_userimg) {
        this.live_userimg = live_userimg;
    }

    public List<StreamListBean> getStream_list() {
        return stream_list;
    }

    public void setStream_list(List<StreamListBean> stream_list) {
        this.stream_list = stream_list;
    }

    public static class StreamListBean {
        /**
         * type : 标清
         * url : http://liveal.quanmin.tv/live/4025818_quanmin576.flv
         */

        private String type;
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
