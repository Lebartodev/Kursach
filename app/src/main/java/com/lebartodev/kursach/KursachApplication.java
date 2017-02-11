package com.lebartodev.kursach;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.lebartodev.kursach.model.KursachApi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Александр on 08.02.2017.
 */
public class KursachApplication extends Application {
    protected static KursachApplication instance;
    private Retrofit retrofit;
    private static KursachApi kursachApi;

    public static KursachApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://server-test-1.herokuapp.com/") //Базовая часть адреса
                .client(okHttpClient)
                .build();

        kursachApi =retrofit.create(KursachApi.class);

        instance = this;
    }
    public static KursachApi getApi() {
        return kursachApi;
    }

}