package com.lebartodev.kursach.model;

import com.lebartodev.kursach.KursachApplication;
import com.lebartodev.kursach.utils.Utils;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Александр on 08.02.2017.
 */

public class AuthModel implements IAuthModel {

    @Override
    public Observable<User> registration(String mail, String password, boolean isAdvertiser) {
        return Observable.create(e -> {

            KursachApplication.getApi().registration(new Register(mail, Utils.MD5(password), isAdvertiser)).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 409) {
                        e.onError(new Exception("This email already in database!"));
                    } else {
                        User user = response.body();
                        e.onNext(user);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    e.onError(t);
                }
            });


        });
    }

    @Override
    public Observable<User> login(String mail, String password) {
        return Observable.create(e -> {

            KursachApplication.getApi().authentication(new Auth(mail, Utils.MD5(password))).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.code() == 404) {
                        e.onError(new Exception("Cannot find user"));
                    }
                    else {
                        User user = response.body();
                        e.onNext(user);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    e.onError(t);
                }
            });


        });
    }
}
