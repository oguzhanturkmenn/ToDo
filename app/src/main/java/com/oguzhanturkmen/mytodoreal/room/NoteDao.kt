package com.oguzhanturkmen.mytodoreal.room

import androidx.room.*
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteData)

    @Update
    suspend fun updateNote(note: NoteData)

    @Delete
    suspend fun deleteNote(note: NoteData)

    @Query("SELECT * FROM note_room ORDER BY note_id DESC")
    fun getAllNotes(): List<NoteData>

    @Query("SELECT * FROM note_room WHERE note_title LIKE :query OR note_body LIKE:query ORDER BY note_id DESC")
    fun searchNote(query: String?): List<NoteData>
}