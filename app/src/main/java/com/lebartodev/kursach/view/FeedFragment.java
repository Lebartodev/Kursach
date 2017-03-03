package com.lebartodev.kursach.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.github.mrengineer13.snackbar.SnackBar;
import com.lebartodev.kursach.PostAdapter;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Category;
import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseFeedPresenter;
import com.lebartodev.kursach.presenter.FeedPresenter;
import com.lebartodev.kursach.utils.CategoryMap;
import com.lebartodev.kursach.utils.SharedPrefer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements FeedPage {
    private View view;
    private View menuImage;
    private PostAdapter adapter;
    private BaseFeedPresenter presenter;
    private RecyclerView feedList;
    private TextView categoryText;
    private CategoryMap categoryMap;
    private PullRefreshLayout refreshFeedLayout;

    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feed, container, false);
        refreshFeedLayout = (PullRefreshLayout) view.findViewById(R.id.refreshFeedLayout);
        feedList = (RecyclerView) view.findViewById(R.id.feed_list);
        menuImage = view.findViewById(R.id.menuImage);
        categoryText = (TextView) view.findViewById(R.id.category_name);
        menuImage.setOnClickListener(view1 -> ((DrawerLayout) getActivity().findViewById(R.id.drawer_layout)).openDrawer(GravityCompat.START));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryMap = new CategoryMap(getActivity());
        refreshFeedLayout.setRefreshing(true);

        //presenter = new ProfilePresenter(this);
        Toolbar toolbarBlue = (Toolbar) getActivity().findViewById(R.id.toolbar_blue_common);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarBlue);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView) view.findViewById(R.id.text_white)).setText("FEED");

        toolbarBlue.findViewById(R.id.add_post).setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), CreatePostActivity.class);
            getActivity().startActivity(intent);
        });
        presenter = new FeedPresenter(this);
        adapter = new PostAdapter(new ArrayList<Post>(), presenter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        feedList.setLayoutManager(llm);
        feedList.setAdapter(adapter);

        view.findViewById(R.id.category_layout).setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), FeedSortActivity.class);
            startActivityForResult(intent, 5);
        });
        categoryText.setText(categoryMap.getNameByCode(SharedPrefer.getCategory()));
        refreshFeedLayout.setOnRefreshListener(() -> {
            adapter.deleteAll();
            presenter.initList(SharedPrefer.getCategory());
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5 && resultCode == 10) {


            Category categoryResult = data.getParcelableExtra("code");
            SharedPrefer.setCategory(categoryResult.getCode());
            adapter.deleteAll();
            adapter.setType(categoryResult.getCode());

            if (categoryResult.getCode().equals("ALL"))
                categoryText.setText("All categories");
            else
                categoryText.setText(categoryResult.getName());
            presenter.initList(categoryResult.getCode());
            refreshFeedLayout.setRefreshing(true);

        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void addPost(Post post) {
        refreshFeedLayout.setRefreshing(false);
        adapter.addPost(post);
    }

    @Override
    public void onError(String localizedMessage) {
        refreshFeedLayout.setRefreshing(false);
        new SnackBar.Builder(getActivity()).withMessage(localizedMessage).show();
    }

    @Override
    public void onPostClicked(Post post) {
        Intent intent = new Intent(getActivity(), SinglePostActivity.class);
        intent.putExtra(SinglePostActivity.POST_KEY, post);
        intent.putExtra(SinglePostActivity.CORDS_KEY, post.getLocation());
        startActivity(intent);
    }
}
