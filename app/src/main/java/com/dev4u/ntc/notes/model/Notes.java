package com.dev4u.ntc.notes.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Base64;

import com.dev4u.ntc.notes.database.ISqliteSchema;

import java.io.UnsupportedEncodingException;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.model
 * Name project: Notes
 * Date: 3/10/2017
 * Time: 18:55
 */

public class Notes implements ISqliteSchema {
    private int id;
    private String content;
    private String timestamps;
    private int color=-1;

    public Notes() {
    }

    public Notes(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.content = decodeNotes(cursor.getString(1));
        this.timestamps = decodeNotes(cursor.getString(2));
    }

    public Notes(int id, String content, String timestamps) {
        this.id = id;
        this.content = (content);
        this.timestamps = (timestamps);
    }

    public static final String encodeNotes(String in) {
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

    public static final String decodeNotes(String in) {
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return (content);
    }

    public void setContent(String content) {
        this.content = (content);
    }

    public String getTimestamps() {
        return (timestamps);
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = (timestamps);
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, encodeNotes(this.getContent()));
        values.put(COL_TIME, encodeNotes(this.getTimestamps()));
        return values;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", timestamps='" + timestamps + '\'' +
                '}';
    }
}
