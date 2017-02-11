package com.lebartodev.kursach.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.presenter.BaseProfilePresenter;
import com.lebartodev.kursach.presenter.ProfilePresenter;
import com.lebartodev.kursach.utils.SharedPrefer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfilePage {
    private View view;
    private EditText nameText;
    private Button saveButton;
    private BaseProfilePresenter presenter;
    private View menuImage;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        nameText = (EditText) view.findViewById(R.id.editName);
        saveButton = (Button) view.findViewById(R.id.login_button);
        menuImage = view.findViewById(R.id.menuImage);

        nameText.setText(SharedPrefer.getAccount().getName());
        saveButton.setOnClickListener(view1 -> {
            closeKeyboard();
            presenter.changeProfile(nameText.getText().toString());
        });
        menuImage.setOnClickListener(view1 -> ((DrawerLayout) getActivity().findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START));
        nameText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                closeKeyboard();
                presenter.changeProfile(nameText.getText().toString());

                return true;
            }
            return false;
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new ProfilePresenter(this);

        Toolbar toolbarBlue = (Toolbar) getActivity().findViewById(R.id.toolbar_blue_common);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarBlue);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) view.findViewById(R.id.text_white)).setText("PROFILE");


    }


    @Override
    public void onUserUpdated() {
        new SnackBar.Builder(getActivity()).withMessage("User saved!").show();

    }

    @Override
    public void onError() {
        new SnackBar.Builder(getActivity()).withMessage("Error!!").show();
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
