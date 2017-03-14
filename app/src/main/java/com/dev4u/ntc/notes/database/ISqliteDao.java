package com.dev4u.ntc.notes.database;

import com.dev4u.ntc.notes.model.Notes;

import java.util.List;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.database
 * Name project: Notes
 * Date: 3/13/2017
 * Time: 18:40
 */

public interface ISqliteDao {
    boolean hasNotes(Notes notes);

    boolean insertNotes(Notes notes);

    boolean deleteNotes(Notes notes);

    List<Notes> getListNotes();
}
