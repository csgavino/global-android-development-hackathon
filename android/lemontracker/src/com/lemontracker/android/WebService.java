package com.lemontracker.android;

import static java.text.MessageFormat.*;

public class WebService {
    private static final String BASE_URL = "10.10.1.196";
    private static final String PORT = "8888";
    private static final String CATEGORY = "http://{0}:{1}/services/categories/{2}/";
    private static final String CATEGORIES = "http://{0}:{1}/services/categories/";
    private static final String EVENTS = "http://{0}:{1}/services/categories/{2}/events/";
    private static final String EVENT = "http://{0}:{1}/services/events/{2}/";
    private static final String ALL_EVENTS = "http://{0}:{1}/services/events/";
    private static final String BANNER = "http://{0}:{1}/{2}/";
    private static final String SEARCH = "http://{0}:{1}/services/search/";
    private static final String RADIUS = "http://{0}:{1}/services/search/location/";
    private static final String LOCATE = "http://{0}:{1}/services/soap/locate/";
    private static final String LOCATIONS = "http://{0}:{1}/services/locations/";

    public static String category(Long id) {
        return format(CATEGORY, BASE_URL, PORT, id);
    }

    public static String categories() {
        return format(CATEGORIES, BASE_URL, PORT);
    }

    public static String events(Long id) {
        return format(EVENTS, BASE_URL, PORT, id);
    }

    public static String event(Long id) {
        return format(EVENT, BASE_URL, PORT, id);
    }

    public static String image(String PATH) {
        return format(BANNER, BASE_URL, PORT, PATH);
    }

    public static String allEvents() {
        return format(ALL_EVENTS, BASE_URL, PORT);
    }

    public static String search() {
        return format(SEARCH, BASE_URL, PORT);
    }

    public static String radius() {
        return format(RADIUS, BASE_URL, PORT);
    }

    public static String locate() {
        return format(LOCATE, BASE_URL, PORT);
    }

    public static String locations() {
        return format(LOCATIONS, BASE_URL, PORT);
    }

}
