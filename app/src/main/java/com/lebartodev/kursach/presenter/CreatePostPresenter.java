package com.lebartodev.kursach.presenter;

import com.lebartodev.kursach.model.IPostModel;
import com.lebartodev.kursach.model.PostModel;
import com.lebartodev.kursach.view.CreatePostPage;

/**
 * Created by Александр on 11.02.2017.
 */

public class CreatePostPresenter implements BaseCreatePostPresenter {
    private CreatePostPage cpPage;
    private IPostModel model;
    private String currentCategory="";
    public CreatePostPresenter(CreatePostPage cpPage) {
        this.cpPage = cpPage;
        model = new PostModel();
    }

    @Override
    public void createPost(String text) {

        model.createPost(text,currentCategory).subscribe(post -> {
           cpPage.onPostCreated(post);
        },error->cpPage.onError(error.getLocalizedMessage()));

    }

    @Override
    public void onPickCategory(String result) {
        currentCategory=result;
    }
}
