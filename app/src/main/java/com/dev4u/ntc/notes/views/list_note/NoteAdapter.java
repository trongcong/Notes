package com.dev4u.ntc.notes.views.list_note;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
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

    protected NoteAdapter(Context context, List<Notes> notesList, OnItemClickListener listener) {
        super(context);
        this.mListNotes = notesList;
        this.listener = listener;
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
        holder.mImgNote.setColorFilter(getRandomMaterialColor());
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

    private int getRandomMaterialColor() {
        int returnColor = Color.GRAY;
        int arrayId = getResources().getIdentifier("notes_color", "array", getContext().getPackageName());

        if (arrayId != 0) {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
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
