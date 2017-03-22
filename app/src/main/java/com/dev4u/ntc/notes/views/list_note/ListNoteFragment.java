package com.dev4u.ntc.notes.views.list_note;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.model.Notes;
import com.dev4u.ntc.notes.presenter.list_note.ListNotePresenter;
import com.dev4u.ntc.notes.presenter.list_note.ListNoteView;
import com.dev4u.ntc.notes.views.add_note.AddNoteFragment;
import com.dev4u.ntc.notes.views.base.BaseFragment;
import com.dev4u.ntc.notes.views.detail_note.DetailNoteFragment;
import com.dev4u.ntc.notes.views.dialog.DeleteNoteDialog;
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

public class ListNoteFragment extends BaseFragment implements ListNoteView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mFabAdd)
    FloatingActionButton mFabAdd;
    @BindView(R.id.mImgLeft)
    AppCompatImageView mImgLeft;
    @BindView(R.id.mSRl)
    SwipeRefreshLayout mSRl;
    private ListNotePresenter mListNotePresenter;
    private NoteAdapter mNoteAdapter;
    private List<Notes> mListNotes;

    @Override
    protected String getTitle() {
        return getString(R.string.list_notes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_note, container, false);
        ButterKnife.bind(this, view);
        mImgLeft.setVisibility(View.GONE);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mSRl.setOnRefreshListener(this);
        mSRl.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSRl.post(new Runnable() {
            @Override
            public void run() {
                mNoteAdapter.notifyDataSetChanged();
            }
        });

        initRycyclerView();
        eventClick();
        return view;
    }

    private void initRycyclerView() {
        RecyclerViewUtils.Create().setUpVertical(getContext(), mRecyclerView);
        mListNotes = new ArrayList<>();
        mNoteAdapter = new NoteAdapter(getContext(), mListNotes);
        mRecyclerView.setAdapter(mNoteAdapter);
        mListNotePresenter = new ListNotePresenter(getContext(), this);
    }

    private void eventClick() {
        mNoteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Fragment fragment = new DetailNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idNotes", mListNotes.get(position).getId());

                if (bundle != null) fragment.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.addToBackStack(fragment.getClass().getName());
                ft.replace(R.id.mFragmentLayout, fragment);
                ft.commit();
            }

            @Override
            public void onItemLongClick(View itemView, int position) {
                showDialogDeleteNote(position);
            }
        });
    }

    private void showDialogDeleteNote(final int position) {
        final DeleteNoteDialog dialog = new DeleteNoteDialog();
        Log.e(TAG, "Create Dialog Add phone number!");
        dialog.setOnTwoButtonDialogListener(new DeleteNoteDialog.OnTwoButtonDialogClickListener() {
            @Override
            public void onLeftClick(View view) {
                mListNotePresenter.deleteNote(new Notes(mListNotes.get(position).getId(), "a", "a"));
                removeAt(position);
                dialog.dismiss();
            }

            @Override
            public void onRightClick(View view) {
                dialog.dismiss();
            }
        }).show(getChildFragmentManager(), DeleteNoteDialog.class.getName());
    }

    private void removeAt(int position) {
        mListNotes.remove(position);
        mNoteAdapter.notifyItemRemoved(position);
        mNoteAdapter.notifyItemRangeChanged(position, mListNotes.size());
    }

    @OnClick(R.id.mFabAdd)
    public void onClick() {
        Fragment fragment = new AddNoteFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(fragment.getClass().getName());
        ft.replace(R.id.mFragmentLayout, fragment);
        ft.commit();
    }

    @Override
    public void showNoteListView(Notes notes) {
        mListNotes.add(notes);
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void delteNoteSuccess() {
        showToastLong("Delete a Note success!");
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void delteNoteError() {
        Log.e("delete Note", "Delete a Note error!");
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mSRl.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mNoteAdapter.notifyDataSetChanged();
                mSRl.setRefreshing(false);
            }
        }, 1000);
    }
}
