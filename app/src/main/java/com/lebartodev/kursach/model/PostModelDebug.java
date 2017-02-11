package com.lebartodev.kursach.model;

import io.reactivex.Observable;

/**
 * Created by Александр on 09.02.2017.
 */

public class PostModelDebug implements IPostModel {
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
            Thread.sleep(2000);
            e.onNext(new Post().newBuilder()
                    //.id(String.valueOf(203))
                    //.userName("Xep c ropIb")
                    .text("Some text  my")
                    .build());

        });
    }
}
