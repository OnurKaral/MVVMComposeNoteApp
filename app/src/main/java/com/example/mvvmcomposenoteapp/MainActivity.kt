package com.example.mvvmcomposenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmcomposenoteapp.data.NotesDataSource
import com.example.mvvmcomposenoteapp.screen.NoteScreen
import com.example.mvvmcomposenoteapp.screen.NoteScreenViewModel
import com.example.mvvmcomposenoteapp.ui.theme.MVVMComposeNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMComposeNoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val noteScreenViewModel: NoteScreenViewModel by viewModels()
                    NotesApp(noteScreenViewModel)

                }
            }
        }
    }
}

@Composable
fun NotesApp(noteScreenViewModel: NoteScreenViewModel = viewModel()){
    val noteList = noteScreenViewModel.getNoteList()
    NoteScreen(notes = noteList,
        onAddNote = noteScreenViewModel::addNote,
        onRemove = noteScreenViewModel::removeNote)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMComposeNoteAppTheme {
        NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemove = {})
    }
}