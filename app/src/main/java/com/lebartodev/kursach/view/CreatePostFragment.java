package com.lebartodev.kursach.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Post;
import com.lebartodev.kursach.presenter.BaseCreatePostPresenter;
import com.lebartodev.kursach.presenter.CreatePostPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePostFragment extends Fragment implements CreatePostPage {
    private EditText postText;
    private Button postButton;
    private BaseCreatePostPresenter presenter;


    public CreatePostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        postText= (EditText) view.findViewById(R.id.post_text);
        postButton= (Button) view.findViewById(R.id.add_button);

        presenter = new CreatePostPresenter(this);

        postButton.setOnClickListener(view1 -> {
            presenter.createPost(postText.getText().toString());
        });
        return view;
    }

    @Override
    public void onPostCreated(Post post) {

    }
}
