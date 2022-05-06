package com.example.mvvmcomposenoteapp.model

import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val desc: String,
    val entryDate: Date = Calendar.getInstance().time
        )
