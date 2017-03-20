package com.dev4u.ntc.notes.presenter.detail_note;

import com.dev4u.ntc.notes.model.Notes;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.detail_note
 * Name project: Notes
 * Date: 3/19/2017
 * Time: 01:08
 */

public interface DetailNoteView {
    void contentEmpty();

    void showNote(Notes note);

    void updateNoteSuccess();

    void updateNoteError();
}
