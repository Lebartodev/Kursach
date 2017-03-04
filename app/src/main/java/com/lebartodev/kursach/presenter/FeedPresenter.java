package com.lebartodev.kursach.presenter;

import android.util.Log;

import com.lebartodev.kursach.model.Comment;
import com.lebartodev.kursach.model.IPostModel;
import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.model.PostModel;
import com.lebartodev.kursach.model.dto.GetPosts;
import com.lebartodev.kursach.utils.SharedPrefer;
import com.lebartodev.kursach.view.FeedPage;

import io.reactivex.functions.Consumer;

/**
 * Created by Александр on 09.02.2017.
 */

public class FeedPresenter implements BaseFeedPresenter {
    private FeedPage fPage;

    private IPostModel model;



    public FeedPresenter(FeedPage fPage) {
        this.fPage = fPage;
        model = new PostModel();
        model.getPosts(new GetPosts(SharedPrefer.getLocation(),SharedPrefer.getToken(), SharedPrefer.getCategory()))
                .flatMapIterable(posts -> posts)
                .subscribe(fPage::addPost,error->{
                    fPage.onError(error.getLocalizedMessage());
                });
    }

    @Override
    public void onClickPost(Post post) {
        fPage.onPostClicked(post);

    }

    @Override
    public void onAddFavorite(Post post) {
        model.addToFav(post.getId()).subscribe(comment -> {
            Log.d("onAddFavorite", "onAddFavorite: yep");
        });

    }

    @Override
    public void initList(String code) {
        if(!code.equals("FAVS"))
        model.getPosts(new GetPosts(SharedPrefer.getLocation(),SharedPrefer.getToken(), code))
                .flatMapIterable(posts -> posts)
                .subscribe(fPage::addPost,error->{
                    fPage.onError(error.getLocalizedMessage());
                });
        else{
            model.getFavorites()
                    .flatMapIterable(posts -> posts)
                    .subscribe(fPage::addPost,error->{
                        fPage.onError(error.getLocalizedMessage());
                    });
        }
    }
}
