package com.lebartodev.kursach.presenter;

import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.view.FeedPage;

/**
 * Created by Александр on 09.02.2017.
 */

public class FeedPresenter implements BaseFeedPresenter {
    private FeedPage fPage;


    public FeedPresenter(FeedPage fPage) {
        this.fPage = fPage;
    }

    @Override
    public void onClickPost(Post post) {

    }
}
