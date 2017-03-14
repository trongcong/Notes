package com.dev4u.ntc.notes.database;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.database
 * Name project: Notes
 * Date: 3/13/2017
 * Time: 18:44
 */

public interface ISqliteSchema {
    String DB_NAME = "DB_NOTES.sqlite";
    String TBL_NOTES = "NOTES";
    String COL_ID = "idNotes";
    String COL_CONTENT = "contentNotes";
    String COL_TIME = "timeNotes";
    String TABLE_NOTES = "CREATE TABLE  " + TBL_NOTES
            + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CONTENT
            + " TEXT NOT NULL, " + COL_TIME + " BIGINT NOT NULL)";
}