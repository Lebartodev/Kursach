package com.lebartodev.kursach.presenter;

import com.lebartodev.kursach.model.Comment;
import com.lebartodev.kursach.model.IPostModel;
import com.lebartodev.kursach.model.PostModel;
import com.lebartodev.kursach.view.SinglePostPage;

import io.reactivex.functions.Consumer;

/**
 * Created by Александр on 15.02.2017.
 */

public class SinglePostPresenter implements BaseSinglePostPresenter {

    private SinglePostPage spPage;
    private IPostModel model;

    public SinglePostPresenter(SinglePostPage spPage, int postId) {
        this.spPage = spPage;
        model = new PostModel();
        model.getSinglePost(postId).flatMapIterable(comments -> comments)
                .subscribe(comment -> spPage.addComment(comment));

    }

    @Override
    public void createComment(String text,int postId) {
        model.createComment(text,postId).subscribe(comment -> {
            spPage.addComment(comment);
        });

    }
}
