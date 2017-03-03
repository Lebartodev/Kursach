package com.lebartodev.kursach.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alexandr on 23.01.2017.
 */

public class Category implements Parcelable {
    private String name;
    private boolean checked;
    private String code;

    public Category(boolean checked, String name, String code) {
        this.checked = checked;
        this.name = name;
        this.code=code;
    }

    protected Category(Parcel in) {
        name = in.readString();
        checked = in.readByte() != 0;
        code = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeByte((byte) (checked ? 1 : 0));
        parcel.writeString(code);
    }
}
