package com.lebartodev.kursach.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

/**
 * Created by Александр on 09.02.2017.
 */
public class AuthTest {
    @Test
    public void getAuth() throws Exception {
        Auth auth = new Auth("email","password");
        Gson gson = new Gson();
        System.out.println(gson.toJson(auth));


    }
}