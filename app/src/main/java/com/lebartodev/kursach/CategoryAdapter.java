package com.lebartodev.kursach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.lebartodev.kursach.model.Category;

import java.util.List;

/**
 * Created by Александр on 28.11.2016.
 */

public class CategoryAdapter extends BaseAdapter {
    private List<Category> fullList;
    private Context context;

    public CategoryAdapter(Context context, List<Category> fullList) {
        this.fullList = fullList;
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
                    .inflate(R.layout.i_category, null);
        }

        convertView.findViewById(R.id.item_cat).setOnClickListener(view -> {

            Intent intent = new Intent();
            intent.putExtra("RESULT", cat.getCode());
            ((Activity) context).setResult(10, intent);
            ((Activity) context).finish();


        });


        ((TextView) convertView.findViewById(R.id.text_cat))
                .setText(cat.getName());
        if (cat.isChecked())
            ((CheckBox) convertView.findViewById(R.id.cat_checkbox)).setChecked(true);
        else
            ((CheckBox) convertView.findViewById(R.id.cat_checkbox)).setChecked(false);
        //convertView.setClickable(false);
        //checkBox.setClickable(false);

        return convertView;
    }


}
