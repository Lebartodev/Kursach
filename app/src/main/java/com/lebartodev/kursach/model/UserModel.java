package com.lebartodev.kursach.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lebartodev.kursach.KursachApplication;
import com.lebartodev.kursach.model.dto.ChangeUserDto;
import com.lebartodev.kursach.utils.SharedPrefer;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Александр on 08.02.2017.
 */

public class UserModel implements IUserModel {


    @Override
    public Observable<User> getUser() {
        return Observable
                .create((e) -> e.onNext(User.newBuilder()
                        .name("Sasha")
                        .email("lebartodev@gmail.com")
                        .build()));
    }

    @Override
    public Observable<User> updateUser(String name) {
        return Observable.create(e -> {
            ChangeUserDto changeUserDto = new ChangeUserDto(SharedPrefer.getAccount().getEmail()
                    ,name
                    ,SharedPrefer.getToken()
                    ,SharedPrefer.getAccount().isAdvertiser());
            Gson   gson  = new GsonBuilder().setPrettyPrinting().create();
            Log.d("UserModel", gson.toJson(changeUserDto));
            KursachApplication.getApi()
                    .changeProfile(changeUserDto)
                    .enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    e.onNext(response.body());
                    Log.d("UserModel", String.valueOf(response.code()));
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        });

    }

    @Override
    public void sendLocation(double lat, double lng) {


    }


}
