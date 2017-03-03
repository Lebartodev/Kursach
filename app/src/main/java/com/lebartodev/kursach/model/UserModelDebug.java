package com.lebartodev.kursach.model;

import io.reactivex.Observable;

/**
 * Created by Александр on 08.02.2017.
 */

public class UserModelDebug implements IUserModel {


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
            e.onNext(User.newBuilder()
                    .name(name)
                    //.avatarUrl(avatarUrl)
                    .email("lebartodev@gmail.com")
                    .build());
        });
    }

    @Override
    public void sendLocation(double lat, double lng) {


    }


}
