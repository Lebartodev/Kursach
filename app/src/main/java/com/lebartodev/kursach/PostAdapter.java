package com.lebartodev.kursach;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseFeedPresenter;
import com.lebartodev.kursach.utils.SharedPrefer;
import com.lebartodev.kursach.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 09.02.2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> posts;
    private BaseFeedPresenter presenter;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public PostAdapter(List<Post> posts, BaseFeedPresenter presenter) {
        this.presenter=presenter;
        this.posts = posts;
    }

    public void deleteAll() {
        posts = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void addPost(Post post) {
        posts.add(post);
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.title.setText(post.getText());
        holder.userName.setText(post.getUser().getName());
        holder.place.setText(post.getLocation().getLocationName());
        holder.time.setText(Utils.readableDate(post.getTimeOffset()/1000L));
        holder.location.setText((int)Utils.getDistance(post.getLocation().getLat(),
                        post.getLocation().getLng(),
                        SharedPrefer.getLocation().getLat(),
                        SharedPrefer.getLocation().getLng()) + " km from you");

        if (post.isFavorite())
            holder.favorite_image.setImageResource(R.drawable.ic_star_active);
        else
            holder.favorite_image.setImageResource(R.drawable.ic_star_unactive);
        if(post.getUser().isAdvertiser()){
            holder.all_comments.setText("Post ad");
        }

        holder.item.setOnClickListener(view -> presenter.onClickPost(post));

        holder.favorite_image.setOnClickListener(view -> {
            presenter.onAddFavorite(post);
            post.setFavorite(!post.isFavorite());
            notifyItemChanged(position);
           // holder.favorite_image.setImageResource(R.drawable.ic_star_active);
        });
        if(type.equals("FAVS")){
            holder.favorite_image.setVisibility(View.INVISIBLE);
        }
        else
            holder.favorite_image.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName, time, location, title, place,all_comments;
        private View item;

        private ImageView favorite_image;

        public ViewHolder(View itemView) {

            super(itemView);
            this.item = itemView.findViewById(R.id.topic_layout);
            userName = (TextView) itemView.findViewById(R.id.u_name);
            time = (TextView) itemView.findViewById(R.id.post_time);
            location = (TextView) itemView.findViewById(R.id.post_location);
            title = (TextView) itemView.findViewById(R.id.post_title);
            place = (TextView) itemView.findViewById(R.id.post_place);
            favorite_image = (ImageView) itemView.findViewById(R.id.favorite_image);
            all_comments = (TextView) itemView.findViewById(R.id.all_comments);

        }
    }
}
