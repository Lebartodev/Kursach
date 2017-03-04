package com.lebartodev.kursach.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.firetrap.permissionhelper.action.OnDenyAction;
import com.firetrap.permissionhelper.action.OnGrantAction;
import com.firetrap.permissionhelper.helper.PermissionHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Coordinates;
import com.lebartodev.kursach.utils.SharedPrefer;
import com.lebartodev.kursach.utils.Utils;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private DrawerLayout drawer_layout;
    private NavigationView nvView;
    protected GoogleApiClient mGoogleApiClient;

    private PermissionHelper.PermissionBuilder permissionRequest;
    private OnDenyAction onDenyAction = new OnDenyAction() {
        @Override
        public void call(int requestCode, boolean shouldShowRequestPermissionRationale) {
        }
    };


    private OnGrantAction onGrantAction = new OnGrantAction() {
        @Override
        public void call(int requestCode) {
            startLocationRequest();

        }
    };

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private boolean isPermissionsGranted() {
        int permissionCheckCOARSE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheckFINE = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionCheckCOARSE == PackageManager.PERMISSION_GRANTED && permissionCheckFINE == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressWarnings({"MissingPermission"})
    public void startLocationRequest() {
        if (isPermissionsGranted()) {

            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(30000);

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, location -> {
                if (location != null) {
                    Coordinates coordinates = new Coordinates(location.getLatitude(),
                            location.getLongitude(),
                            Utils.getLocationName(MainActivity.this, location.getLatitude(),
                                    location.getLongitude()));
                    SharedPrefer.setLocation(coordinates);
                }
            });

        } else {
            permissionRequest = PermissionHelper.with(this)
                    .build(Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                    .onPermissionsDenied(onDenyAction)
                    .onPermissionsGranted(onGrantAction)
                    .request(20);
        }

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvView = (NavigationView) findViewById(R.id.nvView);
        if (SharedPrefer.getToken().equals("")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new LoginFragment(), "tag")
                    .commit();
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new FeedFragment(), "tag")
                    .commit();
        }
        initMenuClickListener();
        initMenu();

        buildGoogleApiClient();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initMenu();
    }

    private void initMenu() {
        try {
            ((TextView) findViewById(R.id.accountName)).setText(SharedPrefer.getAccount().getName());
            ((TextView) findViewById(R.id.accountMail)).setText(SharedPrefer.getAccount().getEmail());
        } catch (Exception e) {

        }

    }

    private void initMenuClickListener() {
        nvView.setNavigationItemSelectedListener(menuItem -> {

            getSupportFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);

            menuItem.setChecked(true);
            drawer_layout.closeDrawers();
            closeKeyboard();

            switch (menuItem.getItemId()) {
                case R.id.nav_feed:
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.activity_main, new FeedFragment(), "tag")
                            .commit();

                    break;
                case R.id.nav_profile:

                    FragmentManager fm2 = getSupportFragmentManager();
                    fm2.beginTransaction().replace(R.id.activity_main, new ProfileFragment(), "tag")
                            .commit();
                    break;
                case R.id.nav_logout:
                    SharedPrefer.setToken("");
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new LoginFragment(), "tag")
                            .commit();
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    break;
            }
            initMenu();
            return true;
        });
    }

    private void closeKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
