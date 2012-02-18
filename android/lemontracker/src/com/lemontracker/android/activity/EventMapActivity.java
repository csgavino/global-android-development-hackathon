package com.lemontracker.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.googlecode.androidannotations.annotations.EActivity;
import com.lemontracker.android.R;

@EActivity(R.layout.map_layout)
public class EventMapActivity extends Activity {
    public static final String TAG = EventMapActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Hello");
    }
}
