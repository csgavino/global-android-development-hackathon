package com.lemontracker.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.lemontracker.android.R;

public class Dashboard extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
    }

}
