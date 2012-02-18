package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Event;
import com.lemontracker.android.util.EventArrayAdapter;
import com.teamcodeflux.android.Result;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

import static com.lemontracker.android.WebService.*;
import static com.teamcodeflux.android.RestTemplateFactory.*;
import static java.util.Arrays.*;

@EActivity(R.layout.search_layout)
public class SearchActivity extends Activity {
    public static final String TAG = SearchActivity.class.getSimpleName();
    private static final String BLANK = "";

    @ViewById(R.id.__list)
    ListView list;

    @ViewById
    EditText queryEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.submitButton)
    public void onSubmitClicked() {
        if (!queryEditText.getText().toString().trim().equals(BLANK)) {
            hideKeyboard();
            fetchEvents(queryEditText.getText().toString().trim());
            queryEditText.setText(BLANK);
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(queryEditText.getWindowToken(), 0);
    }

    @Background
    public void fetchEvents(String query) {
        try {

            MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
            map.add("searchstring", query);

            String URL = search();
            Event[] result = getRestTemplate().postForObject(URL, map, Event[].class);

            processResult(new Result<ArrayList<Event>>(new ArrayList<Event>(asList(result))));
        } catch (RestClientException e) {
            processResult(new Result(e));
        }
    }

    @UiThread
    public void processResult(Result result) {
        if (result.hasErrors()) {
            Log.e(TAG, result.getResult().toString());
        } else {
            List __events = (ArrayList<Event>) result.getResult();
            parseResults(__events);
        }
    }

    private void parseResults(List events) {
        if (!events.isEmpty()) {
            ArrayAdapter adapter = new EventArrayAdapter(this, R.layout.list_cell_layout, events);
            list.setAdapter(adapter);
        } else {
            Log.e(TAG, "Toast!");
            Toast toast = Toast.makeText(SearchActivity.this, "No Results", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

}
