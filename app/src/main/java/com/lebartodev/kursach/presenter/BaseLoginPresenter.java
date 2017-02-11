package com.lebartodev.kursach.presenter;

/**
 * Created by Александр on 08.02.2017.
 */

public interface BaseLoginPresenter {
    void register(String mail,String password);
    void login(String mail,String password);
    void onDestroy();
}
