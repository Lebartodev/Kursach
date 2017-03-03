package com.lebartodev.kursach.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lebartodev.kursach.CommentAdapter;
import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Comment;
import com.lebartodev.kursach.model.Coordinates;
import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseSinglePostPresenter;
import com.lebartodev.kursach.presenter.SinglePostPresenter;

import java.util.ArrayList;

public class SinglePostActivity extends AppCompatActivity implements SinglePostPage {
    private View sendIcon;
    private Post post;
    public final static String POST_KEY = "POST_KEY";
    public final static String CORDS_KEY = "CORDS_KEY";
    private RecyclerView commentsList;
    private EditText commentEdit;
    private Coordinates coords;
    private CommentAdapter adapter;
    private BaseSinglePostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        commentsList = (RecyclerView) findViewById(R.id.comments_list);
        commentEdit = (EditText) findViewById(R.id.editText);
        sendIcon = findViewById(R.id.sendIcon);
        sendIcon.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(commentEdit.getText().toString())) {
                presenter.createComment(commentEdit.getText().toString(), post.getId());

                commentEdit.setText("");
            }

        });
        post = getIntent().getExtras().getParcelable(POST_KEY);
        coords = getIntent().getExtras().getParcelable(CORDS_KEY);
        post.setLocation(coords);
        Toolbar transToolbar = (Toolbar) findViewById(R.id.toolbar_blue_common);
        setSupportActionBar(transToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        transToolbar.findViewById(R.id.menuImage).setVisibility(View.GONE);
        presenter = new SinglePostPresenter(this, post.getId());


        ((TextView) findViewById(R.id.text_white)).setText("COMMENTS");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        commentsList.setLayoutManager(mLayoutManager);

        adapter = new CommentAdapter(post, new ArrayList<>());
        commentsList.setAdapter(adapter);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addComment(Comment comment) {
        adapter.addComment(comment);
    }

    @Override
    public void onCommentAdded() {

    }
}
