package com.lebartodev.kursach.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Александр on 08.02.2017.
 */

public class User  {
    private String email, name, token, avatar;



    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public  class Builder {
        private Builder() {

        }

        public User build() {
            return User.this;
        }

        public Builder name(String name) {
            User.this.name = name;
            return this;
        }

        public Builder email(String email) {
            User.this.email = email;
            return this;
        }
        public Builder token(String token) {
            User.this.token = token;
            return this;
        }
        public Builder avatar(String avatar) {
            User.this.email = email;
            return this;
        }

    }
}
