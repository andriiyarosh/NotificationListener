package com.github.cr9ck.notificationrecorder.model.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static androidx.room.ColumnInfo.BLOB;

@Entity(tableName = "Notifications")
public class NotificationEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String appName;
    private String notificationText;

    @ColumnInfo(typeAffinity = BLOB)
    private byte[] icon;

    @ColumnInfo(name = "timeStamp")
    private long time;

    public NotificationEntity(String appName, String notificationText, byte[] icon, long time) {
        this.appName = appName;
        this.notificationText = notificationText;
        this.icon = icon;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
