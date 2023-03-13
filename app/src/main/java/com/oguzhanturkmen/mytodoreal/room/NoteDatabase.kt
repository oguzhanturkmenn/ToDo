package com.oguzhanturkmen.mytodoreal.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData

@Database(entities = [NoteData::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNoteDao():NoteDao
}