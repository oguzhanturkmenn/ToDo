package com.oguzhanturkmen.mytodoreal.data.repo

import androidx.lifecycle.LiveData
import com.oguzhanturkmen.mytodoreal.data.datasource.NoteDatasource
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData

class NoteRepo(var datasource: NoteDatasource) {

    suspend fun saveNote(note_title: String, note_body: String, note_date: String) =
        datasource.saveNote(note_title, note_body, note_date)

    suspend fun savedAgain(note_data: NoteData) = datasource.savedAgain(note_data)

    suspend fun getAllNote(): List<NoteData> = datasource.getAllNote()

    suspend fun updateNote(note_id: Int, note_title: String, note_body: String, note_date: String) =
        datasource.updateNote(note_id, note_title, note_body, note_date)

    suspend fun deleteNote(note_data: NoteData) = datasource.deleteNote(note_data)

    suspend fun searchNote(word: String): List<NoteData> = datasource.searchNote(word)
}
