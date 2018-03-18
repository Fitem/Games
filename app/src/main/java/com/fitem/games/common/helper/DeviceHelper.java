package com.fitem.games.common.helper;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.blankj.utilcode.util.Utils;

import java.util.UUID;

/**
 * Created by Fitem on 2017/10/24.
 */

public class DeviceHelper {

    public static TelephonyManager getTM() {
        TelephonyManager tm = (TelephonyManager) Utils.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm;
    }

    public static String getSimSerialNumber() {
        return getTM().getSimSerialNumber();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

}
