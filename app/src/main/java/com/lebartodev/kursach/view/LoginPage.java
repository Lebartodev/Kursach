package com.lebartodev.kursach.view;

import com.lebartodev.kursach.model.User;

/**
 * Created by Александр on 08.02.2017.
 */

public interface LoginPage {
    void onUserAuth(User user);

    void onError(String message);
}
