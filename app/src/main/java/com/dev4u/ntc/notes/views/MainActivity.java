package com.dev4u.ntc.notes.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.views.base.BaseActivity;
import com.dev4u.ntc.notes.views.list_note.ListNoteFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        // Write a message to the database
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("m");

        myRef.setValue("Hello");
        initFragment();
    }

    private void initFragment() {
        Fragment fragment = new ListNoteFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.addToBackStack(fragment.getClass().getName());
        ft.replace(R.id.mFragmentLayout, fragment);
        ft.commit();
    }
}
