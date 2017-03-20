package com.dev4u.ntc.notes.views.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.view.base
 * Name project: Notes
 * Date: 3/10/2017
 * Time: 18:54
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected final String TAG = this.getClass().getSimpleName();

    private final Context mContext;

    protected BaseAdapter(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    protected Resources getResources() {
        return mContext.getResources();
    }

    protected String getString(int resId) {
        return mContext.getString(resId);
    }

    protected String getString(int resId, Object... objects) {
        return mContext.getString(resId, objects);
    }
}