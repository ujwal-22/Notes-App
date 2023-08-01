package com.example.notes.Repository

import androidx.lifecycle.LiveData
import com.example.notes.Dao.NotesDao
import com.example.notes.model.Notes

// THIS WILL BE THE SEQUENCE OF MVVM ARCHITECTURE
// ENTITY -> DAO -> DATABASE -> REPOSITORY -> VIEW MODEL

// THIS WILL PERFORM ALL THE OPERATION ON THE DATABASE USING Dao
class NotesRepository(val dao:NotesDao) {

    fun getAllNotes() : LiveData<List<Notes>> = dao.getAllNotes()

    fun getHighNotes() : LiveData<List<Notes>> = dao.getHighNotes()

    fun getMediumNotes() : LiveData<List<Notes>> = dao.getMediumNotes()

    fun getLowNotes() : LiveData<List<Notes>> = dao.getLowNotes()

    fun insertNotes(notes: Notes) = dao.insertNotes(notes)

    fun deleteNotes(id:Int?) = dao.deleteNotes(id)

    fun updateNotes(notes: Notes) = dao.updateNotes(notes)

}