package com.lemontracker.android.util;

import android.content.Context;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Event;
import com.lemontracker.android.model.column.EventProperty;
import com.teamcodeflux.android.adapter.AbstractSimpleAdapterBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lemontracker.android.model.column.EventProperty.*;

public class ListAdapterBuilder extends AbstractSimpleAdapterBuilder<Event> {

    public ListAdapterBuilder(Context context, List<Event> data) {
        super(context, data);
    }

    @Override
    protected List<? extends Map<String, ?>> data(List<Event> events) {
        List<Map<String, String>> filledEvents = new ArrayList<Map<String, String>>();
        for (Event event : events) {
            Map<String, String> eventMap = buildMapFromEvent(event);
            filledEvents.add(eventMap);
        }
        return filledEvents;
    }

    private static Map<String, String> buildMapFromEvent(Event event) {
        HashMap<String, String> eventMap = new HashMap<String, String>();
        eventMap.put(HEADER.toString(), event.getName());
        eventMap.put(BLURB.toString(), event.getBlurb());
        eventMap.put(DATE.toString(), event.getDateStart().toString());
        return eventMap;
    }

    @Override
    protected int resource() {
        return R.layout.list_cell_layout;
    }

    @Override
    protected String[] from() {
        return EventProperty.toArray();
    }

    @Override
    protected int[] to() {
        return new int[]{
                R.id.header,
                R.id.blurb,
                R.id.date
        };
    }
}
