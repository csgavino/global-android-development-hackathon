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
import com.lemontracker.android.MapItemizedOverlay;
import com.lemontracker.android.R;

import java.util.List;

@EActivity(R.layout.map_layout)
public class EventMapActivity extends MapActivity {
    public static final String TAG = EventMapActivity.class.getSimpleName();

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

        GeoPoint point = new GeoPoint(19240000, -99120000);
        OverlayItem overlayitem = new OverlayItem(point, "", "");

        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
