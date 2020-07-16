package com.github.cr9ck.notificationrecorder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.github.cr9ck.notificationrecorder.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class Application extends DaggerApplication {

    public static final String NOTIFICATION_SERVICE_ID = "NOTIFICATION_SERVICE_ID";
    private static boolean isForegroundServiceRunning;

    public static void setForegroundServiceRunning(boolean running) {
        isForegroundServiceRunning = running;
    }

    public static boolean isIsForegroundServiceRunning() {
        return isForegroundServiceRunning;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceNotificationChannel = new NotificationChannel(
                    NOTIFICATION_SERVICE_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceNotificationChannel);
        }
    }
}
