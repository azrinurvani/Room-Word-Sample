package com.mobile.azrinurvani.roomwordssample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mobile.azrinurvani.roomwordssample.model.WordDao;
import com.mobile.azrinurvani.roomwordssample.model.WordRoomDatabase;
import com.mobile.azrinurvani.roomwordssample.model.entity.Word;

import java.util.List;

//TODO 6 - Create WordRepository Class
// (Repository implements the logic for deciding whether to fetch data from a network or use results cached in the local database.)
public class WordRepository {

    //Fungsi dari repository adalah untuk memanggil source data baik dari local(ROOM) ataupun dari REST(Network)

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    //Add a constructor that gets a handle to the database and initializes the member variables.
    public WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWord();
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        new insertAsynctask(mWordDao).execute(word);

    }

    private static class insertAsynctask extends AsyncTask<Word,Void,Void>{

        private WordDao mAsynctaskDao;

        insertAsynctask(WordDao wordDao){
            mAsynctaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsynctaskDao.insert(params[0]);
            return null;
        }
    }

    // TODO 26 - In the WordRepository class, add the deleteAll() method to invoke the AsyncTask that you defined.
    public void deleteAll(){
        new deleteAllWordsAsynctask(mWordDao).execute();
    }

    //TODO 32 - In WordRepository, add the deleteWord() method to invoke the AsyncTask you defined.
    public void deleteWord(Word word){
        new deleteWordAsynctask(mWordDao).execute(word);
    }


    // TODO 25 - Add deleteAll() to the WordRepository class
    //  In WordRepository, define deleteAllWordsAsyncTask as an inner class.
    //  Implement doInBackground() to delete all the words by calling deleteAll() on the DAO:
    private static class deleteAllWordsAsynctask extends AsyncTask<Void,Void,Void>{
        private WordDao mAsynctaskDao;


        deleteAllWordsAsynctask(WordDao wordDao){
            mAsynctaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsynctaskDao.deleteAll();
            return null;
        }
    }

    //TODO 31 - In WordRepository, define another AsyncTask called deleteWordAsyncTask as an inner class.
    // Implement doInBackground() to delete a word by calling deleteWord() on the DAO:
    private static class deleteWordAsynctask extends AsyncTask<Word,Void,Void>{
        private WordDao mAsynctaskDao;

        deleteWordAsynctask(WordDao wordDao){
            mAsynctaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsynctaskDao.deleteWord(params[0]);
            return null;
        }
    }

}
