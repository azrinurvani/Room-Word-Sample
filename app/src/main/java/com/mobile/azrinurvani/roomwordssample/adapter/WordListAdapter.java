package com.mobile.azrinurvani.roomwordssample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.azrinurvani.roomwordssample.R;
import com.mobile.azrinurvani.roomwordssample.model.entity.Word;

import java.util.List;

//TODO 12 - Create WordListAdapter
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords; //Cached copy of Words

    public WordListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords!=null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
       if (mWords!=null){
           return mWords.size();
       }else{
            return 0;
       }
    }

    public void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    //TODO 34 - In WordListAdapter, add a method to get the word at a given position.
    public Word getWordAtPosition(int position){
        return mWords.get(position);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
