package com.treynb.cnode.util;

import org.joda.time.DateTime;

import java.util.Date;

import cz.msebera.android.httpclient.util.TextUtils;

public class FormatUtil {
    private static final long minute = 60 * 1000;
    private static final long hour = 60 * minute;
    private static final long day = 24 * hour;
    private static final long week = 7 * day;
    private static final long month = 31 * day;
    private static final long year = 12 * month;

    public static String dateToTimeBefore(DateTime date) {
        if (null == date) {
            return "";
        }

        long diff = new Date().getTime() - date.getMillis();

        if (diff > year) {
            return (diff / year) + "年前";
        }
        if (diff > month) {
            return (diff / month) + "个月前";
        }
        if (diff > week) {
            return (diff / week) + "周前";
        }
        if (diff > day) {
            return (diff / day) + "天前";
        }
        if (diff > hour) {
            return (diff / hour) + "小时前";
        }
        if (diff > minute) {
            return (diff / minute) + "分钟前";
        }
        return "刚刚";
    }

    public static String contentToHTML(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        content = content.replace("<a href=\"/user/", "<a href=\"https://cnodejs.org/user/");
        content = content.replace("<img src=\"//", "<img src=\"https://");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><meta charset=\"utf-8\"><link href=\"https://dn-cnodestatic.qbox.me/public/stylesheets/index.min.36689462.min.css\" type=\"text/css\" rel=\"stylesheet\"/><style>body{background-color:white;}</style></head><body>");
        sb.append(content);
        sb.append("</body></html>");

        return sb.toString();
    }

    public static String getCompatAvatarUrl(String avatarUrl) {
        if (!TextUtils.isEmpty(avatarUrl) && avatarUrl.startsWith("//gravatar.com/avatar/")) {
            return "http:" + avatarUrl;
        } else {
            return avatarUrl;
        }
    }
}
