package com.fitem.games.common.helper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

/**
 * APP相关帮助类
 * Created by Fitem on 2017/7/7.
 */

public class AppHelper {
    private static final String FB_Domain = "www.facebook.com";
    private static final String Line_Domain = "line.me";
    private static final String Instagram_Domain = "www.instagram.com";
    private static final String Youtube_Domain = "www.youtube.com";
    private static String appPageId;
    private static String app_url;

    /**
     * 跳转到应用市场
     *
     * @param context
     */
    public static void toAppPlay(Context context) {
        //这里开始执行一个应用市场跳转逻辑，默认this为Context上下文对象
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + context.getPackageName())); //跳转到应用市场，非Google Play市场一般情况也实现了这个接口
//        intent.setData(Uri.parse("market://details?id=" + "com.zs.fls")); //跳转到应用市场，非Google Play市场一般情况也实现了这个接口
        //存在手机里没安装应用市场的情况，跳转会包异常，做一个接收判断
        if (intent.resolveActivity(context.getPackageManager()) != null) { //可以接收
            context.startActivity(intent);
        } else { //没有应用市场，我们通过浏览器跳转到Google Play
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName()));
            //这里存在一个极端情况就是有些用户浏览器也没有，再判断一次
            if (intent.resolveActivity(context.getPackageManager()) != null) { //有浏览器
                context.startActivity(intent);
            } else { //天哪，这还是智能手机吗？
//                Toast.makeText(context, R.string.not_browser_tip, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 直接跳转到google play
     *
     * @param context
     */
    public static void toGooglePlay(Context context) {
        String appPackageName = context.getPackageName();
        appPackageName = appPackageName.equals("com.thai.fitem") ? "com.zsft.thai" : appPackageName;
        // 通过包名获取要跳转的app，创建intent对象
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.android.vending");
        // 这里如果intent为空，就说明没有安装要跳转的应用嘛
        if (launchIntent != null) {
            // package name and activity
            ComponentName comp = new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity");
            launchIntent.setComponent(comp);
            launchIntent.setData(Uri.parse("market://details?id=" + appPackageName));
            context.startActivity(launchIntent);
        } else { //没有Google Play应用的情况下，我们通过浏览器跳转到Google Play
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName));
            //这里存在一个极端情况就是有些用户浏览器也没有，再判断一次
            if (intent.resolveActivity(context.getPackageManager()) != null) { //有浏览器
                context.startActivity(intent);
            } else { //天哪，这还是智能手机吗？
//                Toast.makeText(context, R.string.not_browser_tip, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 根据链接跳转到第三方APP
     *
     * @param context
     * @param url
     */
    public static void toThirdApp(Context context, String url) {
        if (!url.startsWith("http")) {
            url = "https://" + url;
        }
        // FB登录
        if (url.contains(FB_Domain)) {
            String[] split = url.split(FB_Domain);
            appPageId = split[split.length - 1];
            app_url = getFBPageURL(context, url, appPageId);
            toStartApp(context, app_url);
        } else if (url.contains(Instagram_Domain)) {
            String packageStr = "com.instagram.android";
            String[] split = url.split(Instagram_Domain);
            appPageId = split[split.length - 1];
            startAppByUrl(context, packageStr, "https://www.instagram.com/_u/" + appPageId);
        } else if (url.contains(Youtube_Domain)) {
            String packageStr = "com.google.android.youtube";
            startAppByUrl(context, packageStr, url);
        } else if (url.contains(Line_Domain)) {
            String packageStr = "jp.naver.line.android";
            startAppByUrl(context, packageStr, url);
        } else {
            // 网页跳转
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)));
        }
    }


    /**
     * 通用跳转APP
     *
     * @param context
     * @param packageStr
     * @param url
     */
    private static void startAppByUrl(Context context, String packageStr, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage(packageStr);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)));
        }
    }

    /**
     * 跳转App
     *
     * @param context
     * @param url
     */
    private static void toStartApp(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    //这个方法是为了生成标准的可用于跳转的Facebook url
    private static String getFBPageURL(Context context, String url, String appPageId) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //新版本的Facebook
                return "fb://facewebmodal/f?href=" + appPageId;
            } else { //旧版本的Facebook
                return "fb://page/" + appPageId;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return url; //要是没有安装就用普通的url
        }
    }

    /**
     * 启动app设置授权界面
     *
     * @param context
     */
    public static void startSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }

    /**
     * 跳转到应用市场
     *
     * @param context
     */
    public static void toThirdBrowser(Context context, Uri uri) {
        // 这里开始执行一个应用市场跳转逻辑，默认this为Context上下文对象
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 通过浏览器跳转
        intent.setData(uri);
        //这里存在一个极端情况就是有些用户浏览器也没有，再判断一次
        if (intent.resolveActivity(context.getPackageManager()) != null) { //有浏览器
//            context.startActivity(Intent.createChooser(intent, context.getString(R.string.select_browser)));
        } else { //天哪，这还是智能手机吗？
//            Toast.makeText(context, R.string.not_browser_tip, Toast.LENGTH_SHORT).show();
        }
    }
}

