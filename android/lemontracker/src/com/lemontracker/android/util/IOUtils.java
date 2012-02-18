package com.lemontracker.android.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import static org.apache.commons.io.IOUtils.*;

public class IOUtils {
    public static Bitmap loadImageFromURL(URL url) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        copy(bis, bos);
        bos.flush();
        byte[] data = baos.toByteArray();
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

}
