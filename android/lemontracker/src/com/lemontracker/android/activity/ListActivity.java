package com.lemontracker.android.activity;

import android.app.Activity;
import android.os.Bundle;
import com.lemontracker.android.R;

public class ListActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
    }
}
