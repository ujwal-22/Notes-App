package com.example.notes.Database

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.Dao.NotesDao
import com.example.notes.model.Notes


// THIS WILL BE THE SEQUENCE OF MVVM ARCHITECTURE
// ENTITY -> DAO -> DATABASE -> REPOSITORY -> VIEW MODEL


// THIS IS THE DATABASE
@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase() : RoomDatabase() {

    abstract fun myNotesDao() : NotesDao

    companion object{

        @Volatile
        var INSTANCE : NotesDatabase? = null

        fun getDatabaseInstance(context:Context):NotesDatabase{

            var tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val roomDatbaseInstance = Room.databaseBuilder(             // THIS WILL CREATE THE INSTANCE OF DATABASE
                    context, NotesDatabase::class.java, "Notes"
                ).allowMainThreadQueries().build()                  // THIS SPECIFIES THAT APP CAN PERFORM ANY OPERATIONS ON MAIN THREAD
                INSTANCE = roomDatbaseInstance
                return roomDatbaseInstance
            }

        }

    }

}