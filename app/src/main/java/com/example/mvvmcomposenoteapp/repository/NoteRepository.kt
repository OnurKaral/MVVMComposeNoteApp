package com.example.mvvmcomposenoteapp.repository

import com.example.mvvmcomposenoteapp.data.NoteDao
import com.example.mvvmcomposenoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()

    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes().flowOn(Dispatchers.IO).conflate()



}