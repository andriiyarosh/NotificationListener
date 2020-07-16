package com.github.cr9ck.notificationrecorder.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.github.cr9ck.notificationrecorder.services.NotificationProcessorService;

import java.util.Objects;

import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_APP_NAME;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_CALENDAR;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_ICON;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_TEXT;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String ActionNotificationReceived = "com.github.cr9ck.notificationrecorder.receiver.NotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ActionNotificationReceived)) {
            Intent processorService = new Intent(context, NotificationProcessorService.class);

            processorService.putExtra(EXTRA_NOTIFICATION_ICON, (Bitmap)intent.getParcelableExtra(EXTRA_NOTIFICATION_ICON));
            processorService.putExtra(EXTRA_NOTIFICATION_APP_NAME, intent.getStringExtra(EXTRA_NOTIFICATION_APP_NAME));
            processorService.putExtra(EXTRA_NOTIFICATION_TEXT, intent.getStringExtra(EXTRA_NOTIFICATION_TEXT));
            processorService.putExtra(EXTRA_NOTIFICATION_CALENDAR, intent.getSerializableExtra(EXTRA_NOTIFICATION_CALENDAR));
            NotificationProcessorService.enqueueWork(context, processorService);
        }
    }
}
