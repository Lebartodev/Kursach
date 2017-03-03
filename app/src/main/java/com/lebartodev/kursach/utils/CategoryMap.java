package com.lebartodev.kursach.utils;

import android.content.Context;

import com.lebartodev.kursach.R;
import com.lebartodev.kursach.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Александр on 24.02.2017.
 */

public class CategoryMap {
    private Map<String,String> categories=new HashMap<>();
    public CategoryMap(Context context){
        String[] categoriesName=context.getResources().getStringArray(R.array.categories);
        String[] categoriesCodes=context.getResources().getStringArray(R.array.categories_code);
        for(int i = 0;i<categoriesCodes.length;i++)
            categories.put(categoriesCodes[i],categoriesName[i]);
    }
    public String getNameByCode(String name){
        return categories.get(name);

    }
}
