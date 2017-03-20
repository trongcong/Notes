package com.dev4u.ntc.notes.database;

import com.dev4u.ntc.notes.model.Notes;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.database
 * Name project: Notes
 * Date: 3/16/2017
 * Time: 13:35
 */

public interface SQLCallback {
    void callBackNoteSuccess(Notes notes);
}
