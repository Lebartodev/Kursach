package com.lebartodev.kursach.model.dto;

/**
 * Created by Александр on 23.02.2017.
 */

public class CreateCommentDto {
    private String token;
    private int postId;
    private String text;

    public CreateCommentDto(int postId, String text, String token) {
        this.postId = postId;
        this.text = text;
        this.token = token;
    }
}
