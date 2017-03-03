package com.lebartodev.kursach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.lebartodev.kursach.model.Category;
import com.lebartodev.kursach.presenter.BaseFeedSortPresenter;

import java.util.List;

/**
 * Created by Александр on 28.11.2016.
 */

public class CategorySortAdapter extends BaseAdapter {
    private List<Category> fullList;
    private BaseFeedSortPresenter presenter;
    private Context context;

    public CategorySortAdapter(Context context, BaseFeedSortPresenter presenter, List<Category> fullList) {
        this.fullList = fullList;
        this.presenter = presenter;
        this.context = context;

    }

    @Override
    public int getCount() {
        return fullList.size();
    }

    @Override
    public Object getItem(int i) {
        return fullList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Category cat = (Category) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.i_category_sort, null);
        }

        convertView.findViewById(R.id.item_category).setOnClickListener(view -> presenter.onClickCategory(cat));


        ((TextView) convertView.findViewById(R.id.text_cat))
                .setText(cat.getName());

        return convertView;
    }


}
