package com.dev4u.ntc.notes.views.list_note;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

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

public class ListNoteFragment extends BaseFragment implements ListNoteView, ActionMode.Callback,
        SwipeRefreshLayout.OnRefreshListener, NoteAdapter.OnItemClickListener, CallBackDelete {

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
    private ActionMode mActionMode;
    private CallBackDelete callBackDelete;
    private DeleteNoteDialog deleteNoteDialog;

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
        mSRl.setColorScheme(new int[]{android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light});
        mSRl.post(new Runnable() {
            @Override
            public void run() {
                mNoteAdapter.notifyDataSetChanged();
            }
        });

        initRycyclerView();
        return view;
    }

    private void initRycyclerView() {
        RecyclerViewUtils.Create().setUpVertical(getContext(), mRecyclerView);
        mListNotes = new ArrayList<>();
        mNoteAdapter = new NoteAdapter(getContext(), mListNotes, this);
        deleteNoteDialog = new DeleteNoteDialog();
        mRecyclerView.setAdapter(mNoteAdapter);
        mListNotePresenter = new ListNotePresenter(getContext(), this);
    }

    private void showDialogDeleteNote() {
        Log.e(TAG, "Create Dialog Add phone number!");
        deleteNoteDialog.setOnTwoButtonDialogListener(new DeleteNoteDialog.OnTwoButtonDialogClickListener() {
            @Override
            public void onLeftClick(View view) {
                deleteItems();
//                removeAt(position);
                deleteNoteDialog.dismiss();
            }

            @Override
            public void onRightClick(View view) {
                deleteNoteDialog.dismiss();
            }
        }).show(getChildFragmentManager(), DeleteNoteDialog.class.getName());
    }

    private void removeAt(int position) {
        mListNotes.remove(position);
        mNoteAdapter.notifyItemRemoved(position);
        mNoteAdapter.notifyItemRangeChanged(position, mListNotes.size());
    }

    private void enableActionMode(int position) {
        if (mActionMode == null) {
            mActionMode = ((AppCompatActivity) getContext()).startSupportActionMode(this);
        }
        listItemSelect(position);
    }

    private void listItemSelect(int position) {
        mNoteAdapter.toggleSelection(position);
        int count = mNoteAdapter.getSelectedCount();
        if (count == 0) {
            mActionMode.finish();
        } else {
            mActionMode.setTitle(String.valueOf(count) + " item selected");
            mActionMode.invalidate();
        }
    }

    public void deleteItems() {
        SparseBooleanArray selected = mNoteAdapter.getSelectedIds();//Get selected ids
        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                //key is position of item
                callBackDelete.onDeleteItem(new Notes(mListNotes.get(selected.keyAt(i)).getId(), "a", "a"));
                Log.e("po", selected.keyAt(i) + "");
                mListNotes.remove(selected.keyAt(i));
                mNoteAdapter.notifyDataSetChanged();
            }
        }
        Toast.makeText(getContext(), selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.mFabAdd)
    public void onClick() {
        mActionMode.finish();
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
        showToastShort("Delete a Note success!");
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

    @Override
    public void onItemClick(View itemView, int position) {
        if (mActionMode != null) {
            enableActionMode(position);
        } else {
            Fragment fragment = new DetailNoteFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("idNotes", mListNotes.get(position).getId());
            fragment.setArguments(bundle);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.addToBackStack(fragment.getClass().getName());
            ft.replace(R.id.mFragmentLayout, fragment);
            ft.commit();
        }
    }

    @Override
    public void onItemLongClick(View itemView, int position) {
        enableActionMode(position);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                // delete all the selected messages
                showDialogDeleteNote();
//                deleteItems();
                mode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mNoteAdapter.removeSelection();
        mActionMode = null;
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mNoteAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDeleteItem(Notes notes) {
        mListNotePresenter.deleteNote(notes);
    }
}
