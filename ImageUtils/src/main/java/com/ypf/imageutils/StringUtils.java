package com.ypf.imageutils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * string处理
 * Created by guchenkai on 2015/11/24.
 */
public final class StringUtils {

    /**
     * 字符串判空
     *
     * @param target 目标
     * @return 是否为空
     */
    public static boolean isEmpty(String target) {
        if (TextUtils.isEmpty(target) || TextUtils.equals("null", target))
            return true;
        return false;
    }

    /**
     * 判断两个字符串是否相同
     *
     * @param target1 目标1
     * @param target2 目标2
     * @return 是否相同
     */
    public static boolean equals(String target1, String target2) {
        return TextUtils.equals(target1, target2);
    }

    /**
     * 获得UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 匹配正则表达式
     *
     * @param text  被匹配文本
     * @param regex 正则表达式
     * @return 是否匹配
     */
    public static boolean checkRegex(final String text, final String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    /**
     * 验证邮箱
     *
     * @param email 电子邮箱地址
     * @return 是否匹配
     */
    public static boolean checkEmailFormat(String email) {
        return checkRegex(email,
                "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    }

    /**
     * 验证手机号码
     *
     * @param mobile 手机号码
     * @return 是否匹配
     */
    public static boolean checkMobileFormat(String mobile) {
        return checkRegex(mobile, "^[1][3,4,5,7,8][0-9]{9}$");
    }

    /**
     * 验证密码(6到16位)
     *
     * @param password 密码文本
     * @return 是否匹配
     */
    public static boolean checkPasswordFormat(String password) {
        return checkRegex(password, "[A-Z0-9a-z]{6,16}");
    }

    /**
     * 查字符串是否为数字型字符串
     *
     * @param target 目标
     * @return 是否匹配
     */
    public static boolean isNumeric(String target) {
        if (StringUtils.isEmpty(target)) return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern != null ? pattern.matcher(target) : null;
        if (isNum != null && !isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 格式化距离
     *
     * @param distance 距离(单位米)
     */
    public static String formatDistance(float distance) {
        String result = "";
        int d = (int) distance;
        if (d >= 1000) {
            double dis = ((double) d) / 1000;
            BigDecimal bd = new BigDecimal(dis);
            bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
            String[] rs = bd.toString().split("\\.");
            if (StringUtils.equals("0", rs[1]))
                result = rs[0] + "公里";
            else
                result = bd.toString() + "公里";
        } else if (d < 1000) {
            result = d + "米";
        }
        return result;
    }
}
