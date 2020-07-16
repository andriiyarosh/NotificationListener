package com.github.cr9ck.notificationrecorder.services;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.github.cr9ck.notificationrecorder.model.NotificationModel;
import com.github.cr9ck.notificationrecorder.model.repository.NotificationsRepository;

import java.util.Calendar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class NotificationProcessorService extends JobIntentService {

    private NotificationsRepository notificationsRepository;

    public static final String EXTRA_NOTIFICATION_ICON = "EXTRA_NOTIFICATION_ICON";
    public static final String EXTRA_NOTIFICATION_APP_NAME = "EXTRA_NOTIFICATION_TITLE";
    public static final String EXTRA_NOTIFICATION_TEXT= "EXTRA_NOTIFICATION_TEXT";
    public static final String EXTRA_NOTIFICATION_CALENDAR= "EXTRA_NOTIFICATION_CALENDAR";

    public static void enqueueWork(Context context, Intent serviceIntent) {
        enqueueWork(context, NotificationProcessorService.class, 111, serviceIntent);
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("NotificationService", "onHandleWork");

        NotificationModel notification = new NotificationModel(
                intent.getStringExtra(EXTRA_NOTIFICATION_APP_NAME),
                intent.getStringExtra(EXTRA_NOTIFICATION_TEXT),
                (Calendar) intent.getSerializableExtra(EXTRA_NOTIFICATION_CALENDAR),
                (Bitmap)intent.getParcelableExtra(EXTRA_NOTIFICATION_ICON)
        );
        notificationsRepository.addNotification(notification);
    }

    @Inject
    public void setNotificationsRepository(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }
}
