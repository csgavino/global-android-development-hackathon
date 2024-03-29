package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lemontracker.android.R;
import com.lemontracker.android.base.Actionbar;
import com.lemontracker.android.model.Event;
import com.lemontracker.android.util.EventArrayAdapter;
import com.teamcodeflux.android.Result;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import static com.lemontracker.android.WebService.*;
import static com.teamcodeflux.android.RestTemplateFactory.*;
import static java.util.Arrays.*;

@EActivity(R.layout.list_layout)
public class EventListActivity extends Activity implements Actionbar {
    protected static final String TAG = EventListActivity.class.getSimpleName();

    @ViewById(R.id.__list)
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchEvents();
    }

    @Background
    public void fetchEvents() {
        try {
            String URL = allEvents();
            Event[] result = getRestTemplate().getForObject(URL, Event[].class);
            processResult(new Result<ArrayList<Event>>(new ArrayList<Event>(asList(result))));
        } catch (RestClientException e) {
            processResult(new Result(e));
        }
    }

    @UiThread
    public void processResult(Result result) {
        if (result.hasErrors()) {
            Log.e(TAG, result.getResult().toString());
            EventListActivity.this.finish();
        } else {
            List __events = (ArrayList<Event>) result.getResult();
            ArrayAdapter adapter = new EventArrayAdapter(this, R.layout.list_cell_layout, __events);
            list.setAdapter(adapter);
        }
    }

    @ItemClick
    void __list(Object object) {
        Intent i = new Intent(this, EventViewActivity_.class);
        Event event = (Event) object;
        i.putExtra("event", event);
        startActivity(i);
    }

    @Click(R.id.radiusButton)
    public void radiusButtonClicked() {
        Intent i = new Intent(this, RadiusActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Click(R.id.header)
    public void logoButtonClicked() {
        Intent i = new Intent(this, DashboardActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    @Click(R.id.listButton)
    public void listButtonClicked() {
    }

    @Click(R.id.searchButton)
    public void searchButtonClicked() {
        Intent i = new Intent(this, SearchActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
