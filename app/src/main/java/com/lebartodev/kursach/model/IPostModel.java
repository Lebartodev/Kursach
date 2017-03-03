package com.lebartodev.kursach.model;

import com.lebartodev.kursach.model.dto.GetPosts;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Александр on 09.02.2017.
 */

public interface IPostModel {
    Observable<List<Post>> getPosts(GetPosts getPosts);

    Observable<List<Post>> getFavorites();

    Observable<Post> createPost(String text, String category);

    Observable<List<Comment>> getSinglePost(int postId);


    Single<Comment> createComment(String text, int postId);

    Single<Comment> addToFav(int id);
}
