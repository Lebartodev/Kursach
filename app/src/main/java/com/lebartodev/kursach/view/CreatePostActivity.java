package com.lebartodev.kursach.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseCreatePostPresenter;
import com.lebartodev.kursach.presenter.CreatePostPresenter;
import com.lebartodev.kursach.utils.CategoryMap;
import com.lebartodev.kursach.utils.SharedPrefer;

public class CreatePostActivity extends AppCompatActivity implements CreatePostPage {
    private View publishButton;
    private EditText editPost;
    private BaseCreatePostPresenter presenter;
    private TextView categoryText;
    private CategoryMap categoryMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryMap = new CategoryMap(this);
        setContentView(R.layout.activity_create_post);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_blue_common);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar()
         //       .setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow_white));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.findViewById(R.id.menuImage).setVisibility(View.GONE);

        ((TextView) toolbar.findViewById(R.id.text_white)).setText("CREATE POST");

        categoryText = (TextView) findViewById(R.id.category_name);

        publishButton = findViewById(R.id.publish_button);
        editPost = (EditText) findViewById(R.id.post_edit_text);
        findViewById(R.id.category_layout).setOnClickListener(view -> {
            startActivityForResult(new Intent(this, CategoryActivity.class), 3);
        });
        presenter = new CreatePostPresenter(this);
        publishButton.setOnClickListener(view -> {
            presenter.createPost(editPost.getText().toString());
        });

    }

    @Override
    public void onPostCreated(Post post) {

        Intent intent = new Intent(this, SinglePostActivity.class);
        intent.putExtra(SinglePostActivity.POST_KEY, post);
        intent.putExtra(SinglePostActivity.CORDS_KEY, post.getLocation());
        startActivity(intent);
        finish();
        //new SnackBar.Builder(this).withMessage("Post created: "+post.getId()).show();

    }

    @Override
    public void onError(String localizedMessage) {
        new SnackBar.Builder(this).withMessage(localizedMessage).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 3:

                if (resultCode == 10) {
                    this.presenter.onPickCategory(data.getExtras().getString("RESULT"));
                    categoryText.setText(categoryMap.getNameByCode(data.getExtras().getString("RESULT")));
                }


                break;


        }
        super.onActivityResult(requestCode, resultCode, data);

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
    }}
