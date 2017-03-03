package com.lebartodev.kursach.model;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Александр on 08.02.2017.
 */
public class AuthModelDebugTest {
    @Test
    public void authorization() throws Exception {
        IAuthModel model = new AuthModel();
        model.registration("lebartodev@gmail.com","password",false)
                .subscribe(user->assertEquals(user.getName(),"Sasha"),error-> System.out.println(error.getLocalizedMessage()));
        Thread.sleep(10000);

    }

}