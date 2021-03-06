package com.example.mvvmcomposenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmcomposenoteapp.data.NotesDataSource
import com.example.mvvmcomposenoteapp.screen.NoteScreen
import com.example.mvvmcomposenoteapp.screen.NoteScreenViewModel
import com.example.mvvmcomposenoteapp.ui.theme.MVVMComposeNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
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
fun NotesApp(noteScreenViewModel: NoteScreenViewModel){
    val noteList = noteScreenViewModel.noteList.collectAsState().value
    NoteScreen(notes = noteList,
        onAddNote = { noteScreenViewModel.addNote(it)},
        onRemove = { noteScreenViewModel.deleteNote(it)})

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMComposeNoteAppTheme {
        NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemove = {})
    }
}