package com.fitem.games.ui.live.bean;

/**
 * Created by Fitem on 2018/3/21.
 */

public class LiveItem {

    /**
     * enable : 1
     * game_type : lol
     * live_id : 666
     * live_img : http://snap.quanmin.tv/666-1521640506-486.jpg?imageView2/2/w/390/
     * live_name : quanmin
     * live_nickname : 解说小漠
     * live_online : 1373204
     * live_title : 史诗级大片 低分段路人王小漠！一打四！
     * live_type : quanmin
     * live_userimg : http://image.quanmin.tv/avatar/bd6033825a6541e5e0a26af5d8f0d560png?imageView2/2/w/300/
     * show_type : native
     */

    private int enable;
    private String game_type;
    private String live_id;
    private String live_img;
    private String live_name;
    private String live_nickname;
    private long live_online;
    private String live_title;
    private String live_type;
    private String live_userimg;
    private String show_type;

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

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }
}
