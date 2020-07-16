package com.github.cr9ck.notificationrecorder.presentation.viewmodel;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.cr9ck.notificationrecorder.Application;
import com.github.cr9ck.notificationrecorder.model.NotificationModel;
import com.github.cr9ck.notificationrecorder.model.repository.NotificationsRepository;
import com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode;
import com.github.cr9ck.notificationrecorder.presentation.filter.period.FilterPeriodImpl;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.ALL;
import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.DAY;
import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.HOUR;
import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.MONTH;

public class MainViewModel extends ViewModel {

    private NotificationsRepository notificationsRepository;
    private boolean serviceState;

    private CompositeDisposable disposable = new CompositeDisposable();
    private BehaviorSubject<FilterMode> filterTypeObservable = BehaviorSubject.create();

    private MutableLiveData<FilterMode> filteringMode = new MutableLiveData<>();
    private MutableLiveData<List<NotificationModel>> notifications = new MutableLiveData<>();
    private MutableLiveData<Boolean> serviceActive = new MutableLiveData<>();

    @Inject
    public MainViewModel(@NonNull NotificationsRepository notificationsRepository, FilterMode filterMode, boolean isServiceActive) {
        this.notificationsRepository = notificationsRepository;
        serviceState = isServiceActive;
        serviceActive.setValue(serviceState);
        initNotificationFiltering();
        filterTypeObservable.onNext(filterMode);
        Log.d("TEST_TEST", "MainViewModel");
//        addNot();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

    public void filterAll() {
        filterTypeObservable.onNext(ALL);
    }

    public void filterForHour() {
        filterTypeObservable.onNext(HOUR);
    }

    public void filterForDay() {
        filterTypeObservable.onNext(DAY);
    }

    public void filterForMonth() {
        filterTypeObservable.onNext(MONTH);
    }

    public void toggleService() {
        serviceState = !serviceState;
        serviceActive.setValue(serviceState);
        Application.setForegroundServiceRunning(serviceState);
    }

    public LiveData<List<NotificationModel>> getNotifications() {
        return notifications;
    }

    public LiveData<Boolean> getServiceActiveState() {
        return serviceActive;
    }

    public LiveData<FilterMode> getFilteringMode() {
        return filteringMode;
    }

    public void addNot() {
        new Thread(() -> {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.DATE, -1);
            notificationsRepository.addNotification(new NotificationModel(
                    "Date",
                    "test.package.name",
                    "Notificaiton text",
                    c
            ));
        }).start();
    }

    private void initNotificationFiltering() {
        disposable.add(
                filterTypeObservable.subscribeOn(Schedulers.io())
                        .doOnNext(filterMode -> this.filteringMode.postValue(filterMode))
                        .flatMap(this::switchSource)
                        .map(notifications -> {
                            Log.d("TEST_TEST", "initNotificationFiltering");
                            Collections.sort(notifications, (notificationModel, t1) -> t1.getCalendar().compareTo(notificationModel.getCalendar()));
                            return notifications;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((notificationsList -> notifications.setValue(notificationsList)))
        );
    }

    private Observable<List<NotificationModel>> switchSource(FilterMode mode) {
        switch (mode) {
            case HOUR:
                return notificationsRepository.getNotifications(new FilterPeriodImpl(HOUR)).toObservable();
            case DAY:
                return notificationsRepository.getNotifications(new FilterPeriodImpl(DAY)).toObservable();
            case MONTH:
                return notificationsRepository.getNotifications(new FilterPeriodImpl(MONTH)).toObservable();
        }
        return notificationsRepository.getNotifications(new FilterPeriodImpl(ALL)).toObservable();
    }
}
