package com.github.cr9ck.notificationrecorder.services;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.github.cr9ck.notificationrecorder.receiver.NotificationReceiver;

public class NotificationListener extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Intent intent = new Intent();
        intent.setAction(NotificationReceiver.ActionNotificationReceived);
        sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
