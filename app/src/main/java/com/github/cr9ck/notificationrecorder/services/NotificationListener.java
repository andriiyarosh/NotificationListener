package com.github.cr9ck.notificationrecorder.services;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.github.cr9ck.notificationrecorder.receiver.NotificationReceiver;

import java.util.Calendar;

import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_APP_NAME;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_APP_PACKAGE_NAME;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_CALENDAR;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_TEXT;


public class NotificationListener extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        sbn.getNotification().getSmallIcon();

        String appName = "";
        String text = "";
        String notificationTitle = sbn.getNotification().extras.getString(Notification.EXTRA_TITLE);
        String notificationText = sbn.getNotification().extras.getString(Notification.EXTRA_TEXT);
        if (notificationTitle != null)
            text += notificationTitle + " ";
        if (notificationText != null)
            text += notificationText;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sbn.getPostTime());
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(sbn.getPackageName(), 0);
            appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(NotificationReceiver.ACTION_NOTIFICATION_RECEIVED);
        intent.putExtra(EXTRA_NOTIFICATION_APP_NAME, appName);
        intent.putExtra(EXTRA_NOTIFICATION_APP_PACKAGE_NAME, sbn.getPackageName());
        intent.putExtra(EXTRA_NOTIFICATION_CALENDAR, calendar);
        intent.putExtra(EXTRA_NOTIFICATION_TEXT, text);
        sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
