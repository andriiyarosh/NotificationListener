package com.github.cr9ck.notificationrecorder.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotificationModel implements Serializable {

    private String appName;
    private String appPackageName;
    private String text;
    private Calendar calendar;

    public NotificationModel(String appName, String appPackageName, String text, Calendar calendar) {
        this.appName = appName;
        this.appPackageName = appPackageName;
        this.text = text;
        this.calendar = calendar;
    }

    public String getAppName() {
        return appName;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(calendar.getTime());
    }

    public String getDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(calendar.getTime());
    }

    public long getTimestamp() {
        return calendar.getTimeInMillis();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getAppPackageName() {
        return appPackageName;
    }
}
