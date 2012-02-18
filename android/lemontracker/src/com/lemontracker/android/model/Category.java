package com.lemontracker.android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private Long id;
    private String name;

    public static Category buildCategory(final String name) {
        Category cat = new Category();
        cat.name = name;
        return cat;
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(final Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(final int size) {
            return new Category[size];
        }
    };

    private Category() {
    }

    public Category(final Parcel source) {
        readFromParcel(source);
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public static Parcelable.Creator<Category> getCreator() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int flags) {
        parcel.writeLong(id);
        parcel.writeString(name);
    }

    private void readFromParcel(final Parcel source) {
        id = source.readLong();
        name = source.readString();
    }

}
