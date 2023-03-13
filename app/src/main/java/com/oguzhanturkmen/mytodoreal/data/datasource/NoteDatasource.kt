package com.oguzhanturkmen.mytodoreal.data.datasource

import androidx.lifecycle.LiveData
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData
import com.oguzhanturkmen.mytodoreal.room.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteDatasource(var noteDao: NoteDao) {

    suspend fun saveNote(note_title: String, note_body: String, note_date: String) {
        withContext(Dispatchers.IO) {
            val newNote = NoteData(0, note_title, note_body, note_date)
            noteDao.insertNote(newNote)
        }
    }

    suspend fun updateNote(note_id: Int, note_title: String, note_body: String, note_date: String) {
        withContext(Dispatchers.IO) {
            val updateNote = NoteData(note_id, note_title, note_body, note_date)
            noteDao.updateNote(updateNote)
        }
    }

    suspend fun deleteNote(note_data: NoteData) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(note_data)
        }
    }

    suspend fun savedAgain(note_data: NoteData) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(note_data)
        }
    }

    suspend fun getAllNote(): List<NoteData> =
        withContext(Dispatchers.IO) {
            noteDao.getAllNotes()
        }

    suspend fun searchNote(word: String): List<NoteData> =
        withContext(Dispatchers.IO) {
            noteDao.searchNote(word)
        }
}