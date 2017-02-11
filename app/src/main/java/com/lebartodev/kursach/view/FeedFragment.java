package com.lebartodev.kursach.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lebartodev.kursach.PostAdapter;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseFeedPresenter;
import com.lebartodev.kursach.presenter.ProfilePresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements FeedPage {
    private View view;
    private View menuImage;
    private PostAdapter adapter;
    private BaseFeedPresenter presenter;
    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        menuImage = view.findViewById(R.id.menuImage);
        menuImage.setOnClickListener(view1-> ((DrawerLayout) getActivity().findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START));

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //presenter = new ProfilePresenter(this);
        Toolbar toolbarBlue = (Toolbar) getActivity().findViewById(R.id.toolbar_blue_common);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarBlue);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) view.findViewById(R.id.text_white)).setText("FEED");

        adapter=new PostAdapter(new ArrayList<Post>(),presenter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void addPost(Post post) {
        adapter.addPost(post);
    }
}
