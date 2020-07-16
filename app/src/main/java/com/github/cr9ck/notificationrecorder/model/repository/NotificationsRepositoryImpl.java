package com.github.cr9ck.notificationrecorder.model.repository;

import androidx.annotation.NonNull;

import com.github.cr9ck.notificationrecorder.model.database.NotificationEntity;
import com.github.cr9ck.notificationrecorder.model.NotificationModel;
import com.github.cr9ck.notificationrecorder.model.database.NotificationsDao;
import com.github.cr9ck.notificationrecorder.model.mapper.TypeMapper;
import com.github.cr9ck.notificationrecorder.presentation.filter.period.FilterPeriod;

import java.util.List;

import io.reactivex.Flowable;

public class NotificationsRepositoryImpl implements NotificationsRepository {

    private TypeMapper<NotificationModel, NotificationEntity> mapper;
    private NotificationsDao notificationsDao;

    public NotificationsRepositoryImpl(
            TypeMapper<NotificationModel, NotificationEntity> mapper,
            NotificationsDao notificationsDao) {
        this.mapper = mapper;
        this.notificationsDao = notificationsDao;
    }

    @Override
    public void addNotification(NotificationModel notificationModel) {
        notificationsDao.insertNotification(mapper.mapToEntity(notificationModel));
    }

    @Override
    public void addNotification(List<NotificationModel> notificationModels) {
        notificationsDao.insertNotification(mapper.mapToEntity(notificationModels));
    }

    @Override
    public void deleteNotifications() {
        notificationsDao.deleteNotifications();
    }

    @NonNull
    @Override
    public Flowable<List<NotificationModel>> getNotifications(FilterPeriod period) {
        return notificationsDao.getNotification(period.getStartPeriod()).map(notificationEntities -> mapper.mapToModel(notificationEntities));
    }
}
