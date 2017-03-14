package com.dev4u.ntc.notes.presenter.add_note;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.add
 * Name project: Notes
 * Date: 3/8/2017
 * Time: 22:42
 */

public interface AddNoteImpInteractor {
    boolean onValidateNote(String contentNote);

    void addNote(String contentNote);
}
