package com.lemontracker.android.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.lemontracker.android.MapItemizedOverlay;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Event;

import java.util.List;

@EActivity(R.layout.map_layout)
public class EventMapActivity extends MapActivity {
    public static final String TAG = EventMapActivity.class.getSimpleName();

    @Extra("event")
    Event event;

    MapItemizedOverlay itemizedOverlay;
    List<Overlay> mapOverlays;
    Drawable drawable;
    MapView mapview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void afterCreate() {
        mapview = (MapView) findViewById(R.id.mapview);
        mapview.setBuiltInZoomControls(true);
        mapOverlays = mapview.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        itemizedOverlay = new MapItemizedOverlay(drawable);

        int latE6 = (int) (event.getLatitude() * 1e6);
        int lonE6 = (int) (event.getLongitude() * 1e6);

        GeoPoint point = new GeoPoint(latE6, lonE6);
        OverlayItem overlayitem = new OverlayItem(point, "", "");

        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
