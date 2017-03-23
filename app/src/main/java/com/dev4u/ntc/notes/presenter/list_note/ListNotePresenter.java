package com.dev4u.ntc.notes.presenter.list_note;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;

import com.dev4u.ntc.notes.database.SQLCallback;
import com.dev4u.ntc.notes.database.SQLDatabaseManager;
import com.dev4u.ntc.notes.model.Notes;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.list_note
 * Name project: Notes
 * Date: 3/10/2017
 * Time: 17:23
 */

public class ListNotePresenter implements ListNoteImpInteractor, SQLCallback {
    ListNoteView listNoteView;
    Context context;
    SQLDatabaseManager mSqlite;

    public ListNotePresenter(Context context, ListNoteView listNoteView) {
        this.context = context;
        this.listNoteView = listNoteView;
        mSqlite = new SQLDatabaseManager(context);
        mSqlite.getNote(this);
    }

    private int getRandomMaterialColor() {
        int returnColor = Color.GRAY;
        int arrayId = context.getResources().getIdentifier("notes_color", "array", context.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = context.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    @Override
    public void loadNotes(Notes notes) {
        if (notes != null) {
            notes.setColor(getRandomMaterialColor());
            listNoteView.showNoteListView(notes);
        }
    }

    @Override
    public void deleteNote(Notes notes) {
        if (mSqlite.deleteNotes(notes)) {
            listNoteView.delteNoteSuccess();
        } else {
            listNoteView.delteNoteError();
        }
    }

    @Override
    public void callBackNoteSuccess(Notes notes) {
        if (notes != null) {
            loadNotes(notes);
        } else {
            Log.e("callBack Note", "Load note Error");
        }
    }
}
