package com.github.cr9ck.notificationrecorder.di.modules;

import android.content.Context;

import com.github.cr9ck.notificationrecorder.model.database.NotificationsDatabase;
import com.github.cr9ck.notificationrecorder.model.mapper.NotificationTypesMapper;
import com.github.cr9ck.notificationrecorder.model.repository.NotificationsRepository;
import com.github.cr9ck.notificationrecorder.model.repository.NotificationsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    @Singleton
    public NotificationsDatabase provideDatabase(Context context){
        return NotificationsDatabase.getInstance(context);
    }

    @Provides
    @Singleton
    public NotificationsRepository provideRepository(NotificationsDatabase notificationsDatabase) {
        return new NotificationsRepositoryImpl(
                new NotificationTypesMapper(),
                notificationsDatabase.notificationsDao()
        );
    }
}
