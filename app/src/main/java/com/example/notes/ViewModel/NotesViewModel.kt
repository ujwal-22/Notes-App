package com.example.notes.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notes.Database.NotesDatabase
import com.example.notes.Repository.NotesRepository
import com.example.notes.model.Notes

// THIS WILL BE THE SEQUENCE OF MVVM ARCHITECTURE
// ENTITY -> DAO -> DATABASE -> REPOSITORY -> VIEW MODEL

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val notesRepository:NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()   // THIS WILL RETURN THE INSTANCE OF "Dao" WHICH IS IN THE "NotesDatabase" CLASS
        notesRepository = NotesRepository(dao)
    }

    fun insertNotes(notes: Notes) = notesRepository.insertNotes(notes)

    fun getHighNotes() : LiveData<List<Notes>> = notesRepository.getHighNotes()

    fun getMediumNotes() : LiveData<List<Notes>> = notesRepository.getMediumNotes()

    fun getLowNotes() : LiveData<List<Notes>> = notesRepository.getLowNotes()

    fun getAllNotes():LiveData<List<Notes>> = notesRepository.getAllNotes()

    fun deleteNotes(id:Int?) = notesRepository.deleteNotes(id)

    fun updateNotes(notes: Notes) = notesRepository.updateNotes(notes)

}