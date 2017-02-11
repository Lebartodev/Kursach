package com.lebartodev.kursach.model;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Александр on 10.02.2017.
 */

public interface KursachApi {
    @POST("/registration")
    Call<User> registration(@Body Auth body);
    @POST("/auth")
    Call<User> auth(@Body Auth body);
    @POST("/createPost")
    Call<Post> createPost(@Body Post body);
    @POST("/sads")
    Call<Post> singlePost(@Body String body);
}
