package com.lemontracker.android.activity;

import android.content.Intent;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.lemontracker.android.R;
import com.lemontracker.android.base.BaseListActivity_;

import static com.lemontracker.android.WebService.*;

@EActivity
public class ConcertsActivity extends BaseListActivity_ {

    @Override
    protected String service() {
        return events(1L);
    }

    @Override
    protected String header() {
        return "Concerts";
    }

    @Click(R.id.radiusButton)
    public void radiusButtonClicked() {
        Intent i = new Intent(this, RadiusActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Click(R.id.header)
    public void logoButtonClicked() {
        Intent i = new Intent(this, DashboardActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Click(R.id.listButton)
    public void listButtonClicked() {
        Intent i = new Intent(this, EventListActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Click(R.id.searchButton)
    public void searchButtonClicked() {
        Intent i = new Intent(this, SearchActivity_.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
