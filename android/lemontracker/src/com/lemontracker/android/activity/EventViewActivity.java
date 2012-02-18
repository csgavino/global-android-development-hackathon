package com.lemontracker.android.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Category;
import com.lemontracker.android.model.Event;
import com.teamcodeflux.android.Result;
import org.springframework.web.client.RestClientException;

import java.net.URL;

import static com.lemontracker.android.WebService.*;
import static com.lemontracker.android.util.IOUtils.*;
import static com.teamcodeflux.android.RestTemplateFactory.*;

@EActivity(R.layout.event_layout)
public class EventViewActivity extends Activity {
    public static String TAG = EventViewActivity.class.getSimpleName();

    @Extra("event")
    Event event;
    @ViewById
    TextView category;
    @ViewById
    TextView h1;
    @ViewById
    TextView h2;
    @ViewById
    TextView description;
    @ViewById
    ImageView banner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveEntry();
        retrieveBanner();
    }

    @AfterViews
    public void afterCreate() {
        h1.setText(event.getName());
        h2.setText(event.getDateStart().toString());
        description.setText(event.getDescription());

    }

    @Click(R.id.map)
    public void mapCellClicked() {
    }

    @Background
    public void retrieveEntry() {
        try {
            String URL = category(event.getCategoryId());
            Category result = getRestTemplate().getForObject(URL, Category.class);
            processResult(new Result(result));
        } catch (RestClientException e) {
            Log.e(TAG, e.toString());
            processResult(new Result(e));
        }
    }

    @UiThread
    public void processResult(Result result) {
        if (result.hasErrors()) {
            EventViewActivity.this.finish();
        } else {
            Category cat = (Category) result.getResult();
            category.setText(cat.getName());
        }
    }

    @Background
    public void retrieveBanner() {
        try {
            String URL = banner(event.getImageURL());
            Bitmap bitmap = loadImageFromURL(new URL(URL));
            renderBanner(bitmap);
        } catch (Exception e) {
            Log.e(TAG, "Failed to render banner");
        }
    }

    @UiThread
    public void renderBanner(Bitmap bitmap) {
        banner.setImageBitmap(bitmap);
    }

}
