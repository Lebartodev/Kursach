package com.lebartodev.kursach.model;

import io.reactivex.Observable;

/**
 * Created by Александр on 08.02.2017.
 */

public interface IUserModel {
    Observable<User> getUser();
    Observable<User> updateUser(String name);
    void sendLocation(double lat,double lng);

}
