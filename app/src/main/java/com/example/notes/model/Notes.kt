package com.example.notes.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

// THIS WILL BE THE SEQUENCE OF MVVM ARCHITECTURE
// ENTITY -> DAO -> DATABASE -> REPOSITORY -> VIEW MODEL

// THE ENTITY WILL STORE ALL THE FIELDS WHICH ARE GOING TO BE THERE IN DATABASE
@Entity(tableName = "Notes")        // THIS IS MANDATORY
class Notes (

    @PrimaryKey(autoGenerate = true)        // THE VERY FIRST VARIABLE AFTER THIS WILL BE THE PRIMARY KEY FOR THE GIVEN TABLE
    var id:Int? = null,
    var title:String?,
    var subtitle:String?,
    var date:String?,
    var note:String?,
    var priority:String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(subtitle)
        parcel.writeString(date)
        parcel.writeString(note)
        parcel.writeString(priority)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notes> {
        override fun createFromParcel(parcel: Parcel): Notes {
            return Notes(parcel)
        }

        override fun newArray(size: Int): Array<Notes?> {
            return arrayOfNulls(size)
        }
    }
}