package com.dev4u.ntc.notes.presenter.detail_note;

import android.content.Context;

import com.dev4u.ntc.notes.database.SQLDatabaseManager;
import com.dev4u.ntc.notes.model.Notes;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.detail_note
 * Name project: Notes
 * Date: 3/19/2017
 * Time: 01:09
 */

public class DetailNoteLogicPresenter implements DetailNoteImpInteractor {
    DetailNoteView mDetailNoteView;
    SQLDatabaseManager mSqlite;
    Context mContext;

    public DetailNoteLogicPresenter(Context context, DetailNoteView detailNoteView) {
        this.mContext = context;
        this.mDetailNoteView = detailNoteView;
        mSqlite = new SQLDatabaseManager(context);
    }

    @Override
    public void getNotebyId(int idNote) {
        mDetailNoteView.showNote(mSqlite.getNote(idNote));
    }

    @Override
    public boolean onValidateNote(String contentNote) {
        return !contentNote.trim().isEmpty();
    }

    @Override
    public void updateNote(Notes note) {
        if (onValidateNote(note.getContent())) {
            if (mSqlite.updateNote(note)) {
                mDetailNoteView.updateNoteSuccess();
            } else {
                mDetailNoteView.updateNoteError();
            }
        } else {
            mDetailNoteView.contentEmpty();
        }
    }
}
