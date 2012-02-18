package com.lemontracker.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.lemontracker.android.R;

@EActivity
public class EventViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
    }

    @Click(R.id.map)
    public void mapCellClicked() {
    }
}
