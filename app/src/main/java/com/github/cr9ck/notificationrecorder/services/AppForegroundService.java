package com.github.cr9ck.notificationrecorder.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.github.cr9ck.notificationrecorder.Application;
import com.github.cr9ck.notificationrecorder.R;
import com.github.cr9ck.notificationrecorder.presentation.MainActivity;
import com.github.cr9ck.notificationrecorder.receiver.NotificationReceiver;

import java.util.Calendar;

import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_APP_NAME;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_CALENDAR;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_ICON;
import static com.github.cr9ck.notificationrecorder.services.NotificationProcessorService.EXTRA_NOTIFICATION_TEXT;

public class AppForegroundService extends Service {

    private NotificationReceiver notificationReceiver = new NotificationReceiver();
    private static final int SERVICE_NOTIFICATION_ID = 4312;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NotificationReceiver.ActionNotificationReceived);
        registerReceiver(notificationReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification serviceNotification = getNotification();
        startForeground(SERVICE_NOTIFICATION_ID, serviceNotification);
        sendNot();
        return START_NOT_STICKY;
    }

    private Notification getNotification() {
        Intent contentIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, contentIntent, 0);
        return new NotificationCompat.Builder(this, Application.NOTIFICATION_SERVICE_ID)
                .setSmallIcon(R.drawable.custom_radio_pulp)
                .setContentTitle(getString(R.string.app_name))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentIntent(pendingIntent)
                .build();
    }

    private void sendNot() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.DATE, -1);

        Intent receiverIntent = new Intent(this, NotificationReceiver.class);
        receiverIntent.setAction(NotificationReceiver.ActionNotificationReceived);
//        BitmapFactory.decodeResource(getResources(),
//                R.drawable.custom_radio_pulp);
//        receiverIntent.putExtra(EXTRA_NOTIFICATION_ICON, getDrawable(R.drawable.custom_radio_pulp)));
        receiverIntent.putExtra(EXTRA_NOTIFICATION_APP_NAME, "AppName");
        receiverIntent.putExtra(EXTRA_NOTIFICATION_TEXT, "SomeText");
        receiverIntent.putExtra(EXTRA_NOTIFICATION_CALENDAR, c);

        sendBroadcast(receiverIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(notificationReceiver);
    }
}
