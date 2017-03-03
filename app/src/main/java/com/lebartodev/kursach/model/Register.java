package com.lebartodev.kursach.model;

/**
 * Created by Александр on 09.02.2017.
 */

public class Register {
    private String email,password;
    private boolean advertiser;
    public Register(String email, String password, boolean advertiser) {
        this.email = email;
        this.password = password;
        this.advertiser = advertiser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(boolean advertiser) {
        this.advertiser = advertiser;
    }
}
