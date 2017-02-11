package com.lebartodev.kursach.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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

        emailEdit.setText("lebartodev@gmail.com");

        loginButton.setOnClickListener(view1 -> {
            closeKeyboard();
            presenter.register(emailEdit.getText().toString(), passwordEdit.getText().toString());

        });
        registerButton.setOnClickListener(view1 -> {
            closeKeyboard();
            presenter.login(emailEdit.getText().toString(), passwordEdit.getText().toString());

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
        SharedPrefer.setToken("token");
        closeKeyboard();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, new ProfileFragment())
                .commit();
        //new SnackBar.Builder(getActivity()).withMessage("Hello "+user.getName()+"!").show();

    }

    @Override
    public void onError(String message) {
        new SnackBar.Builder(getActivity()).withMessage(message).show();
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
