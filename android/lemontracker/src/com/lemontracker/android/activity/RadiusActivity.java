package com.lemontracker.android.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.lemontracker.android.MapItemizedOverlay;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Event;
import com.teamcodeflux.android.Result;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import static com.lemontracker.android.WebService.*;
import static com.teamcodeflux.android.RestTemplateFactory.*;
import static java.util.Arrays.*;

@EActivity(R.layout.map_layout)
public class RadiusActivity extends MapActivity {
    private static final String TAG = RadiusActivity.class.getSimpleName();

    Float latitude = new Float("14.620748");
    Float longitude = new Float("121.053451");

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
    }

    @Override
    public void onResume() {
        super.onResume();
        // You need to feed this function with your current lat, lon
        fetchEvents();
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    @Background
    public void fetchEvents() {
        try {
            MultiValueMap<String, Float> map = new LinkedMultiValueMap<String, Float>();
            map.add("latitude", latitude);//Double.toString(latitude));
            map.add("longitude", longitude);// Double.toString(longitutde));

            String URL = radius();
            Event[] result = getRestTemplate().postForObject(URL, map, Event[].class);

            processResult(new Result<ArrayList<Event>>(new ArrayList<Event>(asList(result))));
        } catch (RestClientException e) {
            Log.e(TAG, e.toString());
        }
    }

    @UiThread
    public void processResult(Result result) {
        if (!result.hasErrors()) {
            List __events = (ArrayList<Event>) result.getResult();
            parseResults(__events);
        }
    }

    private GeoPoint getCenter() {
        int latE6 = (int) (latitude * 1e6);
        int lonE6 = (int) (longitude * 1e6);
        return new GeoPoint(latE6, lonE6);
    }

    private void parseResults(List<Event> events) {
        if (!events.isEmpty()) {
            mapview.getController().setCenter(getCenter());

            for (Event event : events) {
                int latE6 = (int) (event.getLatitude() * 1e6);
                int lonE6 = (int) (event.getLongitude() * 1e6);
                GeoPoint point = new GeoPoint(latE6, lonE6);
                OverlayItem overlayitem = new OverlayItem(point, "", "");
                itemizedOverlay.addOverlay(overlayitem);
            }

            mapOverlays.add(itemizedOverlay);

        } else {
            Log.e(TAG, "No Results");
        }
    }

}
