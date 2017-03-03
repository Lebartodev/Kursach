package com.lebartodev.kursach.model.dto;

/**
 * Created by Александр on 23.02.2017.
 */

public class SinglePostDto {
    private String token;
    private int postId;

    public SinglePostDto(int postId, String token) {
        this.postId = postId;
        this.token = token;
    }
}
