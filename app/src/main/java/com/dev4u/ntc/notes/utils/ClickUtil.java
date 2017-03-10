package com.dev4u.ntc.notes.utils;

import android.os.Handler;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.utils
 * Name project: Notes
 * Date: 3/8/2017
 * Time: 20:59
 */

public final class ClickUtil {
    private static final int CLICK_LOCK_INTERVAL = 350;

    public static boolean isLocked;

    private static Runnable mClickLockRunnale = new Runnable() {
        @Override
        public void run() {
            isLocked = false;
        }
    };

    private static Handler mHandler = new Handler();

    public synchronized static void lock() {
        isLocked = true;
        mHandler.postDelayed(mClickLockRunnale, CLICK_LOCK_INTERVAL);
    }
}
