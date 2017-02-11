package com.lebartodev.kursach.model;

/**
 * Created by Александр on 09.02.2017.
 */

public class Post {
    private String text;
    private long timeOffset;
    private int postId;
    private User user;
    private Coordinates location;


    public static Builder newBuilder() {
        return new Post().new Builder();
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
        public Builder timeOffset(long timeOffset) {
            Post.this.timeOffset = timeOffset;
            return this;
        }
        public Builder postId(int postId) {
            Post.this.postId = postId;
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

