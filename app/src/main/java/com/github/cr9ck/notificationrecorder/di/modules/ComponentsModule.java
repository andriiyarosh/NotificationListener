package com.github.cr9ck.notificationrecorder.di.modules;

import com.github.cr9ck.notificationrecorder.presentation.MainActivity;
import com.github.cr9ck.notificationrecorder.services.NotificationProcessorService;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ComponentsModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract NotificationProcessorService contributeForegroundService();
}
