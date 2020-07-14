package com.github.cr9ck.notificationrecorder.presentation.viewmodel;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.cr9ck.notificationrecorder.model.NotificationModel;
import com.github.cr9ck.notificationrecorder.model.repository.NotificationsRepository;
import com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode;
import com.github.cr9ck.notificationrecorder.presentation.filter.period.FilterPeriodImpl;

import java.util.Calendar;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.ALL;
import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.DAY;
import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.HOUR;
import static com.github.cr9ck.notificationrecorder.presentation.filter.FilterMode.MONTH;

public class MainViewModel extends ViewModel {

    private @NonNull
    NotificationsRepository notificationsRepository;
    private @NonNull
    PublishSubject<FilterMode> subject = PublishSubject.create();
    private MutableLiveData<MainViewState> viewState = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public MainViewModel(@NonNull NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
        initState();
//        addNot();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

    public void filterAll() {
        subject.onNext(ALL);
    }

    public void filterForHour() {
        subject.onNext(HOUR);
    }

    public void filterForDay() {
        subject.onNext(DAY);
    }

    public void filterForMonth() {
        subject.onNext(MONTH);
    }

    public LiveData<MainViewState> getViewState() {
        return viewState;
    }

    public void addNot() {
        new Thread(() -> {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.DATE, -1);
            notificationsRepository.addNotification(new NotificationModel(
                    "Date",
                    "Notificaiton text",
                    c,
                    Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8)
            ));
        }).start();
    }

    private void initState() {
        disposable.add(
                subject.subscribeOn(Schedulers.io())
                        .flatMap(this::switchSource)
                        .map(mainViewState -> {
                            Collections.sort(mainViewState.getNotifications(), (notificationModel, t1) -> t1.getCalendar().compareTo(notificationModel.getCalendar()));
                            return mainViewState;
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((state -> viewState.setValue(state)))
        );
    }

    private Observable<MainViewState> switchSource(FilterMode mode) {
        switch (mode) {
            case HOUR:
                return notificationsRepository.getNotifications(new FilterPeriodImpl(HOUR))
                        .map(notificationModels -> new MainViewState(notificationModels, mode))
                        .toObservable();
            case DAY:
                return notificationsRepository.getNotifications(new FilterPeriodImpl(DAY))
                        .map(notificationModels -> new MainViewState(notificationModels, mode))
                        .toObservable();
            case MONTH:
                return notificationsRepository.getNotifications(new FilterPeriodImpl(MONTH))
                        .map(notificationModels -> new MainViewState(notificationModels, mode))
                        .toObservable();
        }
        return notificationsRepository.getNotifications()
                .map(notificationModels -> new MainViewState(notificationModels, mode))
                .toObservable();
    }
}
