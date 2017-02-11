package com.lebartodev.kursach.model;

import io.reactivex.Observable;

/**
 * Created by Александр on 08.02.2017.
 */

public interface IAuthModel {
    Observable<User> registration(String mail, String password);
    Observable<User> login(String mail, String password);

}
