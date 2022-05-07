package com.example.mvvmcomposenoteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName =  "note_table")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "note_title")
    val title: String,
    @ColumnInfo(name = "note_description")
    val desc: String,
    @ColumnInfo(name = "note_date")
    val entryDate: String = Calendar.getInstance().time.toString())
