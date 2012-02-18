package com.lemontracker.android.activity;

import com.lemontracker.android.base.BaseListActivity_;

import static com.lemontracker.android.WebService.*;

public class ConcertsActivity extends BaseListActivity_ {

    @Override
    protected String service() {
        return events(1L);
    }
}
