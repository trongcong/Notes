package com.dev4u.ntc.notes.views.list_note;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev4u.ntc.notes.Constant;
import com.dev4u.ntc.notes.R;
import com.dev4u.ntc.notes.model.Notes;
import com.dev4u.ntc.notes.views.base.BaseAdapter;

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
    private List<Notes> mListNotes;
    private SparseBooleanArray selectedItems;

    protected NoteAdapter(Context context, List<Notes> notesList, OnItemClickListener listener) {
        super(context);
        this.mListNotes = notesList;
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_notes, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        Notes notes = mListNotes.get(position);
        String contentNote = notes.getContent().trim();
        String firstChar = fChar(contentNote);
        if (Character.isLetter(firstChar.charAt(0))) {
            holder.iconText.setText(firstChar);
        } else {
            holder.iconText.setText(contentNote.substring(1, 2).toUpperCase());
        }

        holder.mTvTiteNote.setText(firstChar + contentNote.substring(1));
        long time = Long.valueOf(notes.getTimestamps());
        holder.mTvTimeNote.setText(Constant.getInstance().formatTime(time));

        holder.mImgNote.setImageResource(R.drawable.bg_circle);
        holder.mImgNote.setColorFilter(notes.getColor());
        holder.itemView.setBackgroundColor(selectedItems.get(position) ? Color.parseColor("#e0e0e0") : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return mListNotes.size();
    }

    private String fChar(String str) {
        String fLetter;
        if (str.length() > 0) {
            fLetter = str.substring(0, 1).toUpperCase();
        } else return "";
        return fLetter;
    }

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !selectedItems.get(position));
    }

    //Remove selected selections
    public void removeSelection() {
        selectedItems = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value) {
            selectedItems.put(position, value);
        } else {
            selectedItems.delete(position);
        }
        notifyDataSetChanged();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return selectedItems;
    }

    public int getSelectedCount() {
        return selectedItems.size();
    }

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);

        void onItemLongClick(View itemView, int position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.mImgNote)
        ImageView mImgNote;
        @BindView(R.id.icon_text)
        TextView iconText;
        @BindView(R.id.mTvTiteNote)
        AppCompatTextView mTvTiteNote;
        @BindView(R.id.mTvTimeNote)
        AppCompatTextView mTvTimeNote;

        public NoteHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                // gets item position
                int position = getAdapterPosition();
                // Check if an item was deleted, but the user clicked it before the UI removed it
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener != null) {
                // gets item position
                int position = getAdapterPosition();
                // Check if an item was deleted, but the user clicked it before the UI removed it
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemLongClick(itemView, position);
                }
            }
            return true;
        }
    }
}
