package com.lebartodev.kursach.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lebartodev.kursach.CategorySortAdapter;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Category;
import com.lebartodev.kursach.presenter.BaseFeedSortPresenter;
import com.lebartodev.kursach.presenter.FeedSortPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedSortActivity extends AppCompatActivity implements FeedSortPage {

    private CategorySortAdapter adapter;
    private BaseFeedSortPresenter presenter;

    ListView categoriesList;

    View menuButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_sort);
        categoriesList = (ListView) findViewById(R.id.list_categories);
        initSort();


    }

    void initSort() {
        Toolbar transToolbar = (Toolbar) findViewById(R.id.toolbar_blue_common);
        setSupportActionBar(transToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        menuButton = transToolbar.findViewById(R.id.menuImage);
        ((TextView) findViewById(R.id.text_white)).setText("CATEGORIES");
        ((ImageView) transToolbar.findViewById(R.id.menuImage)).setImageResource(R.drawable.ic_back_arrow_white);
        menuButton.setOnClickListener(view -> finish());
        List<Category> categories = new ArrayList<>();
        List<String> categoriesNames = Arrays.asList(getResources().getStringArray(R.array.categories));
        List<String> categoriesCodes = Arrays.asList(getResources().getStringArray(R.array.categories_code));
        for (int i = 0; i < categoriesCodes.size(); i++) {
            categories.add(new Category(false, categoriesNames.get(i), categoriesCodes.get(i)));
        }
        presenter = new FeedSortPresenter(this);
        adapter = new CategorySortAdapter(this, presenter, categories);
        categoriesList.setAdapter(adapter);
        findViewById(R.id.fav_posts_layout).setOnClickListener(view -> presenter.onClickCategory(new Category(false,"Saved posts","FAVS")));
    }
    @Override
    public void openCategory(Category code, int distance) {
        Intent intent = new Intent();
        intent.putExtra("code", code);
        setResult(10, intent);
        finish();



    }
}
