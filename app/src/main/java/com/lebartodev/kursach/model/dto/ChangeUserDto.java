package com.lebartodev.kursach.model.dto;

/**
 * Created by Александр on 12.02.2017.
 */

public class ChangeUserDto {
    public boolean isAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(boolean advertiser) {
        this.advertiser = advertiser;
    }

    private boolean advertiser;
    private String name,email,token;

    public ChangeUserDto(String email, String name, String token,boolean advertiser) {
        this.email = email;
        this.name = name;
        this.token = token;
        this.advertiser=advertiser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
