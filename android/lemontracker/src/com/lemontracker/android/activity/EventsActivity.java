package com.lemontracker.android.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Category;
import com.lemontracker.android.model.Event;
import com.lemontracker.android.model.User;
import com.lemontracker.android.util.EventArrayAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsActivity extends ListActivity {

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
    }

}
