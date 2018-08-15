package com.xtreme.leowallet.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.xtreme.leowallet.model.AccountInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

public class CallableAccountInfo implements Callable<AccountInfo> {

    private final InputStream mInputStream;

    public CallableAccountInfo(InputStream inputStream) {
        mInputStream = inputStream;
    }

    @Override
    public AccountInfo call() throws Exception {

        String jsonString = loadJsonFromAsset(mInputStream);

        if(jsonString.trim().isEmpty()){
            return null;
        }else{
            Gson gson = new Gson();
            return gson.fromJson(jsonString, AccountInfo.class);
        }
    }

    public String loadJsonFromAsset(InputStream inputStream) {

        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
