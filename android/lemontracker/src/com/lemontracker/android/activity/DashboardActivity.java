package com.lemontracker.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.lemontracker.android.R;
import com.lemontracker.android.model.Category;
import org.springframework.web.client.RestClientException;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lemontracker.android.WebService.*;
import static com.lemontracker.android.util.IOUtils.*;
import static com.teamcodeflux.android.RestTemplateFactory.*;
import static java.util.Arrays.*;

@EActivity
public class DashboardActivity extends Activity {
    public static String TAG = DashboardActivity.class.getSimpleName();
    @ViewById
    ImageView today;
    @ViewById
    ImageView restaurants;
    @ViewById
    ImageView sales;
    @ViewById
    ImageView concerts;
    @ViewById
    ImageView parties;
    @ViewById
    ImageView others;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        fetchCategories();
    }

    @Background
    public void fetchCategories() {
        try {
            String URL = categories();
            Category[] result = getRestTemplate().getForObject(URL, Category[].class);
            List<Category> categories = asList(result);
            for (Category category : categories) {
                fetchBitmap(category.getName(), category.getImageUrl());
            }
        } catch (RestClientException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Background
    public void fetchBitmap(String name, String imageURL) {
        try {
            String formattedURL = image(imageURL);
            Bitmap bitmap = loadImageFromURL(new URL(formattedURL));
            renderBitmap(name, bitmap);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @UiThread
    public void renderBitmap(String name, Bitmap bitmap) {
        int id = Container.getContainer(name);
        ImageView view = (ImageView) findViewById(id);
        view.setImageBitmap(bitmap);
    }

    private enum Container {
        TODAY("Today", R.id.today),
        CONCERTS("Concerts", R.id.concerts),
        RESTAURANTS("Restaurants", R.id.restaurants),
        SALES("Sales", R.id.sales),
        PARTIES("Parties", R.id.parties),
        OTHERS("Others", R.id.others);

        private String name;
        private int value;
        private static Map<String, Integer> keyedGroup;

        private Container(String name, int value) {
            this.name = name;
            this.value = value;
        }

        static {
            keyedGroup = new HashMap<String, Integer>();
            for (Container container : values()) {
                keyedGroup.put(container.name, container.value);
            }
        }

        public static int getContainer(String name) {
            return keyedGroup.containsKey(name) ?
                    keyedGroup.get(name) : R.id.today;
        }

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

    @Click(R.id.restaurants)
    public void restaurantsCellClicked() {
        Intent i = new Intent(this, RestaurantsActivity_.class);
        startActivity(i);
    }

    @Click(R.id.sales)
    public void salesCellClicked() {
        Intent i = new Intent(this, SalesActivity_.class);
        startActivity(i);
    }

    @Click(R.id.concerts)
    public void concertsCellClicked() {
        Intent i = new Intent(this, ConcertsActivity_.class);
        startActivity(i);
    }

    @Click(R.id.parties)
    public void partiesCellClicked() {
        Intent i = new Intent(this, PartiesActivity_.class);
        startActivity(i);
    }

    @Click(R.id.others)
    public void othersCellClicked() {
        Intent i = new Intent(this, OthersActivity_.class);
        startActivity(i);
    }

}
