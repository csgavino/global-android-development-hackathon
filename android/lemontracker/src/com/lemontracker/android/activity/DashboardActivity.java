package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lemontracker.android.R;

@EActivity
public class DashboardActivity extends Activity {

    @ViewById
    TextView lemonTextView;

    @ViewById
    TextView trackerTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        lemonTextView.setText("Lemon");
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "FuturaLT-CondensedBold.ttf");
        lemonTextView.setTypeface(tf);
        trackerTextView.setText("Tracker");
        trackerTextView.setTypeface(tf);
    }

    @Click(R.id.listButton)
    public void listButtonClick() {
        Intent i = new Intent(this, ListActivity.class);
        startActivity(i);
    }

    @Click(R.id.searchButton)
    public void searchButtonClick() {

    }


}
