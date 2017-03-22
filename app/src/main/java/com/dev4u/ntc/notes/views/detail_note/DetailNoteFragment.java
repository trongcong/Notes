package com.dev4u.ntc.notes.views.detail_note;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.model.Notes;
import com.dev4u.ntc.notes.presenter.detail_note.DetailNoteLogicPresenter;
import com.dev4u.ntc.notes.presenter.detail_note.DetailNoteView;
import com.dev4u.ntc.notes.views.base.BaseFragment;
import com.dev4u.ntc.notes.widget.AlphaTextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dev4u.ntc.notes.R.id.mRlImgLeftCancel;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.views.detail_note
 * Name project: Notes
 * Date: 3/19/2017
 * Time: 01:07
 */

public class DetailNoteFragment extends BaseFragment implements DetailNoteView, TextWatcher {
    @BindView(R.id.mTvDone)
    AlphaTextView mTvDone;
    @BindView(R.id.mEdContentNoteEdit)
    AppCompatEditText mEdContentNoteEdit;
    @BindView(R.id.mTvTitleActionbar)
    AppCompatTextView mTvTitleActionbar;
    @BindView(R.id.mRlImgLeft)
    RelativeLayout mRlImgLeft;
    @BindView(R.id.mImgLeft)
    AppCompatImageView mImgLeft;
    @BindView(mRlImgLeftCancel)
    RelativeLayout mRlImgLeftCancle;
    @BindView(R.id.mTvDetailNote)
    AppCompatTextView mTvDetailNote;
    private int click = 0;
    private String mContentNote;

    private DetailNoteLogicPresenter detailNoteLogicPresenter;

    @Override
    protected String getTitle() {
        return getString(R.string.detail_note);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_note, container, false);
        ButterKnife.bind(this, view);
        detailNoteLogicPresenter = new DetailNoteLogicPresenter(getContext(), this);
        detailNoteLogicPresenter.getNotebyId(getArguments().getInt("idNotes"));

        return view;
    }

    @OnClick({R.id.mTvDone, R.id.mRlImgLeft, R.id.mRlImgLeftCancel, R.id.mTvDetailNote})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mTvDone:
                long timestamp = Calendar.getInstance().getTimeInMillis();
                Notes notes = new Notes(getArguments().getInt("idNotes"), mEdContentNoteEdit.getText().toString(), timestamp + "");
                detailNoteLogicPresenter.updateNote(notes);
                Log.e("updateClick", "Update click");
                break;
            case R.id.mRlImgLeft:
                getActivity().onBackPressed();
                break;
            case R.id.mRlImgLeftCancel:
                mRlImgLeftCancle.setVisibility(View.GONE);
                mRlImgLeft.setVisibility(View.VISIBLE);
                mTvDetailNote.setVisibility(View.VISIBLE);
                mEdContentNoteEdit.setVisibility(View.GONE);
                mTvDone.setText("");
                mImgLeft.setBackgroundResource(R.drawable.ic_menu_back);
                mTvTitleActionbar.setText(getString(R.string.detail_note));
                showToastShort("Cancel");
                break;
            case R.id.mTvDetailNote:
                click++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        click = 0;
                    }
                };

                if (click == 1) {
                    //Single click
                    showToastShort("Click again to edit the note. ");
                    handler.postDelayed(r, 250);
                } else if (click == 2) {
                    //Double click
                    mImgLeft.setBackgroundResource(R.drawable.ic_menu_close);
                    mTvDetailNote.setVisibility(View.GONE);
                    mRlImgLeftCancle.setVisibility(View.VISIBLE);
                    mRlImgLeft.setVisibility(View.GONE);
                    mEdContentNoteEdit.setVisibility(View.VISIBLE);
                    mTvTitleActionbar.setText(getString(R.string.edit_note));

                    mEdContentNoteEdit.setText(mContentNote);
                    mEdContentNoteEdit.setFocusableInTouchMode(true);

                    mEdContentNoteEdit.addTextChangedListener(this);
                    click = 0;
                }
                break;
        }
    }

    @Override
    public void contentEmpty() {
        showToastLong(getString(R.string.enter_a_note));
    }

    @Override
    public void showNote(Notes note) {
        mContentNote = note.getContent();
        mTvDetailNote.setText(mContentNote);
    }

    @Override
    public void updateNoteSuccess() {
        showToastShort("Update a Note success!");
        getFragmentManager().popBackStack();
    }

    @Override
    public void updateNoteError() {
        Log.e("updateNote", "Update a Note error!");
        getFragmentManager().popBackStack();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            mTvDone.setText("Update");
        } else {
            mTvDone.setText("");
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            mTvDone.setText("Update");
        } else {
            mTvDone.setText("");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
