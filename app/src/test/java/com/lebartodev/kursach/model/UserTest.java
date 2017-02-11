package com.lebartodev.kursach.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 09.02.2017.
 */
public class UserTest {
    @Test
    public void getAuth() throws Exception {
        User user = new User()
                .newBuilder()
                .token("token")
                .name("name")
                .avatar("avatar")
                .email("email")
                .build();
        Comment comment = new Comment().newBuilder()
                .text("text")
                .time(1488)
                .postId(228)
                .user(user)
                .build();

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(comments));


    }
}