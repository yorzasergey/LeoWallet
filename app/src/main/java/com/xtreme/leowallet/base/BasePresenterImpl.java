package com.xtreme.leowallet.base;

public interface BasePresenterImpl <V extends BaseViewImpl> {

    void attachView(V mvpView);

    void viewIsReady();

    void detachView();

    void destroy();
}
