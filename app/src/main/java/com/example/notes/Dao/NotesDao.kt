package com.example.notes.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.Notes

// THIS WILL BE THE SEQUENCE OF MVVM ARCHITECTURE
// ENTITY -> DAO -> DATABASE -> REPOSITORY -> VIEW MODEL

// Dao STAND FOR DATA ACCESS OBJECT
// ALL THE OPERATION PERFORMED ON THE DATABASE THROUGH THIS Dao
@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")       // THIS WILL GIVE ALL THE NOTES AVAILABLE IN THE "Notes" TABLE
    fun getAllNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=\"3\"")
    fun getHighNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=\"2\"")
    fun getMediumNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=\"1\"")
    fun getLowNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)        // THIS WILL INSERT THE NOTE INTO THE TABLE
    fun insertNotes(notes:Notes)

    @Query("DELETE FROM Notes WHERE id=:id")        // THIS WILL DELETE THE NOTE FROM THE TABLE USING THE GIVEN ID
    fun deleteNotes(id:Int?)

    @Update                                     // THIS IS USED TO UPDATE THE NOTES
    fun updateNotes(notes: Notes)

}