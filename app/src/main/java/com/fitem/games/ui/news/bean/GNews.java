package com.fitem.games.ui.news.bean;

import java.util.List;

/**
 * Created by Fitem on 2018/3/19.
 */

public class GNews {

    /**
     * template : recommend
     * lmodify : 2018-03-20 11:37:05
     * source : 网易游戏频道
     * postid : DDB6A3TJ003198EF
     * title : 龙骑士菲尼克斯即将加入《风暴英雄》
     * mtime : 2018-03-20 11:37:05
     * hasImg : 1
     * wap_portal : [{"subtitle":"Top ranking","title":"手游热榜","imgsrc":"http://cms-bucket.nosdn.127.net/c0946c8f883e4232a02cb7086395212020161103173201.png","animation_icon":"","url":"http://ka.play.163.com/shouyou/wap/mobile_game/top/?from=ntes_news_app&rank_type=2"},{"subtitle":"Game Live","title":"游戏直播","imgsrc":"http://img4.cache.netease.com/3g/2015/8/30/20150830193938d48bb.png","animation_icon":"","url":"http://cc.163.com/activity/daily/newsClient/index.html?from=news"}]
     * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348652751993.jpg
     * digest : 在《星际争霸》二十周年来临之际，作为星际历史上最伟大的英雄之一，备受尊敬的菲尼克斯即将来到时空枢纽的战场。曾经的龙骑士再度归来，他渴望战斗，更渴望在《风暴英雄》
     * boardid : game_bbs
     * alias : Games
     * hasAD : 1
     * imgsrc : http://cms-bucket.nosdn.127.net/bdef2955f9034e23a190b375c242583f20180320113643.jpeg
     * ptime : 2018-03-20 09:56:04
     * daynum : 17610
     * hasHead : 1
     * imgType : 1
     * order : 1
     * wap_pluginfo : [{"subtitle":"E-sports","title":"电竞","imgsrc":"http://cms-bucket.nosdn.127.net/dbe321aaf702406ea58e9b477943985220170712142647.png","animation_icon":"","url":"https://c.m.163.com/nc/qa/newsapp/game.html?tid=T1500363322884"},{"subtitle":"Blizzard","title":"暴雪","imgsrc":"http://cms-bucket.nosdn.127.net/0a998be18c9e4a1586291cce06fe166d20170712142752.png","animation_icon":"","url":"https://c.m.163.com/nc/qa/newsapp/game.html?tid=T1500363281827"},{"subtitle":"Mobile games","title":"手游","imgsrc":"http://cms-bucket.nosdn.127.net/52457eba7c0e47f18828f48e43e6bdaa20170712142828.png","animation_icon":"","url":"https://c.m.163.com/nc/qa/newsapp/game.html?tid=T1500363247096"},{"subtitle":"Video games","title":"电玩","imgsrc":"http://cms-bucket.nosdn.127.net/2772fbc2a3fd4650bfdf322fedd7687020170712142940.png","animation_icon":"","url":"https://c.m.163.com/nc/qa/newsapp/game.html?tid=T1500343161337"}]
     * editor : []
     * votecount : 376
     * hasCover : false
     * docid : DDB6A3TJ003198EF
     * tname : 游戏
     * url_3w : http://play.163.com/18/0320/09/DDB6A3TJ003198EF.html
     * priority : 255
     * url : http://3g.163.com/play/18/0320/09/DDB6A3TJ003198EF.html
     * ename : youxi
     * replyCount : 405
     * ltitle : 龙骑士菲尼克斯即将加入《风暴英雄》
     * hasIcon : true
     * subtitle :
     * cid : C1348652751993
     * imgextra : [{"imgsrc":"http://dingyue.nosdn.127.net/f1RiAYHLAZTaRwk94HMPpEwXf0YXWZsCnwAK5A=mZiqAC1521529878073.jpeg"},{"imgsrc":"http://dingyue.nosdn.127.net/AoomeVqW2nnn=pHKMu=yXcsoHxyEKcBSNQOEo7bZR6Ame1521529886546.jpeg"}]
     * skipID : S1514256062745
     * specialID : S1514256062745
     * skipType : special
     * length : 120
     * videosource : 新媒体
     * videoID : VDCDH8F1J
     * TAGS : 视频
     */

    private String template;
    private String lmodify;
    private String source;
    private String postid;
    private String title;
    private String mtime;
    private int hasImg;
    private String topic_background;
    private String digest;
    private String boardid;
    private String alias;
    private int hasAD;
    private String imgsrc;
    private String ptime;
    private String daynum;
    private int hasHead;
    private int imgType;
    private int order;
    private int votecount;
    private boolean hasCover;
    private String docid;
    private String tname;
    private String url_3w;
    private int priority;
    private String url;
    private String ename;
    private int replyCount;
    private String ltitle;
    private boolean hasIcon;
    private String subtitle;
    private String cid;
    private String skipID;
    private String specialID;
    private String skipType;
    private int length;
    private String videosource;
    private String videoID;
    private String TAGS;
    private List<WapPortalBean> wap_portal;
    private List<WapPluginfoBean> wap_pluginfo;
    private List<ImgextraBean> imgextra;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public String getTopic_background() {
        return topic_background;
    }

    public void setTopic_background(String topic_background) {
        this.topic_background = topic_background;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getDaynum() {
        return daynum;
    }

    public void setDaynum(String daynum) {
        this.daynum = daynum;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getTAGS() {
        return TAGS;
    }

    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
    }

    public List<WapPortalBean> getWap_portal() {
        return wap_portal;
    }

    public void setWap_portal(List<WapPortalBean> wap_portal) {
        this.wap_portal = wap_portal;
    }

    public List<WapPluginfoBean> getWap_pluginfo() {
        return wap_pluginfo;
    }

    public void setWap_pluginfo(List<WapPluginfoBean> wap_pluginfo) {
        this.wap_pluginfo = wap_pluginfo;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    public static class WapPortalBean {
        /**
         * subtitle : Top ranking
         * title : 手游热榜
         * imgsrc : http://cms-bucket.nosdn.127.net/c0946c8f883e4232a02cb7086395212020161103173201.png
         * animation_icon :
         * url : http://ka.play.163.com/shouyou/wap/mobile_game/top/?from=ntes_news_app&rank_type=2
         */

        private String subtitle;
        private String title;
        private String imgsrc;
        private String animation_icon;
        private String url;

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getAnimation_icon() {
            return animation_icon;
        }

        public void setAnimation_icon(String animation_icon) {
            this.animation_icon = animation_icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class WapPluginfoBean {
        /**
         * subtitle : E-sports
         * title : 电竞
         * imgsrc : http://cms-bucket.nosdn.127.net/dbe321aaf702406ea58e9b477943985220170712142647.png
         * animation_icon :
         * url : https://c.m.163.com/nc/qa/newsapp/game.html?tid=T1500363322884
         */

        private String subtitle;
        private String title;
        private String imgsrc;
        private String animation_icon;
        private String url;

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getAnimation_icon() {
            return animation_icon;
        }

        public void setAnimation_icon(String animation_icon) {
            this.animation_icon = animation_icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ImgextraBean {
        /**
         * imgsrc : http://dingyue.nosdn.127.net/f1RiAYHLAZTaRwk94HMPpEwXf0YXWZsCnwAK5A=mZiqAC1521529878073.jpeg
         */

        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
