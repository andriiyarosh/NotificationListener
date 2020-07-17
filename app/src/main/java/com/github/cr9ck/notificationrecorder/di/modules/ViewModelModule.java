package com.github.cr9ck.notificationrecorder.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.github.cr9ck.notificationrecorder.di.viewmodel.ViewModelFactory;
import com.github.cr9ck.notificationrecorder.di.viewmodel.ViewModelKey;
import com.github.cr9ck.notificationrecorder.presentation.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
