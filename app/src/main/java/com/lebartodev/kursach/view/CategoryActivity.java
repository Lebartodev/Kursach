package com.lebartodev.kursach.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

import com.lebartodev.kursach.CategoryAdapter;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Category;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryActivity extends AppCompatActivity {

    ListView languagesList;
    String selectedCategory;

    private CategoryAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        languagesList = (ListView) findViewById(R.id.languageList);
        initCategories();
    }

    void initCategories() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_blue_common);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar()
        //        .setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow_white));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((TextView) toolbar.findViewById(R.id.text_white)).setText("CHOOSE CATEGORY");

        toolbar.findViewById(R.id.menuImage).setVisibility(View.GONE);

        Map<String, String> categories = new HashMap<>();
        List<String> categoriesNames = Arrays.asList(getResources().getStringArray(R.array.categories));
        List<String> categoriesCodes = Arrays.asList(getResources().getStringArray(R.array.categories_code));

        for (int i = 1; i < categoriesCodes.size(); i++) {
            categories.put(categoriesCodes.get(i), categoriesNames.get(i));
        }


        List<Category> list = new ArrayList<>();


        for (String str : categories.keySet())

            list.add(new Category(false, categories.get(str), str));


        for (Category category : list)
            if (category.getCode().equals(selectedCategory))
                category.setChecked(true);
        adapter = new CategoryAdapter(this, list);
        languagesList.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
