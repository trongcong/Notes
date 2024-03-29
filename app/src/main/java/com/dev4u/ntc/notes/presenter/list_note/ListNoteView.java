package com.dev4u.ntc.notes.presenter.list_note;

import com.dev4u.ntc.notes.model.Notes;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.presenter.list_note
 * Name project: Notes
 * Date: 3/10/2017
 * Time: 17:20
 */

public interface ListNoteView {
    void showNoteListView(Notes notes);

    void delteNoteSuccess();

    void delteNoteError();
}
