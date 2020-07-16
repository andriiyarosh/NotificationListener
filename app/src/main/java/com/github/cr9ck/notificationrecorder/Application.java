package com.github.cr9ck.notificationrecorder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.github.cr9ck.notificationrecorder.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class Application extends DaggerApplication {

    public static final String NOTIFICATION_SERVICE_ID = "NOTIFICATION_SERVICE_ID";
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static boolean isForegroundServiceRunning;

    public static void setForegroundServiceRunning(boolean running) {
        isForegroundServiceRunning = running;
    }

    public static boolean isForegroundServiceRunning() {
        return isForegroundServiceRunning;
    }

    public boolean isNotificationServiceEnabled(){
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
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
