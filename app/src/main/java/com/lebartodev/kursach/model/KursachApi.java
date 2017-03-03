package com.lebartodev.kursach.model;

import com.lebartodev.kursach.model.dto.ChangeUserDto;
import com.lebartodev.kursach.model.dto.CreateCommentDto;
import com.lebartodev.kursach.model.dto.FavoriteAddDto;
import com.lebartodev.kursach.model.dto.FavoritesDto;
import com.lebartodev.kursach.model.dto.GetPosts;
import com.lebartodev.kursach.model.dto.SinglePostDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Александр on 10.02.2017.
 */

public interface KursachApi {
    @POST("/registration")
    Call<User> registration(@Body Register body);
    @POST("/authentication")
    Call<User> authentication(@Body Auth body);
    @POST("/get_posts")
    Call<List<Post>> getPosts(@Body GetPosts body);
    @POST("/new_post")
    Call<Post> createPost(@Body Post body);
    @POST("/comments")
    Call<List<Comment>> singlePost(@Body SinglePostDto body);
    @POST("/add_comment")
    Call<Void> createComment(@Body CreateCommentDto body);
    @POST("/new_favorite")
    Call<Void> addToFavorite(@Body FavoriteAddDto body);
    @POST("/get_favorites")
    Call<List<Post>> getFavs(@Body FavoritesDto token);
    @POST("/change_user")
    Call<User> changeProfile(@Body ChangeUserDto body);
}
