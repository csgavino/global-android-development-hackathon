package com.lemontracker.android.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Category;
import com.lemontracker.android.model.Event;
import com.lemontracker.android.model.User;
import com.lemontracker.android.util.EventArrayAdapter;
import com.teamcodeflux.android.Result;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.lemontracker.android.WebService.*;
import static com.teamcodeflux.android.RestTemplateFactory.*;
import static java.util.Arrays.*;

@EActivity
public class EventListActivity extends ListActivity {
    protected static final String TAG = EventListActivity.class.getSimpleName();
    protected List<Event> events;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        events = new ArrayList<Event>();

        for (int i = 0; i < 5; i++) {

            Category category = Category.buildCategory(
                    "name " + i
            );

            User user = User.buildUser(
                    "name " + i
            );

            Event event = Event.buildEvent(
                    "name " + i,
                    "blurb " + i,
                    "description " + i,
                    "imageURL " + i,
                    "thumbnailURL " + i,
                    new Date(),
                    new Date(),
                    category,
                    user
            );

            events.add(event);
        }

        ArrayAdapter adapter = new EventArrayAdapter(this, R.layout.list_cell_layout, events);
        setListAdapter(adapter);

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
    void processResult(Result result) {
        if (result.hasErrors()) {
            EventListActivity.this.finish();
        } else {
            List __events = (ArrayList<Event>) result.getResult();
            ArrayAdapter adapter = new EventArrayAdapter(this, R.layout.list_cell_layout, __events);
            setListAdapter(adapter);
        }
    }


}
