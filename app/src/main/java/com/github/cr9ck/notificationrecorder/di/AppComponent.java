package com.github.cr9ck.notificationrecorder.di;

import android.content.Context;

import com.github.cr9ck.notificationrecorder.Application;
import com.github.cr9ck.notificationrecorder.di.modules.ActivityModule;
import com.github.cr9ck.notificationrecorder.di.modules.ModelModule;
import com.github.cr9ck.notificationrecorder.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ViewModelModule.class, ModelModule.class, ActivityModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<Application> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }
}
