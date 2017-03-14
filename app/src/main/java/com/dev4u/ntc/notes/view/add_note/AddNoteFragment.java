package com.dev4u.ntc.notes.view.add_note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.presenter.add_note.AddNoteLogicPresenter;
import com.dev4u.ntc.notes.presenter.add_note.AddNoteView;
import com.dev4u.ntc.notes.view.base.BaseFragment;
import com.dev4u.ntc.notes.widget.AlphaTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.view
 * Name project: Notes
 * Date: 3/8/2017
 * Time: 22:34
 */

public class AddNoteFragment extends BaseFragment implements AddNoteView {

    @BindView(R.id.mTvDone)
    AlphaTextView mTvDone;
    @BindView(R.id.mEdContentNote)
    AppCompatEditText mEdContentNote;
    private AddNoteLogicPresenter mAddNotePresenter;

    @Override
    protected String getTitle() {
        return getString(R.string.add_a_note);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mAddNotePresenter = new AddNoteLogicPresenter(getContext(), this);

        mEdContentNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    // Set text add patient done
                    mTvDone.setText(R.string.done);
                } else {
                    // Set text add patient done
                    mTvDone.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    @Override
    public void contentEmpty() {
        showToastLong(getString(R.string.enter_a_note));
    }

    @Override
    public void addNoteSuccess() {
        showToastLong(getString(R.string.insert_note_success));
    }

    @Override
    public void addNoteError() {
        showToastLong(getString(R.string.insert_note_error));
    }

    @OnClick(R.id.mTvDone)
    public void onClick() {
        mAddNotePresenter.addNote(mEdContentNote.getText().toString());
        getFragmentManager().popBackStack();

    }

}
