package com.example.mvvmcomposenoteapp.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcomposenoteapp.model.Note
import com.example.mvvmcomposenoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(private val repository: NoteRepository)  : ViewModel() {
    private val  _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
           viewModelScope.launch(Dispatchers.IO) {
               repository.getAllNotes().distinctUntilChanged().collect { listOfNote ->

                   if (listOfNote.isNotEmpty()) {
                       _noteList.value = listOfNote
                   }
               }
           }
    }

     fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
     fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
     fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }



}
