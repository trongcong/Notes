package com.dev4u.ntc.notes.presenter.list_note;

import com.dev4u.ntc.notes.model.Notes;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.list_note
 * Name project: Notes
 * Date: 3/15/2017
 * Time: 11:22
 */

public interface ListNoteImpInteractor {
    void loadNotes(Notes notes);

    void deleteNote(Notes notes);
}
