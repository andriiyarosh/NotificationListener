package com.github.cr9ck.notificationrecorder.model.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notifications")
public class NotificationEntity {

    @PrimaryKey
    private @NonNull
    String id;
    private String appName;
    private String appPackageName;
    private String notificationText;

    @ColumnInfo(name = "timeStamp")
    private long time;

    public NotificationEntity(String id, String appName, String appPackageName, String notificationText, long time) {
        this.id = id;
        this.appName = appName;
        this.appPackageName = appPackageName;
        this.notificationText = notificationText;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }
}
