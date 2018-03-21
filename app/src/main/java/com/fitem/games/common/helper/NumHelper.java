package com.fitem.games.common.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Fitem on 2017/8/8.
 */

public class NumHelper {

    public static final int MILLION = 1000000;
    public static final int THOUAND = 1000;
    private static DecimalFormat acronymDecimal = new DecimalFormat(",###");
    private static DecimalFormat twoDecimalDecimal = new DecimalFormat("0.00");

    /**
     * 数字转换成K M
     *
     * @param number
     * @return
     */
    public static String toAcronymKM(String number) {
        int money = Integer.valueOf(number);
        return toAcronymKM(money);
    }

    /**
     * 数字转换成K M
     *
     * @param number
     * @return
     */
    public static String toAcronymKM(int number) {
        return toAcronymKM((long) number);
    }

    public static String toAcronymKM(long number) {
        String moneyStr;
        long money = number;
        if (money >= 10 * MILLION) {
            moneyStr = money / MILLION + "M";
        } else if (money >= THOUAND * 10) {
            moneyStr = money / THOUAND + "K";
        } else {
            moneyStr = String.valueOf(money);
        }
        return moneyStr;
    }

    /**
     * 数字转换成K M
     *
     * @param number
     * @return
     */
    public static String toFormatAcronym(String number) {
        int money = Integer.valueOf(number);
        return toFormatAcronym(money);
    }

    /**
     * 数字转换成K M
     *
     * @param money
     * @return
     */
    public static String toFormatAcronym(int money) {
        String moneyStr;
        if (money >= 100 * MILLION) {
            moneyStr = toFormatNum(money / MILLION) + "M";
        } else if (money >= THOUAND * 100) {
            moneyStr = toFormatNum(money / THOUAND) + "K";
        } else {
            moneyStr = toFormatNum(money);
        }
        return moneyStr;
    }

    /**
     * 数字转换成K M
     *
     * @param money
     * @return
     */
    public static String toFormatAcronym(long money) {
        String moneyStr;
        if (money >= 100 * MILLION) {
            moneyStr = toFormatNum(money / MILLION) + "M";
        } else if (money >= THOUAND * 100) {
            moneyStr = toFormatNum(money / THOUAND) + "K";
        } else {
            moneyStr = toFormatNum(money);
        }
        return moneyStr;
    }

    /**
     * 转换成格式化数字
     *
     * @param number
     * @return
     */
    public static String toFormatNum(int number) {
        return acronymDecimal.format(number);
    }

    /**
     * 转换成格式化数字(100000 --> 100,000)
     *
     * @param number
     * @return
     */
    public static String toFormatNum(String number) {
        int num = Integer.valueOf(number);
        return toFormatNum(num);
    }

    /**
     * 转换成格式化数字(100000 --> 100,000)
     *
     * @param number
     * @return
     */
    public static String toFormatNum(long number) {
        return acronymDecimal.format(number);
    }

    public static String toTwoDecimalPlaces(double odds) {
        return twoDecimalDecimal.format(odds);
    }

    /**
     * BigDecimal解析double数据（默认：BigDecimal.ROUND_HALF_UP）
     * @param values 传入的double值
     * @param newScale 小数点位数
     * @return
     */
    public static String toBigDecimal(double values, int newScale) {
        BigDecimal bigDecimal = new BigDecimal(values).setScale(newScale, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }
}
