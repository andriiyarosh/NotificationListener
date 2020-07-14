package com.github.cr9ck.notificationrecorder.presentation.viewmodel;

import com.github.cr9ck.notificationrecorder.model.NotificationModel;
import com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode;

import java.util.List;

public class MainViewState {
    private List<NotificationModel> notifications;
    private FilterMode filterMode;

    public MainViewState(List<NotificationModel> notifications, FilterMode filterMode) {
        this.notifications = notifications;
        this.filterMode = filterMode;
    }

    public List<NotificationModel> getNotifications() {
        return notifications;
    }

    public void setPeriod(FilterMode filterMode) {
        this.filterMode = filterMode;
    }

    public FilterMode getFilterMode() {
        return filterMode;
    }

    public boolean isEmpty() {
        return notifications.size() == 0;
    }
}
