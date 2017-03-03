package com.lebartodev.kursach.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Александр on 08.02.2017.
 */

public class User implements Parcelable  {
    private String email, name, token;
    private boolean advertiser;


    protected User(Parcel in) {
        email = in.readString();
        name = in.readString();
        token = in.readString();
        advertiser = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(token);
        dest.writeByte((byte) (advertiser ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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


    public boolean isAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(boolean advertiser) {
        this.advertiser = advertiser;
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
        public Builder advertiser(boolean advertiser) {
            User.this.advertiser = advertiser;
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
