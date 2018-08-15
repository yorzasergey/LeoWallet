package com.xtreme.leowallet.repository;

import android.support.annotation.NonNull;

public final class DataRepositoryProvider {

    private static DataRepositoryImpl sDataRepository;

    @NonNull
    public static DataRepositoryImpl provideDataRepository(){

        DataRepositoryImpl dataRepository = sDataRepository;

        if(dataRepository == null){
            synchronized (DataRepositoryProvider.class){
                dataRepository = sDataRepository;
                if(dataRepository == null){
                    dataRepository = sDataRepository = new DataRepository();
                }
            }
        }

        return sDataRepository;
    }
}
