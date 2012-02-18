package com.lemontracker.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Event;

import java.util.List;

public class EventArrayAdapter extends ArrayAdapter<Event> {

    public EventArrayAdapter(Context context, int id, List<Event> events) {
        super(context, id, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_cell_layout, null);
        }

        Event event = getItem(position);

        /*
        ImageView icon = (ImageView) row.findViewById(R.id.listIcon);
        Bitmap bitmap = resource(subcat.getName());
        icon.setImageBitmap(bitmap);
        */

        TextView name = (TextView) row.findViewById(R.id.header);
        name.setText(event.getName());

        TextView blurb = (TextView) row.findViewById(R.id.blurb);
        blurb.setText(event.getBlurb());

        TextView date = (TextView) row.findViewById(R.id.date);
        date.setText(event.getDateStart().toString());

        return row;
    }

    private Bitmap resource(String name) {
        return null;
    }

}


