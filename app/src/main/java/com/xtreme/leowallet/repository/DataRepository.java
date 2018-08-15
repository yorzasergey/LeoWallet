package com.xtreme.leowallet.repository;

import android.support.annotation.NonNull;

import com.xtreme.leowallet.model.AccountInfo;

import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DataRepository implements DataRepositoryImpl {

    @NonNull
    @Override
    public Observable<AccountInfo> getAccountInfo(InputStream inputStream) {

        return Observable.fromCallable(new CallableAccountInfo(inputStream))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<AccountInfo, ObservableSource<AccountInfo>>() {
                    @Override
                    public ObservableSource<AccountInfo> apply(AccountInfo accountInfo) throws Exception {
                        return Observable.just(accountInfo);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<AccountInfo>>() {
                    @Override
                    public ObservableSource<AccountInfo> apply(Throwable throwable) throws Exception {

                        return Observable.error(throwable);
                    }
                });
    }
}
