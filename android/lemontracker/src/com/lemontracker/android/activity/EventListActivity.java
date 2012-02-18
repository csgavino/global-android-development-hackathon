package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lemontracker.android.R;
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
public class EventListActivity extends Activity {
    protected static final String TAG = EventListActivity.class.getSimpleName();
    protected List<Event> events;

    @ViewById(R.id.__list)
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieveEntries();
    }

    @Background
    public void retrieveEntries() {
        try {
            String URL = events(3L);
            Event[] result = getRestTemplate().getForObject(URL, Event[].class);
            processResult(new Result<ArrayList<Event>>(new ArrayList<Event>(asList(result))));
        } catch (RestClientException e) {
            processResult(new Result(e));
        }
    }

    @UiThread
    public void processResult(Result result) {
        if (result.hasErrors()) {
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
}
