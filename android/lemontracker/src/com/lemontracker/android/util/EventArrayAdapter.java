package com.lemontracker.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Event;
import com.teamcodeflux.android.Result;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.lemontracker.android.WebService.*;
import static com.lemontracker.android.util.IOUtils.*;

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

        ImageView icon = (ImageView) row.findViewById(R.id.image);
        new LoadContentTask().execute(event, icon);

        TextView name = (TextView) row.findViewById(R.id.header);
        name.setText(event.getName());

        TextView blurb = (TextView) row.findViewById(R.id.blurb);
        blurb.setText(event.getBlurb());

        TextView date = (TextView) row.findViewById(R.id.date);
        date.setText(event.getDateStart().toString());

        return row;
    }

    private class LoadContentTask extends AsyncTask<Object, Void, Result<Bitmap>> {
        private Event event;
        private ImageView view;

        @Override
        protected Result<Bitmap> doInBackground(Object... arg) {
            event = (Event) arg[0];
            view = (ImageView) arg[1];

            try {
                String thumbnailURL = thumb(this.event.getThumbnailURL());
                Bitmap bitmap = loadImageFromURL(new URL(thumbnailURL));
                return new Result<Bitmap>(bitmap);
            } catch (IOException e) {
                return new Result(e);
            }
        }

        protected void onPostExecute(Result<Bitmap> result) {
            if (!result.hasErrors()) {
                view.setImageBitmap(result.getResult());
                view.postInvalidate();
            }
        }

    }
}


