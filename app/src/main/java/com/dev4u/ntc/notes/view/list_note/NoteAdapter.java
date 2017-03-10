package com.dev4u.ntc.notes.view.list_note;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.model.Notes;
import com.dev4u.ntc.notes.view.base.BaseAdapter;
import com.dev4u.ntc.notes.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.notes.view.list_note
 * Name project: Notes
 * Date: 3/10/2017
 * Time: 18:50
 */

public class NoteAdapter extends BaseAdapter<NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    private List<Notes> mListNotes = new ArrayList<>();

    protected NoteAdapter(Context context, List<Notes> notesList) {
        super(context);
        this.mListNotes = notesList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_notes, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
//        Notes notes = mListNotes.get(position);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }


    public class NoteHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mImgNote)
        CircleImageView mImgNote;
        @BindView(R.id.mTvTiteNote)
        AppCompatTextView mTvTiteNote;
        @BindView(R.id.mTvTimeNote)
        AppCompatTextView mTvTimeNote;

        public NoteHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        // gets item position
                        int position = getAdapterPosition();
                        // Check if an item was deleted, but the user clicked it before the UI removed it
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }
}
