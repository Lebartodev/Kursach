package com.lebartodev.kursach.utils;

/**
 * Created by Александр on 08.02.2017.
 */

import com.google.gson.Gson;
import com.lebartodev.kursach.model.Coordinates;
import com.lebartodev.kursach.model.User;

/**
 * Created by Alexandr on 22.09.2016.
 */
public class SharedPrefer extends BaseSharedPrefer {

    public static void setAccount(User user) {
        Gson gson = new Gson();
        String res = gson.toJson(user);
        get().put("account", res);
    }

    public static User getAccount() {
        Gson gson = new Gson();

        User acc = gson.fromJson(get().get("account", ""), User.class);
        return acc;
    }
    public static void setToken(String token) {
        get().put("token", token);
    }

    public static String getToken() {

        return get().get("token", "");
    }

    public static void deleteAll() {
        setAccount(null);
        setToken(null);

    }
    public static void setLocation(Coordinates location){
        Gson gson = new Gson();
        get().put("location",gson.toJson(location));

    }
    public static  Coordinates getLocation(){
        Gson gson = new Gson();
        return gson.fromJson(get().get("location",""),Coordinates.class);

    }


}

