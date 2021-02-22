package com.mobile.azrinurvani.roomwordssample.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mobile.azrinurvani.roomwordssample.model.entity.Word;

import java.util.List;

//TODO 3 - Create DAO Interface (noted : DAO must be set to Interface or Abstract Class in ROOM)
@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    //TODO 4 - Change return form List<Word> to LiveData<List<Word>>
    LiveData<List<Word>> getAllWord();

    //TODO 23 - Add a method to the DAO to get a single word
    // The method to get a single word does not need to return LiveData, because your app will call the method explicitly when needed.
    //Room issues the database query when the getAnyWord() method is called and returns an array containing one word.
    // You don't need to write any additional code to implement it.
    @Query("SELECT * FROM word_table LIMIT 1")
    Word[] getAnyWord(); //get single word

    // TODO 30 - In WordDao, add the deleteWord() method:
    //  Because this operation deletes a single row, the @Delete annotation is all that is needed to delete the word from the database.
    @Delete
    void deleteWord(Word word);

}
