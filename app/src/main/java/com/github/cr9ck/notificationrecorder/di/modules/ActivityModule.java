package com.github.cr9ck.notificationrecorder.di.modules;

import com.github.cr9ck.notificationrecorder.presentation.MainActivity;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
