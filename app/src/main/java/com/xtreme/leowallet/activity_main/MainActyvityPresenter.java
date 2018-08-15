package com.xtreme.leowallet.activity_main;

import com.xtreme.leowallet.base.BasePresenter;
import com.xtreme.leowallet.model.AccountInfo;
import com.xtreme.leowallet.repository.DataRepositoryProvider;

import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActyvityPresenter extends BasePresenter<MainActivityView> implements MainActivityPresenterImpl {

    private AccountInfo mAccountInfo;

    @Override
    public void getListAccountInfo(InputStream inputStream) {

        if(mAccountInfo == null){
            DataRepositoryProvider.provideDataRepository()
                    .getAccountInfo(inputStream)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AccountInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AccountInfo value) {

                            mAccountInfo = value;

                            getView().showData(mAccountInfo);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //getView().showLoadingError();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{
            getView().showData(mAccountInfo);
        }
    }

    @Override
    public void viewIsReady() {

    }
}


