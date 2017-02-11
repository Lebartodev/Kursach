package com.lebartodev.kursach.model;

import android.util.Log;

import com.lebartodev.kursach.KursachApplication;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Александр on 08.02.2017.
 */

public class AuthModel implements IAuthModel {

    @Override
    public Observable<User> registration(String mail, String password) {
        return Observable.create(e -> {

            KursachApplication.getApi().registration(new Auth(mail, password)).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.code()==409){
                        e.onError(new Exception("This email already in database!"));
                    }
                    User user = response.body();
                    e.onNext(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("MainActivity", t.getLocalizedMessage());
                }
            });


        });
    }

    @Override
    public Observable<User> login(String mail, String password) {
        return Observable.create(e -> {

            KursachApplication.getApi().registration(new Auth(mail, password)).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.code()==409){
                        e.onError(new Exception("This email already in database!"));
                    }
                    User user = response.body();
                    e.onNext(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("MainActivity", t.getLocalizedMessage());
                }
            });


        });
    }
}
