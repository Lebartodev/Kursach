package com.lebartodev.kursach.model;

import com.lebartodev.kursach.KursachApplication;
import com.lebartodev.kursach.utils.SharedPrefer;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Александр on 09.02.2017.
 */

public class PostModel implements IPostModel {
    @Override
    public Observable<Post> getPosts() {
        return Observable.create(e -> {
            for (int i = 0; i < 10; i++) {
                e.onNext(new Post().newBuilder()
                        //.id(String.valueOf(i))
                        // .userName("Xep c ropIb")
                        .text("Some text " + i)
                        .build());
                Thread.sleep(2000);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<Post> createPost(String text) {
        return Observable.create(e -> {
            Post post = new Post().newBuilder()
                    .location(SharedPrefer.getLocation())
                    .text(text)
                    .user(SharedPrefer.getAccount())
                    .build();
            KursachApplication.getApi().createPost(post).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    Post post = response.body();
                    e.onNext(post);

                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        });
    }
    @Override
    public Observable<Post> getSinglePost(int postId) {
        return Observable.create(e -> {
            KursachApplication.getApi().singlePost("xyu").enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    Post post = response.body();
                    e.onNext(post);

                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        });
    }
}
