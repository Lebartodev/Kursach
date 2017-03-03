package com.lebartodev.kursach.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lebartodev.kursach.KursachApplication;
import com.lebartodev.kursach.model.dto.CreateCommentDto;
import com.lebartodev.kursach.model.dto.FavoriteAddDto;
import com.lebartodev.kursach.model.dto.FavoritesDto;
import com.lebartodev.kursach.model.dto.GetPosts;
import com.lebartodev.kursach.model.dto.SinglePostDto;
import com.lebartodev.kursach.utils.SharedPrefer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Александр on 09.02.2017.
 */

public class PostModel implements IPostModel {
    private String TAG = "PostModel";
    @Override
    public Observable<List<Post>> getPosts(GetPosts getPosts) {
        Log.d(TAG, "getPosts: "+new Gson().toJson(getPosts));
        return Observable.create(e -> {
            KursachApplication.getApi().getPosts(getPosts).enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.body() != null) {
                        e.onNext(response.body());
                        Log.d(TAG, "getPosts onResponse: "+new Gson().toJson(response.body()));
                    } else
                        e.onError(new Exception("Not found!"));

                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    e.onError(t);
                }
            });
        });
    }
    @Override
    public Observable<List<Post>> getFavorites() {
        return Observable.create(e -> {
            KursachApplication.getApi().getFavs(new FavoritesDto(SharedPrefer.getToken())).enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.body() != null) {
                        Log.d(TAG, "getFavorites onResponse: "+new Gson().toJson(response.body()));
                        e.onNext(response.body());

                    } else
                        e.onError(new Exception("Not found!"));

                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    e.onError(t);
                }
            });
        });
    }

    @Override
    public Observable<Post> createPost(String text, String category) {
        return Observable.create(e -> {
            Post post = new Post().newBuilder()
                    .location(SharedPrefer.getLocation())
                    .text(text)
                    .category(category)
                    .user(SharedPrefer.getAccount())
                    .build();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Log.d("PostModel", "createPost: " + gson.toJson(post));
            KursachApplication
                    .getApi()
                    .createPost(post)
                    .enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {

                            Post post = response.body();
                            if (post != null)
                                e.onNext(post);
                            else
                                e.onError(new Exception(String.valueOf(response.code())));
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });
        });
    }

    @Override
    public Observable<List<Comment>> getSinglePost(int postId) {
        return Observable.create(e -> {
            KursachApplication.getApi()
                    .singlePost(new SinglePostDto(postId, SharedPrefer.getToken()))
                    .enqueue(new Callback<List<Comment>>() {
                        @Override
                        public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                            e.onNext(response.body());
                            Log.d(TAG, "getSinglePost onResponse: "+new Gson().toJson(response.body()));
                        }

                        @Override
                        public void onFailure(Call<List<Comment>> call, Throwable t) {

                        }
                    });
        });
    }

    @Override
    public Single<Comment> createComment(String text, int postId) {
        return Single.create(e -> {
            Comment comment=Comment.newBuilder().user(SharedPrefer.getAccount()).postId(postId).time(0).text(text).build();
            KursachApplication
                    .getApi()
                    .createComment(new CreateCommentDto(postId,text,SharedPrefer.getToken()))
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.d(TAG, "createComment: success");
                            e.onSuccess(comment);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
        });

    }

    @Override
    public Single<Comment> addToFav(int id) {
        FavoriteAddDto fa =  new FavoriteAddDto(id,SharedPrefer.getToken());
        Log.d(TAG, "addToFav: "+new Gson().toJson(fa));
        return Single.create(e -> {

            KursachApplication
                    .getApi()
                    .addToFavorite(fa).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d(TAG, "addToFav onResponse: "+new Gson().toJson(response.body()));
                    e.onSuccess(new Comment());
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        });
    }
}
