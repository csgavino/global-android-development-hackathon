package com.lemontracker.android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private Long id;
    private String name;

    public static User buildUser(final String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(final Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(final int size) {
            return new User[size];
        }
    };

    private User() {
    }

    public User(final Parcel source) {
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

    public static Parcelable.Creator<User> getCreator() {
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
