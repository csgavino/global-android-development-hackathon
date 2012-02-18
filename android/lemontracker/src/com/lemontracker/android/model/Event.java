package com.lemontracker.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Event implements Parcelable {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private String thumbnailURL;
    private Date dateStart;
    private Date dateEnd;
    private Category category;
    private User user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imageURL);
        parcel.writeString(thumbnailURL);
        parcel.writeSerializable(dateStart);
        parcel.writeSerializable(dateEnd);
        parcel.writeParcelable(category, i);
        parcel.writeParcelable(user, i);
    }

    private void readFromParcel(final Parcel source) {
        id = source.readLong();
        name = source.readString();
        description = source.readString();
        imageURL = source.readString();
        thumbnailURL = source.readString();
        dateStart = (Date) source.readSerializable();
        dateEnd = (Date) source.readSerializable();
        category = source.readParcelable(Category.class.getClassLoader());
        user = source.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(final Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(final int size) {
            return new Event[size];
        }
    };

    public Event(final Parcel source) {
        readFromParcel(source);
    }

    public static Parcelable.Creator<Event> getCreator() {
        return CREATOR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
