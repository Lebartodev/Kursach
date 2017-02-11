package com.lebartodev.kursach.model;

import io.reactivex.Observable;

/**
 * Created by Александр on 09.02.2017.
 */

public interface IPostModel {
    Observable<Post> getPosts();
    Observable<Post> createPost(String text);

    Observable<Post> getSinglePost(int postId);
}
