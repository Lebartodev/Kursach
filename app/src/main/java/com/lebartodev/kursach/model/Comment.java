package com.lebartodev.kursach.model;

/**
 * Created by Александр on 09.02.2017.
 */

public class Comment {
    private String text;
    private long timeOffset;
    private User user;
    private int postId;

    public static Builder newBuilder() {
        return new Comment().new Builder();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return timeOffset;
    }

    public void setTime(long time) {
        this.timeOffset = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class Builder {
        private Builder() {

        }

        public Comment build() {
            return Comment.this;
        }


        public Builder time(long timeOffset) {
            Comment.this.timeOffset = timeOffset;
            return this;
        }
        public Builder user(User user) {
            Comment.this.user = user;
            return this;
        }
        public Builder text(String text) {
            Comment.this.text = text;
            return this;
        }
        public Builder postId(int postId) {
            Comment.this.postId = postId;
            return this;
        }



    }


}

