package com.lebartodev.kursach.presenter;

import com.lebartodev.kursach.model.Category;
import com.lebartodev.kursach.view.FeedSortPage;

import io.reactivex.disposables.Disposable;

/**
 * Created by Александр on 23.02.2017.
 */

public class FeedSortPresenter implements  BaseFeedSortPresenter{
    private FeedSortPage fsPage;
    private int distance;

    public FeedSortPresenter(FeedSortPage fsPage) {
        this.fsPage = fsPage;

    }

    @Override
    public void onClickCategory(Category code) {

        fsPage.openCategory(code,distance);

    }
}
