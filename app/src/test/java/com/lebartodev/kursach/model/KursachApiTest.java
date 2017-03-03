package com.lebartodev.kursach.model;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by Александр on 10.02.2017.
 */
public class KursachApiTest {
    @Test
    public void registrationTest() throws Exception{
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://server-test-1.herokuapp.com/") //Базовая часть адреса
                .build();

        KursachApi kursachApi =retrofit.create(KursachApi.class);
        kursachApi.registration(new Register("email","pass", false)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(response.body().getEmail());
                assertEquals(response.body().getEmail(),"email");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
                assertEquals(1,0);
            }
        });

    }

}