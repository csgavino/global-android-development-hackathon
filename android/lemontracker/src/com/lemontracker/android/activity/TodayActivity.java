package com.lemontracker.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity
public class TodayActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.today_layout);
        Intent i = new Intent(this, EventViewActivity_.class);
        startActivity(i);
        */
    }

}
