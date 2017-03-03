package com.lebartodev.kursach.presenter;

import com.lebartodev.kursach.model.IUserModel;
import com.lebartodev.kursach.model.UserModel;
import com.lebartodev.kursach.model.UserModelDebug;
import com.lebartodev.kursach.utils.SharedPrefer;
import com.lebartodev.kursach.view.ProfilePage;

/**
 * Created by Александр on 08.02.2017.
 */

public class ProfilePresenter implements BaseProfilePresenter {
    private ProfilePage pPage;
    private IUserModel model;

    public ProfilePresenter(ProfilePage pPage) {
        this.pPage = pPage;
        model=new UserModel();
    }

    @Override
    public void changeProfile(String name) {
        model.updateUser(name).subscribe(user-> {
            pPage.onUserUpdated(user);
                    SharedPrefer.setAccount(user);
        },
                error->{pPage.onError();
        });

    }
}
