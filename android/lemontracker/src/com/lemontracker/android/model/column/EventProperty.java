package com.lemontracker.android.model.column;

public enum EventProperty {
    HEADER("header"),
    BLURB("blurb"),
    DATE("date");

    private String column;

    private EventProperty(final String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return column;
    }

    public static String[] toArray() {
        EventProperty[] props = EventProperty.values();
        int count = props.length;
        String[] propsArray = new String[count];
        for (int i = 0; i < count; i++) {
            propsArray[i] = props[i].toString();
        }
        return propsArray;
    }
}
