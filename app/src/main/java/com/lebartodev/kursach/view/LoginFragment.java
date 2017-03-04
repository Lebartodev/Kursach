package com.lebartodev.kursach.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.User;
import com.lebartodev.kursach.presenter.BaseLoginPresenter;
import com.lebartodev.kursach.presenter.LoginPresenter;
import com.lebartodev.kursach.utils.SharedPrefer;

public class LoginFragment extends Fragment implements LoginPage {
    private EditText emailEdit, passwordEdit;
    private Button loginButton;
    private Button registerButton;
    private BaseLoginPresenter presenter;
    private CheckBox advertiserCheckbox;

    private View view;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        emailEdit = (EditText) view.findViewById(R.id.emailText);
        passwordEdit = (EditText) view.findViewById(R.id.passwordText);
        loginButton = (Button) view.findViewById(R.id.loginButton);
        registerButton = (Button) view.findViewById(R.id.register_button);
        advertiserCheckbox= (CheckBox) view.findViewById(R.id.advertiser_checkbox);

        loginButton.setOnClickListener(view1 -> {
            closeKeyboard();
            presenter.login(emailEdit.getText().toString(), passwordEdit.getText().toString());
            emailEdit.setClickable(false);
            loginButton.setClickable(false);
            registerButton.setClickable(false);
            passwordEdit.setClickable(false);
            new SnackBar.Builder(getActivity()).withMessage("Please wait").show();

        });
        registerButton.setOnClickListener(view1 -> {
            closeKeyboard();
            presenter.register(emailEdit.getText().toString(), passwordEdit.getText().toString(),advertiserCheckbox.isChecked());
            emailEdit.setClickable(false);
            loginButton.setClickable(false);
            registerButton.setClickable(false);
            passwordEdit.setClickable(false);
            new SnackBar.Builder(getActivity()).withMessage("Please wait").show();
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new LoginPresenter(this);


    }

    @Override
    public void onUserAuth(User user) {
        SharedPrefer.setAccount(user);
        SharedPrefer.setToken(user.getToken());
        ((DrawerLayout)getActivity().findViewById(R.id.drawer_layout)).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        closeKeyboard();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, new ProfileFragment())
                .commit();


    }

    @Override
    public void onError(String message) {
        new SnackBar.Builder(getActivity()).withMessage(message).show();
        emailEdit.setClickable(true);
        loginButton.setClickable(true);
        registerButton.setClickable(true);
        passwordEdit.setClickable(true);
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
