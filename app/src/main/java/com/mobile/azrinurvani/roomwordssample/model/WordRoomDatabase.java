package com.mobile.azrinurvani.roomwordssample.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mobile.azrinurvani.roomwordssample.model.entity.Word;

//TODO 5 - Create Abstract Class Call it WordRoomDatabase that extends RoomDatabase (RoomDB Class must be Abstract Class)
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null){
                    // Create database here
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase.class,"word_database"
                    )
                            .fallbackToDestructiveMigration()
                            //TODO 16 - Add addCallback() method before build
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //TODO 15 - Create the Callback for populating the database
    // To delete all content and repopulate the database whenever the app is started,
    // you create a RoomDatabase.Callback and override the onOpen() method.
    // Because you cannot do Room database operations on the UI thread,
    // onOpen() creates and executes an AsyncTask to add content to the database.
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    //TODO 14 - Create inner class PopulateDbAsync
    // There is no data in the database yet. You will add data in two ways: Add some data when the database is opened,
    // and add an Activity for adding words. Every time the database is opened, all content is deleted and repopulated.
    // This is a reasonable solution for a sample app, where you usually want to restart on a clean slate.
    private static class  PopulateDbAsync extends AsyncTask<Void,Void,Void>{

       private final WordDao mDao;
       String[] words ={"cobra","crocodile","dolphin"};


       PopulateDbAsync(WordRoomDatabase db){
           mDao = db.wordDao();
       }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            // Seluruh data akan dihapus kembali apabila Aplikasi di Close (Destroy)
            // Kecuali data Array words yang ada di Class ini yaitu {cobra,crocodile,dolphin}
            mDao.deleteAll();

            for (int i=0;i<=words.length - 1;i++){
                Word word = new Word(words[i]);
                mDao.insert(word);
            }
            return null;
        }
    }
}

