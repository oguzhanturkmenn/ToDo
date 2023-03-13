package com.oguzhanturkmen.mytodoreal.ui.note

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanturkmen.mytodoreal.data.entity.NoteData
import com.oguzhanturkmen.mytodoreal.data.repo.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllNoteViewModel @Inject constructor(var noteRepo: NoteRepo) : ViewModel() {
    val allNoteList = MutableLiveData<List<NoteData>>()


    init {
        getAllNote()
    }

    fun saveNoteAgain(note_data: NoteData) {
        CoroutineScope(Dispatchers.Main).launch {
            noteRepo.savedAgain(note_data)
            getAllNote()
        }
    }


    fun getAllNote() {
        CoroutineScope(Dispatchers.Main).launch {
            allNoteList.value = noteRepo.getAllNote()

        }
    }

    fun deleteNote(note_data: NoteData) {
        CoroutineScope(Dispatchers.Main).launch {
            noteRepo.deleteNote(note_data)
            getAllNote()
        }
    }

    fun searchNote(word: String) {
        CoroutineScope(Dispatchers.Main).launch {
            allNoteList.value = noteRepo.searchNote(word)
        }
    }
}