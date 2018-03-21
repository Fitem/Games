package com.fitem.games.api;

/**
 * Created by Fitem on 2017/3/14.
 */

public class ApiConstants {

    /**
     * 新闻资讯
     * http://c.3g.163.com/nc/article/list/T1348654151579/0-20.html
     * http://c.3g.163.com/nc/article/DCUAJVQC00318QE9/full.html
     */

    public static final String NEWS_HOST = "http://c.3g.163.com/nc/article/";
    public static final String NEWS_GAMES_TYPE = "T1348654151579";
    public static final String NEWS_HOT_TYPE = "T1348647909107";

    /**
     * 美女图片
     * http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1
     */

    public static final String GRILS_HOST = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    /**
     * TYPE: lol、dnf、pubg
     * http://api.maxjia.com/api/live/list/?offset=0&limit=20&live_type=&game_type=lol
     * http://api.maxjia.com/api/live/detail/?live_type=quanmin&live_id=666&game_type=lol
     */

    public static final String LIVE_HOST = "http://api.maxjia.com/api/live/";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String URL = NEWS_HOST;
        switch (hostType) {
            case HostType.NEWS_HOST:
                URL = NEWS_HOST;
                break;
            case HostType.GRILS_HOST:
                URL = GRILS_HOST;
                break;
            case HostType.LIVE_HOST:
                URL = LIVE_HOST;
                break;
        }
        return URL;
    }

}
