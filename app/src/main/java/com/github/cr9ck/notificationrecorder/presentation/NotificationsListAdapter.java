package com.github.cr9ck.notificationrecorder.presentation;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.cr9ck.notificationrecorder.R;
import com.github.cr9ck.notificationrecorder.model.NotificationModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationsListAdapter extends RecyclerView.Adapter<NotificationsListAdapter.NotificationViewHolder> {

    private List<NotificationModel> notifications = new ArrayList<>();

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.setNotification(notifications.get(position));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public void setItems(List<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {

        private View view;

        public NotificationViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
        }

        public void setNotification(NotificationModel notification) {
            try {
                PackageManager packageManager = view.getContext().getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(notification.getAppPackageName(), 0);
                Drawable icon = packageInfo.applicationInfo.loadIcon(packageManager);
                ((ImageView)view.findViewById(R.id.icon)).setImageDrawable(icon);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            ((TextView)view.findViewById(R.id.title)).setText(notification.getAppName());
            ((TextView)view.findViewById(R.id.text)).setText(notification.getText());
            ((TextView)view.findViewById(R.id.time)).setText(notification.getTime());
            ((TextView)view.findViewById(R.id.date)).setText(notification.getDay());
        }
    }
}
