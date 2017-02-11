package com.lebartodev.kursach;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lebartodev.kursach.model.Comment;
import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseFeedPresenter;

import java.util.List;

/**
 * Created by Александр on 09.02.2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private List<Comment> comments;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    public void addPost(Comment comment) {
        comments.add(comment);
        notifyItemInserted(comments.size()-1);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Comment comment = comments.get(position);
        //int distance = (int) LocationUtil.getDistance(topic.getLat(), topic.getLng(), SharedPrefer.getAccount().getLat(), SharedPrefer.getAccount().getLat());
        //holder.title.setText(post.getText());
        //holder.userName.setText(post.getUserName());
        //holder.place.setText(post.getPlaceName());
        //holder.location.setText(distance + "km from you");




    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName,time, location, title, place;
        private View item;

        private ImageView icon, image;

        public ViewHolder(View itemView) {

            super(itemView);
            this.item = itemView.findViewById(R.id.topic_layout);
            userName = (TextView) itemView.findViewById(R.id.u_name);
            title = (TextView) itemView.findViewById(R.id.comment_text);
            icon = (ImageView) itemView.findViewById(R.id.u_avatar);
            time = (TextView) itemView.findViewById(R.id.comment_time);

        }
    }
}
