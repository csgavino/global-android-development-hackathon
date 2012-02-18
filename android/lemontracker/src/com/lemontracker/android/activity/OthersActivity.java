package com.lemontracker.android.activity;

import com.googlecode.androidannotations.annotations.EActivity;
import com.lemontracker.android.base.BaseListActivity_;

import static com.lemontracker.android.WebService.*;

@EActivity
public class OthersActivity extends BaseListActivity_ {

    @Override
    protected String service() {
        return events(5L);
    }

    @Override
    protected String header() {
        return "Others";
    }
}
