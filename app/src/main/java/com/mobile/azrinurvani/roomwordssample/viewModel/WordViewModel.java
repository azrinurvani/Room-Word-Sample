package com.mobile.azrinurvani.roomwordssample.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mobile.azrinurvani.roomwordssample.model.entity.Word;
import com.mobile.azrinurvani.roomwordssample.repository.WordRepository;

import java.util.List;

//TODO 7 - Create WordViewModel Class that extends AndroidViewModel.
// A ViewModel acts as a communication center between the Repository and the UI
public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        mRepository.insert(word);
    }

    // TODO 27 - Add deleteAll() to the WordViewModel class
    public void deleteAll(){
        mRepository.deleteAll();
    }

    //TODO 33 - Add deleteWord() to the WordViewModel class
    public void deleteWord(Word word){
        mRepository.deleteWord(word);
    }

    //Note : Apa perbedaan extends dari ViewModel() dengan AndroidViewModel ?
}
