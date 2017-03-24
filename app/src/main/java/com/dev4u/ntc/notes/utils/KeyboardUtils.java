package com.dev4u.ntc.notes.utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.utils
 * Name project: Notes
 * Date: 3/24/2017
 * Time: 13:02
 */

public final class KeyboardUtils {
    /**
     * Hide keyboard on activity
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Show keyboard on view
     *
     * @param v
     */
    public static void showKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)
                v.getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (v.requestFocus()) {
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * Hide keyboard on view edit
     *
     * @param v
     */
    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)
                v.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
