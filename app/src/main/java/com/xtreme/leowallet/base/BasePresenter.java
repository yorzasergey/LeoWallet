package com.xtreme.leowallet.base;

public abstract class BasePresenter<V extends BaseViewImpl> implements BasePresenterImpl<V> {

    private V view;

    @Override
    public void attachView(V mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public V getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {

    }
}
