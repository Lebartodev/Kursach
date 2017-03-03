package com.lebartodev.kursach.model.dto;

import com.lebartodev.kursach.model.Coordinates;

/**
 * Created by Александр on 12.02.2017.
 */

public class GetPosts {
    private String token;
    private String category;
    private Coordinates location;

    public GetPosts(Coordinates location, String token, String category) {
        this.location = location;
        this.token = token;

        this.category = category;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
