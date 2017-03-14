package com.dev4u.ntc.notes;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes
 * Name project: Notes
 * Date: 3/14/2017
 * Time: 17:52
 */

public class Constant {
    private static final Constant ourInstance = null;

    private Constant() {
    }

    public static Constant getInstance() {
        if (ourInstance != null) {
            return ourInstance;
        }
        return new Constant();
    }

    public String encodeNote(String in) {
        String endcode = "";
        try {
            // Sending side
            byte[] content = in.getBytes("UTF-8");
            endcode = Base64.encodeToString(content, Base64.DEFAULT);
            content = endcode.getBytes("UTF-16");
            endcode = Base64.encodeToString(content, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return endcode;
    }

    public String decodeNote(String in) {
        String decode = "";
        try {
            // Receiving side
            byte[] content = Base64.decode(in, Base64.DEFAULT);
            decode = new String(content, "UTF-16");
            content = Base64.decode(decode, Base64.DEFAULT);
            decode = new String(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decode;
    }

    public String formatTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss - EEE, dd MMM yyyy ");
        Date date = new Date(timestamp);
        return sdf.format(date);
    }
}
