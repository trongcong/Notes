package com.dev4u.ntc.notes.widget;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.widget
 * Name project: Notes
 * Date: 3/11/2017
 * Time: 00:21
 */

public class RecyclerViewUtils {
    private static RecyclerViewUtils mNewInstance;

    /**
     * single ton
     *
     * @return
     */
    public static RecyclerViewUtils Create() {
        if (mNewInstance == null) {
            mNewInstance = new RecyclerViewUtils();
        }
        return mNewInstance;
    }

    /**
     * set up horizontal for recycler view
     *
     * @param context
     * @param recyclerView
     * @return
     */
    public RecyclerView setUpHorizontal(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

    /**
     * set up vertical for recycler view
     *
     * @param context
     * @param recyclerView
     * @return
     */
    public RecyclerView setUpVertical(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        return recyclerView;
    }

    public RecyclerView setUpGrid(Context context, RecyclerView recyclerView, int column) {
        RecyclerView.LayoutManager mLayout = new GridLayoutManager(context, column);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }
}
