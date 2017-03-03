package com.lebartodev.kursach.model;

/**
 * Created by Александр on 09.02.2017.
 */

public class Auth {
    private String email,password;
    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
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

}
