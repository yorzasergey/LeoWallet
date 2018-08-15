package com.xtreme.leowallet.activity_main;

import com.xtreme.leowallet.base.BasePresenterImpl;

import java.io.InputStream;

public interface MainActivityPresenterImpl extends BasePresenterImpl<MainActivityView> {

    public void getListAccountInfo(InputStream inputStream);
}
