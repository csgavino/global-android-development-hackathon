package com.lemontracker.android;

import static java.text.MessageFormat.*;

public class WebService {
    private static final String BASE_URL = "10.10.1.196";
    private static final String PORT = "3000";
    private static final String CATEGORIES = "http://{0}:{1}/services/categories/";
    private static final String EVENTS = "http://{0}:{1}/services/categories/{2}/events/";
    private static final String EVENT = "http://{0}:{1}/services/categories/events/{2}/";

    public static String categories() {
        return format(CATEGORIES, BASE_URL, PORT);
    }

    public static String events(Long id) {
        return format(EVENTS, BASE_URL, PORT, id);
    }

    public static String event(Long id) {
        return format(EVENT, BASE_URL, PORT, id);
    }

}
