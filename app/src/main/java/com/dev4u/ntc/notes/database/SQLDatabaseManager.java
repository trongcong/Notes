package com.dev4u.ntc.notes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dev4u.ntc.notes.model.Notes;

import java.util.ArrayList;
import java.util.List;


/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.database
 * Name project: Notes
 * Date: 3/13/2017
 * Time: 18:28
 */

public class SQLDatabaseManager extends SQLiteOpenHelper implements ISqliteSchema, ISqliteDao {
    public static SQLDatabaseManager sqlInstance = null;

    public SQLiteDatabase mDb;

    public SQLDatabaseManager(Context context) {
        super(context, DB_NAME, null, 1);
        mDb = getWritableDatabase();
    }

    public static synchronized SQLDatabaseManager getInstance(Context context) {
        sqlInstance = new SQLDatabaseManager(context);
        return sqlInstance;
    }

    public void close() {
        this.mDb.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public boolean hasNotes(Notes notes) {
        return false;
    }

    @Override
    public boolean insertNotes(Notes notes) {
        try {
            int result = (int) insert(TBL_NOTES, notes.getValues());
            if (result != -1) return true;
            Log.e("insertNotes", "Ok " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    @Override
    public boolean deleteNotes(Notes notes) {
        try {
            int result = delete(TBL_NOTES, COL_ID + "=?", new String[]{String.valueOf(notes.getId())});
            if (result != -1) return true;
            Log.e("deleteNotes", "Ok " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    @Override
    public List<Notes> getListNotes() {
        ArrayList<Notes> listNotes = new ArrayList<>();
        try {
            String sql = "select * from " + TBL_NOTES;
            Cursor cursor = rawQuery(sql, null);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Notes notes = new Notes(cursor);
                    listNotes.add(notes);
                    cursor.moveToNext();
                }
                Log.e("arr", listNotes.toString());
                cursor.close();
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNotes;
    }

    private long insert(String tableName, ContentValues values) {
        return mDb.insert(tableName, null, values);
    }

    private int delete(String tableName, String selection,
                       String[] selectionArgs) {
        return mDb.delete(tableName, selection, selectionArgs);
    }

    private int update(String tableName, ContentValues values,
                       String selection, String[] selectionArgs) {
        return mDb.update(tableName, values, selection,
                selectionArgs);
    }

    private Cursor rawQuery(String sql, String[] selectionArgs) {
        return mDb.rawQuery(sql, selectionArgs);
    }
}
