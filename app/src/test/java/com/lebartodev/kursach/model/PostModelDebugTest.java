package com.lebartodev.kursach.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Александр on 09.02.2017.
 */
public class PostModelDebugTest {
    @Test
    public void getPosts() throws Exception {
        IPostModel postModel = new PostModelDebug();
        //postModel.getPosts().subscribe(post -> System.out.println(post.getUserName()+"|"+post.getText()),throwable -> System.out.println(throwable.getLocalizedMessage()),() ->assertEquals(1,1) );
    }

}