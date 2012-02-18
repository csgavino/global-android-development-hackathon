package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.lemontracker.android.R;

@EActivity
public class DashboardActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
    }

    @Click(R.id.listButton)
    public void listButtonClicked() {
        Intent i = new Intent(this, EventListActivity_.class);
        startActivity(i);
    }

    @Click(R.id.searchButton)
    public void searchButtonClicked() {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    @Click(R.id.today)
    public void todayCellClicked() {
        Intent i = new Intent(this, TodayActivity_.class);
        startActivity(i);
    }

    @Click(R.id.shops)
    public void shopsCellClicked() {
        Intent i = new Intent(this, ShopsActivity_.class);
        startActivity(i);
    }

    @Click(R.id.restaurants)
    public void restaurantsCellClicked() {

    }

}
