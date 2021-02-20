package com.mobile.azrinurvani.roomwordssample.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
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
}
