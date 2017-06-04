package com.dev4u.ntc.notes.views.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev4u.ntc.notes.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.views.dialog
 * Name project: Notes
 * Date: 4/23/2017
 * Time: 11:21
 */

public class AddPhoneNumberDialog extends DialogFragment {

    @BindView(R.id.mEdtPhoneNumber)
    EditText mEdtPhoneNumber;
    @BindView(R.id.mTvOk)
    TextView mTvOk;
    @BindView(R.id.mTvCancel)
    TextView mTvCancel;
    @BindView(R.id.mTvMessage)
    TextView mTvMessage;
    @BindView(R.id.mRlOk)
    RelativeLayout mRlOk;
    @BindView(R.id.mRlCancel)
    RelativeLayout mRlCancel;

    private OnTwoButtonDialogClickListener mListener;

    public AddPhoneNumberDialog setOnTwoButtonDialogListener(OnTwoButtonDialogClickListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
        View view = inflater.inflate(R.layout.dialog_add_phone_number, container);
        ButterKnife.bind(this, view);
        setValidation();
        return view;
    }

    /**
     * Set value textviews
     */
    private void setValidation() {
        String ok = mTvOk.getText().toString();
        String cancel = mTvCancel.getText().toString();
        String message = mTvMessage.getText().toString();
        if (TextUtils.isEmpty(ok)) {
            mTvOk.setText(R.string.ok);
        }
        if (TextUtils.isEmpty(cancel)) {
            mTvCancel.setText(R.string.cancel);
        }
        if (TextUtils.isEmpty(message)) {
            mTvMessage.setText(R.string.add_phone_number);
        }
    }

    /**
     * Check empty edittext
     *
     * @return
     */
    public boolean checkEmpty() {
        String phomeNumber = mEdtPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(phomeNumber)) {
            mEdtPhoneNumber.requestFocus();
            return true;
        }
        return false;
    }

    @OnClick({R.id.mRlOk, R.id.mRlCancel})
    void onClick(View view) {
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

    public String getNumber() {
        return mEdtPhoneNumber.getText().toString();
    }

    public interface OnTwoButtonDialogClickListener {
        void onLeftClick(View view);

        void onRightClick(View view);
    }
}
