package com.xtreme.leowallet.repository;

import android.support.annotation.NonNull;

import com.xtreme.leowallet.model.AccountInfo;

import java.io.InputStream;

import io.reactivex.Observable;


public interface DataRepositoryImpl {

    @NonNull
    Observable<AccountInfo> getAccountInfo(InputStream inputStream);
}
