package com.lebartodev.kursach.model;

/**
 * Created by Александр on 09.02.2017.
 */

public class Comment {
    private String text;
    private long time;
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
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public class Builder {
        private Builder() {

        }

        public Comment build() {
            return Comment.this;
        }


        public Builder time(long time) {
            Comment.this.time = time;
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

