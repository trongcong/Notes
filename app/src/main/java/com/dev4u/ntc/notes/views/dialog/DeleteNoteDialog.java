package com.dev4u.ntc.notes.views.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.widget.AlphaTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.view.dialog
 * Name project: Notes
 * Date: 3/15/2017
 * Time: 10:09
 */

public class DeleteNoteDialog extends DialogFragment {
    @BindView(R.id.mTvMessage)
    AppCompatTextView mTvMessage;
    @BindView(R.id.mEdtPhoneNumber)
    AppCompatEditText mEdtPhoneNumber;
    @BindView(R.id.mTvOk)
    AlphaTextView mTvOk;
    @BindView(R.id.mRlOk)
    RelativeLayout mRlOk;
    @BindView(R.id.mTvCancel)
    AlphaTextView mTvCancel;
    @BindView(R.id.mRlCancel)
    RelativeLayout mRlCancel;
    private OnTwoButtonDialogClickListener mListener;

    public DeleteNoteDialog setOnTwoButtonDialogListener(OnTwoButtonDialogClickListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(true);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
        View view = inflater.inflate(R.layout.dialog_delete_note, container);
        ButterKnife.bind(this, view);
        setValidation();
        return view;
    }

    private void setValidation() {
        String ok = mTvOk.getText().toString();
        String cancel = mTvCancel.getText().toString();
        String message = mTvMessage.getText().toString();
        mEdtPhoneNumber.setVisibility(View.GONE);
        if (TextUtils.isEmpty(ok)) {
            mTvOk.setText(R.string.ok);
        }
        if (TextUtils.isEmpty(cancel)) {
            mTvCancel.setText(R.string.cancel);
        }
        if (TextUtils.isEmpty(message)) {
            mTvMessage.setText(R.string.delete_a_note);
        }
    }

    @OnClick({R.id.mRlOk, R.id.mRlCancel})
    public void onClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.mRlOk:
                    mListener.onLeftClick(view);
                    break;
                case R.id.mRlCancel:
                    mListener.onRightClick(view);
                    break;
            }
        }
    }

    public interface OnTwoButtonDialogClickListener {
        void onLeftClick(View view);

        void onRightClick(View view);
    }
}
