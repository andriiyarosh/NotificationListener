package com.github.cr9ck.notificationrecorder.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NotificationsDao {

    @Query("SELECT * FROM Notifications WHERE timeStamp >= :start")
    Flowable<List<NotificationEntity>> getNotification(long start);

    @Query("DELETE FROM Notifications")
    void deleteNotifications();

    @Insert
    void insertNotification(NotificationEntity notification);

    @Insert
    void insertNotification(List<NotificationEntity> notification);
}
