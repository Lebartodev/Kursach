package com.lebartodev.kursach.model;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Created by Александр on 08.02.2017.
 */
public class UserModelDebugTest {
    @Test
    public void getUser() throws Exception {
        IUserModel userModel = new UserModelDebug();
        userModel.getUser().subscribe(user -> assertEquals(user.getName(),"Sasha"));
    }

}