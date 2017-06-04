package com.dev4u.ntc.notes.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.views.base.BaseActivity;
import com.dev4u.ntc.notes.views.dialog.AddPhoneNumberDialog;
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
        showDialogAddPhoneNumber();
        // Write a message to the database
//        mDatabase = FirebaseDatabase.getInstance();
//        myRef = mDatabase.getReference("m");
//
//        myRef.setValue("Hello");
        initFragment();
    }

    private void initFragment() {
        Fragment fragment = new ListNoteFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.addToBackStack(fragment.getClass().getName());
        ft.replace(R.id.mFragmentLayout, fragment);
        ft.commit();
    }

    private void showDialogAddPhoneNumber() {
        final AddPhoneNumberDialog dialog = new AddPhoneNumberDialog();
        Log.e(TAG, "Create Dialog Add phone number!");
        dialog.setOnTwoButtonDialogListener(new AddPhoneNumberDialog.OnTwoButtonDialogClickListener() {
            @Override
            public void onLeftClick(View view) {
                // TODO : send phone number to server.
                if (dialog.checkEmpty()) {
                    showToastShort("Please enter your phone number!");
                } else {
                    showToastLong(dialog.getNumber());
                }
            }

            @Override
            public void onRightClick(View view) {
                dialog.dismiss();
            }
        }).show(getSupportFragmentManager(), AddPhoneNumberDialog.class.getName());
    }
}
