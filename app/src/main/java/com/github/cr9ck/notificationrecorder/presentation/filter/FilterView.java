package com.github.cr9ck.notificationrecorder.presentation.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.github.cr9ck.notificationrecorder.R;

public class FilterView extends PopupWindow {

    private View root;
    private FilterSelectedListener listener;

    public FilterView(@NonNull FilterSelectedListener listener,
                      @NonNull LayoutInflater inflater,
                      @NonNull View root) {
        super(inflater.inflate(R.layout.filter_view, null), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        this.root = root;
        this.listener = listener;
        initFilterOption();
        initListener();
    }

    public void show() {
        showAsDropDown(root);
    }

    private void initFilterOption() {
        ((RadioButton) getContentView().findViewById(R.id.filterAll)).setChecked(true);
    }

    private void initListener() {
        ((RadioGroup) getContentView().findViewById(R.id.filterGroup)).setOnCheckedChangeListener((radioGroup, id) -> {
            switch (id) {
                case R.id.filterAll:
                    if (listener == null) return;
                    listener.onFilterAll();
                    break;
                case R.id.filterHour:
                    if (listener == null) return;
                    listener.onFilterHour();
                    break;
                case R.id.filterDay:
                    if (listener == null) return;
                    listener.onFilterDay();
                    break;
                case R.id.filterMonth:
                    if (listener == null) return;
                    listener.onFilterMonth();
                    break;
                default:
                    break;
            }
            dismiss();
        });
    }

    public interface FilterSelectedListener {
        void onFilterAll();

        void onFilterHour();

        void onFilterDay();

        void onFilterMonth();
    }
}
