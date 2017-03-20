package com.dev4u.ntc.notes.presenter.detail_note;

import com.dev4u.ntc.notes.model.Notes;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.detail_note
 * Name project: Notes
 * Date: 3/19/2017
 * Time: 01:09
 */

public interface DetailNoteImpInteractor {
    void getNotebyId(int idNote);

    boolean onValidateNote(String contentNote);

    void updateNote(Notes note);
}
