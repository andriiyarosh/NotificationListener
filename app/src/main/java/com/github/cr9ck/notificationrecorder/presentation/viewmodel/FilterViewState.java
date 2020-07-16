package com.github.cr9ck.notificationrecorder.presentation.viewmodel;

import com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode;

public class FilterViewState {
    private FilterMode filterMode;
    private boolean isVisible;

    public FilterViewState(FilterMode filterMode, boolean isVisible) {
        this.filterMode = filterMode;
        this.isVisible = isVisible;
    }

    public FilterMode getFilterMode() {
        return filterMode;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
