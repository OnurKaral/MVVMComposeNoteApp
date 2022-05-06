package com.example.mvvmcomposenoteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mvvmcomposenoteapp.data.NotesDataSource
import com.example.mvvmcomposenoteapp.model.Note

class NoteScreenViewModel: ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init {
       noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }
    fun removeNote(note: Note) {
        noteList.remove(note)
    }
    fun getNoteList():  List<Note> {
        return noteList
    }

}
