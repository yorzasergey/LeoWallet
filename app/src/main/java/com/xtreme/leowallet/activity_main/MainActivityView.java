package com.xtreme.leowallet.activity_main;

import com.xtreme.leowallet.base.BaseViewImpl;
import com.xtreme.leowallet.model.AccountInfo;

public interface MainActivityView extends BaseViewImpl {

    public void showData(AccountInfo value);
}
