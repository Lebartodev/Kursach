package com.lebartodev.kursach.model.dto;

/**
 * Created by Александр on 28.02.2017.
 */

public class FavoriteAddDto {
    private String token;
    private int postId;

    public FavoriteAddDto(int postId, String token) {
        this.postId = postId;
        this.token = token;
    }
}
