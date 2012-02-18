package com.lemontracker.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties({
        "created_at",
        "updated_at"
})
public class Event implements Parcelable {
    private Long id;
    private String name;
    private String blurb;
    private String description;
    @JsonProperty("image_url")
    private String imageURL;
    @JsonProperty("thumb_url")
    private String thumbnailURL;
    @JsonProperty("date_start")
    private Date dateStart;
    @JsonProperty("date_end")
    private Date dateEnd;
    private Float latitude;
    private Float longitude;
    @JsonProperty("category_id")
    private Integer categoryId;
    /*
    private Category category;
    private User user;
    */

    private Event() {
    }

    public static Event buildEvent(final String name,
                                   final String blurb,
                                   final String description,
                                   final String imageURL,
                                   final String thumbnailURL,
                                   final Date dateStart,
                                   final Date dateEnd,
                                   final Category category,
                                   final User user) {

        Event event = new Event();
        event.name = name;
        event.blurb = blurb;
        event.description = description;
        event.imageURL = imageURL;
        event.thumbnailURL = thumbnailURL;
        event.dateStart = dateStart;
        event.dateEnd = dateEnd;

        return event;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(blurb);
        parcel.writeString(description);
        parcel.writeString(imageURL);
        parcel.writeString(thumbnailURL);
        parcel.writeSerializable(dateStart);
        parcel.writeSerializable(dateEnd);
        /*
        parcel.writeParcelable(category, i);
        parcel.writeParcelable(user, i);
        */
    }

    private void readFromParcel(final Parcel source) {
        id = source.readLong();
        name = source.readString();
        blurb = source.readString();
        description = source.readString();
        imageURL = source.readString();
        thumbnailURL = source.readString();
        dateStart = (Date) source.readSerializable();
        dateEnd = (Date) source.readSerializable();
        /*
        category = source.readParcelable(Category.class.getClassLoader());
        user = source.readParcelable(User.class.getClassLoader());
        */
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

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
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

    /*
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        this.user = user;
    }
    */

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
