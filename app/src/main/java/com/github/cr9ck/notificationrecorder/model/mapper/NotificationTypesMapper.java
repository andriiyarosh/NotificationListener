package com.github.cr9ck.notificationrecorder.model.mapper;

import com.github.cr9ck.notificationrecorder.model.NotificationModel;
import com.github.cr9ck.notificationrecorder.model.database.NotificationEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationTypesMapper implements TypeMapper<NotificationModel, NotificationEntity> {

    @Override
    public NotificationEntity mapToEntity(NotificationModel model) {
        return new NotificationEntity(
                model.getAppName(),
                model.getAppPackageName(),
                model.getText(),
                model.getTimestamp()
        );
    }

    @Override
    public NotificationModel mapToModel(NotificationEntity entity) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(entity.getTime());
        return new NotificationModel(
                entity.getAppName(),
                entity.getAppPackageName(),
                entity.getNotificationText(),
                calendar
        );
    }

    @Override
    public List<NotificationEntity> mapToEntity(List<NotificationModel> models) {
        List<NotificationEntity> result = new ArrayList<>();
        for (NotificationModel model : models) {
            result.add(mapToEntity(model));
        }
        return result;
    }

    @Override
    public List<NotificationModel> mapToModel(List<NotificationEntity> entities) {
        List<NotificationModel> result = new ArrayList<>();
        for (NotificationEntity entity : entities) {
            result.add(mapToModel(entity));
        }
        return result;
    }
}
