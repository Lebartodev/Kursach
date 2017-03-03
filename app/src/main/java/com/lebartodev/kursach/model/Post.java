package com.lebartodev.kursach.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Александр on 09.02.2017.
 */

public class Post implements Parcelable{
    private String text;
    private long timeOffset;
    private int id;
    private User user;
    private boolean favorite;
    private String category;
    private Coordinates location;
    public Post(){

    }


    protected Post(Parcel in) {
        text = in.readString();
        timeOffset = in.readLong();
        id = in.readInt();
        user = in.readParcelable(User.class.getClassLoader());
        favorite = in.readByte() != 0;
        category = in.readString();
        location = in.readParcelable(Coordinates.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Builder newBuilder() {
        return new Post().new Builder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeLong(timeOffset);
        parcel.writeInt(id);
        parcel.writeParcelable(user, i);
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeString(category);
    }


    public class Builder {
        private Builder() {

        }

        public Post build() {
            return Post.this;
        }


        public Builder text(String text) {
            Post.this.text = text;
            return this;
        }
        public Builder favorite(boolean favorite) {
            Post.this.favorite = favorite;
            return this;
        }
        public Builder category(String category) {
            Post.this.category = category;
            return this;
        }

        public Builder timeOffset(long timeOffset) {
            Post.this.timeOffset = timeOffset;
            return this;
        }

        public Builder id(int id) {
            Post.this.id = id;
            return this;
        }

        public Builder user(User user) {
            Post.this.user = user;
            return this;
        }

        public Builder location(Coordinates location) {
            Post.this.location = location;
            return this;
        }


    }


}

