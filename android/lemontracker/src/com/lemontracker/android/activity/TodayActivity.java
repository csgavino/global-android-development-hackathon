package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.googlecode.androidannotations.annotations.EActivity;
import com.lemontracker.android.R;

@EActivity
public class TodayActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_layout);
        Intent i = new Intent(this, EventViewActivity_.class);
        startActivity(i);
    }

}
