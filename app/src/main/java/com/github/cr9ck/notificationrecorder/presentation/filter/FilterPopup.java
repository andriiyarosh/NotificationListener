package com.github.cr9ck.notificationrecorder.presentation.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.github.cr9ck.notificationrecorder.R;

public class FilterPopup extends PopupWindow {

    private LayoutInflater inflater;
    private View view;
    private FilterClickListener listener;

    public FilterPopup(LayoutInflater inflater, FilterClickListener listener) {
        this.listener = listener;
        this.inflater = inflater;
        this.view = inflater.inflate(R.layout.item_notification, null);
        initView();
    }

    private void initView() {

    }

    public interface FilterClickListener {
        void onAllFiltered();
        void onHourFiltered();
        void onDayFiltered();
        void onMonthFiltered();
    }
}
