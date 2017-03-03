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
import com.lebartodev.kursach.utils.SharedPrefer;
import com.lebartodev.kursach.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 09.02.2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Comment> comments;
    private Post post;

    public CommentAdapter(Post post,List<Comment> comments) {
        this.comments=comments;
        this.post = post;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        notifyDataSetChanged();

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_comment, parent, false);
            return new CommentAdapter.ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_post, parent, false);
            return new HeaderView(v);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentAdapter.ViewHolder) {
            Comment comment = comments.get(position - 1);
            ((ViewHolder) holder).title.setText(comment.getText());
            ((CommentAdapter.ViewHolder) holder).time.setText(Utils.readableDate(comment.getTime()/1000L));
            ((CommentAdapter.ViewHolder) holder).userName.setText(comment.getUser().getName());



        }
        else
            if(holder instanceof HeaderView) {

                ((HeaderView) holder).userName.setText(post.getUser().getName());
                ((HeaderView) holder).title.setText(post.getText());
                ((HeaderView) holder).location.setText((int)Utils.getDistance(post.getLocation().getLat(),
                        post.getLocation().getLng(),
                        SharedPrefer.getLocation().getLat(),
                        SharedPrefer.getLocation().getLng()) + " km from you");
                ((HeaderView) holder).place.setText(post.getLocation().getLocationName());
                ((HeaderView) holder).time.setText(Utils.readableDate(post.getTimeOffset()/1000L));
                ((HeaderView) holder).favs_image.setVisibility(View.INVISIBLE);
                ((HeaderView) holder).all_comments.setVisibility(View.INVISIBLE);

                //((HeaderView) holder).item.findViewById(R.id.favorite_image).setVisibility(View.GONE);

            }


    }

    @Override
    public int getItemCount() {
        return comments.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 2;

        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName,time, title;
        private View item;

        public ViewHolder(View itemView) {

            super(itemView);
            this.item = itemView.findViewById(R.id.topic_layout);
            userName = (TextView) itemView.findViewById(R.id.u_name);
            title = (TextView) itemView.findViewById(R.id.comment_text);
            time = (TextView) itemView.findViewById(R.id.comment_time);

        }
    }
    class HeaderView extends RecyclerView.ViewHolder {
        private TextView userName, time, location, title, place,all_comments;

        private View item;


        private ImageView favs_image;

        public HeaderView(View itemView) {

            super(itemView);
            this.item = itemView.findViewById(R.id.topic_layout);
            all_comments = (TextView) itemView.findViewById(R.id.all_comments);
            favs_image = (ImageView) itemView.findViewById(R.id.favorite_image);
            userName = (TextView) itemView.findViewById(R.id.u_name);
            time = (TextView) itemView.findViewById(R.id.post_time);
            location = (TextView) itemView.findViewById(R.id.post_location);
            title = (TextView) itemView.findViewById(R.id.post_title);
            place = (TextView) itemView.findViewById(R.id.post_place);

        }
    }
}
