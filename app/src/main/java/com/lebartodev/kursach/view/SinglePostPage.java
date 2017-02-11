package com.lebartodev.kursach.view;

import com.lebartodev.kursach.model.Comment;
import com.lebartodev.kursach.model.Post;

import java.util.List;

/**
 * Created by Александр on 11.02.2017.
 */

public interface SinglePostPage {
    void onCommentsLoaded(List<Comment> comments);
    void onCommentAdded();

}
