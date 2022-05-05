package com.example.mvvmcomposenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmcomposenoteapp.data.NotesDataSource
import com.example.mvvmcomposenoteapp.model.Note
import com.example.mvvmcomposenoteapp.screen.NoteScreen
import com.example.mvvmcomposenoteapp.ui.theme.MVVMComposeNoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMComposeNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    var notes = remember {
                        mutableStateListOf<Note>()

                    }
                    NoteScreen(notes = notes,
                        onAddNote = {
                                    notes.add(it)
                        }, onRemove = {
                                    notes.remove(it)
                        })
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMComposeNoteAppTheme {
        NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemove = {})

    }
}