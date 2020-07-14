package com.github.cr9ck.notificationrecorder.presentation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.cr9ck.notificationrecorder.R;
import com.github.cr9ck.notificationrecorder.databinding.ActivityMainBinding;
import com.github.cr9ck.notificationrecorder.presentation.filter.FilterView;
import com.github.cr9ck.notificationrecorder.presentation.viewmodel.MainViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements FilterView.FilterSelectedListener {

    private ActivityMainBinding binding;
    private ViewModelProvider.Factory factory;
    private MainViewModel viewModel;
    private FilterView filterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        initFilterView();
        viewModel = new ViewModelProvider(getViewModelStore(), factory).get(MainViewModel.class);
        initRecyclerView();
        initObservers();
        viewModel.filterAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.filterMenu) {
            filterView.show();
            binding.rootView.getForeground().setAlpha(100);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFilterAll() {
        viewModel.filterAll();
    }

    @Override
    public void onFilterHour() {
        viewModel.filterForHour();
    }

    @Override
    public void onFilterDay() {
        viewModel.filterForDay();
    }

    @Override
    public void onFilterMonth() {
        viewModel.filterForMonth();
    }

    @Inject
    public void setFactory(ViewModelProvider.Factory factory) {
        this.factory = factory;
    }

    private void initFilterView() {
        filterView = new FilterView(this, getLayoutInflater(), binding.toolbar.findViewById(R.id.filterMenu));
        filterView.setOnDismissListener(() -> binding.rootView.getForeground().setAlpha(0));
        binding.rootView.setForeground(getDrawable(R.drawable.window_dim));
        binding.rootView.getForeground().setAlpha(0);
    }

    private void initObservers() {
        viewModel.getViewState().observe(this, (state) -> {
            binding.noNotificationGroup.setVisibility(state.isEmpty() ? View.VISIBLE : View.GONE);
            binding.rvList.setVisibility(state.isEmpty() ? View.GONE : View.VISIBLE);
            if (state.isEmpty()) return;
            getAdapter().setItems(state.getNotifications());
            getAdapter().notifyDataSetChanged();
        });
    }

    private void initRecyclerView() {
        binding.rvList.setHasFixedSize(true);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.setAdapter(new NotificationsListAdapter());
    }

    private NotificationsListAdapter getAdapter() {
        return (NotificationsListAdapter) binding.rvList.getAdapter();
    }
}
