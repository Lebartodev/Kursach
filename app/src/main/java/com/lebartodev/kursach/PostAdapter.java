package com.lebartodev.kursach;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseFeedPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 09.02.2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private List<Post> posts;
    private BaseFeedPresenter presenter;

    public PostAdapter(List<Post> posts,BaseFeedPresenter presenter) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        notifyItemInserted(posts.size()-1);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Post post = posts.get(position);
        //int distance = (int) LocationUtil.getDistance(topic.getLat(), topic.getLng(), SharedPrefer.getAccount().getLat(), SharedPrefer.getAccount().getLat());
        //holder.title.setText(post.getText());
        //holder.userName.setText(post.getUserName());
        //holder.place.setText(post.getPlaceName());
        //holder.location.setText(distance + "km from you");

        holder.item.setOnClickListener(view -> presenter.onClickPost(post));




    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName,time, location, title, place;
        private View item;

        private ImageView icon, image;

        public ViewHolder(View itemView) {

            super(itemView);
            this.item = itemView.findViewById(R.id.topic_layout);
            userName = (TextView) itemView.findViewById(R.id.u_name);
            time = (TextView) itemView.findViewById(R.id.post_time);
            location = (TextView) itemView.findViewById(R.id.post_location);
            title = (TextView) itemView.findViewById(R.id.post_title);
            place = (TextView) itemView.findViewById(R.id.post_place);
            icon = (ImageView) itemView.findViewById(R.id.u_avatar);
            //image = (ImageView) itemView.findViewById(R.id.post_image);

        }
    }
}
