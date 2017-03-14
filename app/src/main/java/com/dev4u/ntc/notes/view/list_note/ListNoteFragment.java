package com.dev4u.ntc.notes.view.list_note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.database.SQLDatabaseManager;
import com.dev4u.ntc.notes.model.Notes;
import com.dev4u.ntc.notes.presenter.list_note.ListNotePresenter;
import com.dev4u.ntc.notes.presenter.list_note.ListNoteView;
import com.dev4u.ntc.notes.view.add_note.AddNoteFragment;
import com.dev4u.ntc.notes.view.base.BaseFragment;
import com.dev4u.ntc.notes.widget.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.view.list_note
 * Name project: Notes
 * Date: 3/10/2017
 * Time: 17:17
 */

public class ListNoteFragment extends BaseFragment implements ListNoteView {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mFabAdd)
    FloatingActionButton mFabAdd;
    @BindView(R.id.mImgLeft)
    AppCompatImageView mImgLeft;
    private ListNotePresenter mListNotePresenter;
    private NoteAdapter mNoteAdapter;

    private List<Notes> mListNotes = new ArrayList<>();

    @Override
    protected String getTitle() {
        return getString(R.string.list_notes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_note, container, false);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mListNotePresenter = new ListNotePresenter(this);
        initDataNotes();
        return view;
    }

    private void initDataNotes() {
        mListNotes = SQLDatabaseManager.getInstance(getContext()).getListNotes();
        mImgLeft.setVisibility(View.GONE);
        initRycyclerView();
        eventClick();
    }

    private void initRycyclerView() {
        RecyclerViewUtils.Create().setUpVertical(getContext(), mRecyclerView);
        mNoteAdapter = new NoteAdapter(getContext(), mListNotes);
        mRecyclerView.setAdapter(mNoteAdapter);
    }

    private void eventClick() {
        mNoteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                showToastShort("You click item " + position);
            }

            @Override
            public void onItemLongClick(View itemView, int position) {
                showToastShort("You delete item " + position);
                SQLDatabaseManager.getInstance(getContext()).deleteNotes(new Notes(mListNotes.get(position).getId(), "", ""));
                mListNotes = SQLDatabaseManager.getInstance(getContext()).getListNotes();
                mNoteAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.mFabAdd)
    public void onClick() {
        Fragment fragment = new AddNoteFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(fragment.getClass().getName());
        ft.replace(R.id.mFragmentLayout, fragment);
        ft.commit();
    }

}
