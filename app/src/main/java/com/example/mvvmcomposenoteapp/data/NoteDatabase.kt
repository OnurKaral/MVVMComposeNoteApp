package com.example.mvvmcomposenoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmcomposenoteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}