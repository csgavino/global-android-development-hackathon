package com.lemontracker.android.base;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lemontracker.android.R;
import com.lemontracker.android.activity.EventListActivity;
import com.lemontracker.android.activity.EventViewActivity_;
import com.lemontracker.android.model.Event;
import com.lemontracker.android.util.EventArrayAdapter;
import com.teamcodeflux.android.Result;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import static com.teamcodeflux.android.RestTemplateFactory.*;
import static java.util.Arrays.*;


@EActivity(R.layout.specific_list_layout)
public abstract class BaseListActivity extends Activity {
    protected static final String TAG = EventListActivity.class.getSimpleName();

    @ViewById(R.id.__list)
    ListView list;
    @ViewById
    TextView category;

    @AfterViews
    public void afterCreate() {
        fetchEntries();
        setHeader();
    }

    protected abstract String service();

    private void setHeader() {
        category.setText(header());
    }

    protected abstract String header();

    @Background
    public void fetchEntries() {
        try {
            Event[] result = getRestTemplate().getForObject(service(), Event[].class);
            processResult(new Result<ArrayList<Event>>(new ArrayList<Event>(asList(result))));
        } catch (RestClientException e) {
            processResult(new Result(e));
        }
    }

    @UiThread
    public void processResult(Result result) {
        if (result.hasErrors()) {
            Log.e(TAG, result.getResult().toString());
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
