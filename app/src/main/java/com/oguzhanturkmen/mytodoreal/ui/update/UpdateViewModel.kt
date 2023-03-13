package com.oguzhanturkmen.mytodoreal.ui.update

import androidx.lifecycle.ViewModel
import com.oguzhanturkmen.mytodoreal.data.repo.NoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(var noteRepo: NoteRepo) : ViewModel() {

    fun updatedNote(note_id: Int, note_title: String, note_body: String, note_date: String) {
        CoroutineScope(Dispatchers.Main).launch {
            noteRepo.updateNote(note_id, note_title, note_body,note_date)
        }
    }
}