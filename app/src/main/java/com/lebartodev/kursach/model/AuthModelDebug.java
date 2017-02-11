package com.lebartodev.kursach.model;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Александр on 08.02.2017.
 */

public class AuthModelDebug implements IAuthModel {

    @Override
    public Observable<User> registration(String mail, String password) {
        return Observable.create(e -> {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl("https://server-test-1.herokuapp.com/") //Базовая часть адреса
                    .build();

            KursachApi kursachApi =retrofit.create(KursachApi.class);

            kursachApi.registration(new Auth(mail,password));

        });
    }
}
