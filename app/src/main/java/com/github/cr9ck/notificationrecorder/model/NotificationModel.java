package com.github.cr9ck.notificationrecorder.model;

import android.graphics.Bitmap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotificationModel {

    private String appName;
    private String text;
    private Calendar calendar;
    private Bitmap icon;

    public NotificationModel(String appName, String text, Calendar calendar, Bitmap icon) {
        this.appName = appName;
        this.text = text;
        this.calendar = calendar;
        this.icon = icon;
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

    public Bitmap getIcon() {
        return icon;
    }
}
