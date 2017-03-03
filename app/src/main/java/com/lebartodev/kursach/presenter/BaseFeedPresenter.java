package com.lebartodev.kursach.presenter;

import com.lebartodev.kursach.model.Post;

/**
 * Created by Александр on 09.02.2017.
 */

public interface BaseFeedPresenter {
    void onClickPost(Post post);
    void onAddFavorite(Post post);
    void initList(String code);
}
