package com.lebartodev.kursach.presenter;

import com.lebartodev.kursach.model.AuthModel;
import com.lebartodev.kursach.model.IAuthModel;
import com.lebartodev.kursach.view.LoginPage;

import io.reactivex.disposables.Disposable;


public class LoginPresenter implements BaseLoginPresenter {
    private IAuthModel model;
    private Disposable subscription;
    private LoginPage loginPage;

    public LoginPresenter(LoginPage loginPage) {
        this.loginPage = loginPage;
        model=new AuthModel();

    }

    @Override
    public void register(String mail, String password,boolean isAdvertiser) {
        subscription = model.registration(mail, password,isAdvertiser)
                .subscribe(user -> loginPage.onUserAuth(user)
                        ,error-> loginPage.onError(error.getLocalizedMessage()));

    }

    @Override
    public void login(String mail, String password) {
        subscription = model.login(mail, password)
                .subscribe(user -> loginPage.onUserAuth(user)
                        ,error-> loginPage.onError(error.getLocalizedMessage()));
    }

    @Override
    public void onDestroy() {
        subscription.dispose();
    }
}
