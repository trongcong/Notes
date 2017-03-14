package com.dev4u.ntc.notes.presenter.add_note;

import android.content.Context;

import com.dev4u.ntc.notes.database.SQLDatabaseManager;
import com.dev4u.ntc.notes.model.Notes;

import java.util.Calendar;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.add
 * Name project: Notes
 * Date: 3/8/2017
 * Time: 22:41
 */

public class AddNoteLogicPresenter implements AddNoteImpInteractor {
    AddNoteView addNoteView;
    SQLDatabaseManager mSqlite;
    Context mContext;

    public AddNoteLogicPresenter(Context context, AddNoteView addNoteView) {
        this.mContext = context;
        this.addNoteView = addNoteView;
        mSqlite = new SQLDatabaseManager(context);
    }

    @Override
    public boolean onValidateNote(String contentNote) {
        return !contentNote.isEmpty();
    }

    @Override
    public void addNote(String contentNote) {
        if (onValidateNote(contentNote)) {
            Notes notes = null;
            long timestamp = Calendar.getInstance().getTimeInMillis();
            notes = new Notes(0, (contentNote), (timestamp + ""));

            if (mSqlite.insertNotes(notes)) {
                addNoteView.addNoteSuccess();
            } else {
                addNoteView.addNoteError();
            }
        } else {
            addNoteView.contentEmpty();
        }
    }
}
