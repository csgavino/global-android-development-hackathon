package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.lemontracker.android.base.Actionbar;
import com.lemontracker.android.model.Category;
import com.lemontracker.android.model.Event;
import com.teamcodeflux.android.Result;
import org.springframework.web.client.RestClientException;

import java.net.URL;

import static com.lemontracker.android.WebService.*;
import static com.lemontracker.android.util.IOUtils.*;
import static com.teamcodeflux.android.RestTemplateFactory.*;

@EActivity(R.layout.event_layout)
public class EventViewActivity extends Activity implements Actionbar {
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
        fetchEntry();
        fetchBanner();
    }

    @AfterViews
    public void afterCreate() {
        h1.setText(event.getName());
        h2.setText(event.getDateStart().toString());
        description.setText(event.getDescription());

    }

    @Click(R.id.map)
    public void mapCellClicked() {
        Intent i = new Intent(this, EventMapActivity_.class);
        i.putExtra("event", event);
        startActivity(i);
    }

    @Background
    public void fetchEntry() {
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
    public void fetchBanner() {
        try {
            String URL = image(event.getImageURL());
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

    @Click(R.id.radiusButton)
    public void radiusButtonClicked() {
        Intent i = new Intent(this, RadiusActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Click(R.id.listButton)
    public void listButtonClicked() {
        Intent i = new Intent(this, EventListActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Click(R.id.searchButton)
    public void searchButtonClicked() {
        Intent i = new Intent(this, SearchActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
