package com.dev4u.ntc.notes.view.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.presenter.BaseView;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.view.base
 * Name project: Notes
 * Date: 3/8/2017
 * Time: 20:35
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected final String TAG = this.getClass().getSimpleName();

    private ProgressDialog mProgressDialog;

    protected abstract int getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState);
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.message_waiting));
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void showToastLong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastShort(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        if (!mProgressDialog.isShowing() && !isFinishing()) {
            mProgressDialog.show();
        }
    }

    public void dissmissProgressDialog() {
        if (!isFinishing()) {
            mProgressDialog.dismiss();
        }
    }
}
