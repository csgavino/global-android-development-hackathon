package com.lemontracker.android.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
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
    private static final int WAIT_TIME = 5000;

    private int executions;
    private Float latitude = new Float("14.620748");
    private Float longitude = new Float("121.053451");
    private String transId = "23737294091789509982012021903024235";
    private MapItemizedOverlay itemizedOverlay;
    private List<Overlay> mapOverlays;
    private Drawable drawable;
    private MapView mapview;
    private Handler updateHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestUpdates();
        // This is a manual call and should be removed
        //updateHandler.postDelayed(updateCoordsTask, WAIT_TIME);
    }

    @AfterViews
    public void afterCreate() {
        mapview = (MapView) findViewById(R.id.mapview);
        mapview.setBuiltInZoomControls(true);
        mapOverlays = mapview.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        itemizedOverlay = new MapItemizedOverlay(drawable, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        executions = 0;
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

    private GeoPoint getCenter() {
        int latE6 = (int) (latitude * 1e6);
        int lonE6 = (int) (longitude * 1e6);
        return new GeoPoint(latE6, lonE6);
    }

    @Background
    public void requestUpdates() {
        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("Placeholder", "Boobs");
            transId = getRestTemplate().postForObject(locate(), map, String.class);
            updateHandler.postDelayed(updateCoordsTask, WAIT_TIME);
        } catch (RestClientException e) {
            Log.e(TAG, "Failed to post request " + e.toString());
        }
    }

    private Runnable updateCoordsTask = new Runnable() {
        @Override
        public void run() {
            try {
                Log.e(TAG, "Updating coords");
                MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
                map.add("transaction_id", transId);

                Event[] result = getRestTemplate().postForObject(locations(), map, Event[].class);
                processResult(new Result<ArrayList<Event>>(new ArrayList<Event>(asList(result))));
            } catch (RestClientException e) {
                Log.e(TAG, "Rest client exception " + e.toString());
            }
        }
    };

    @UiThread
    public void processResult(Result result) {
        if (!result.hasErrors()) {
            List __events = (ArrayList<Event>) result.getResult();
            parseResults(__events);
        }
    }

    private void parseResults(List<Event> events) {
        if (!events.isEmpty()) {
            mapview.getController().setCenter(getCenter());
            Toast.makeText(this, "Updating the map", Toast.LENGTH_LONG).show();
            fillOverlay(events);
        } else {
            Log.e(TAG, "No Results");
            reupdateCoords();
        }
    }

    private void fillOverlay(List<Event> events) {
        for (Event event : events) {
            int latE6 = (int) (event.getLatitude() * 1e6);
            int lonE6 = (int) (event.getLongitude() * 1e6);
            GeoPoint point = new GeoPoint(latE6, lonE6);
            OverlayItem overlayitem = new OverlayItem(point, event.getName(), event.getBlurb());
            itemizedOverlay.addOverlay(overlayitem);
        }
        mapOverlays.add(itemizedOverlay);
    }

    private void reupdateCoords() {
        if (executions < 10) {
            Log.e(TAG, "OK executed " + executions);
            updateHandler.postDelayed(updateCoordsTask, WAIT_TIME);
            executions++;
        } else {
            Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
        }
    }

}
