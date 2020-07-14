package com.github.cr9ck.notificationrecorder.model.repository;

import androidx.annotation.NonNull;

import com.github.cr9ck.notificationrecorder.model.NotificationModel;
import com.github.cr9ck.notificationrecorder.presentation.filter.period.FilterPeriod;

import java.util.List;

import io.reactivex.Flowable;

public interface NotificationsRepository {

    void addNotification(NotificationModel notificationModel);
    void addNotification(List<NotificationModel> notificationModel);
    void deleteNotifications();
    @NonNull
    Flowable<List<NotificationModel>> getNotifications(FilterPeriod period);
}
