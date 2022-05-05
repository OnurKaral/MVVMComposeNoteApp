package com.example.mvvmcomposenoteapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmcomposenoteapp.R
import com.example.mvvmcomposenoteapp.components.NoteButton
import com.example.mvvmcomposenoteapp.components.NoteInputText
import com.example.mvvmcomposenoteapp.data.NotesDataSource
import com.example.mvvmcomposenoteapp.model.Note

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemove: (Note) -> Unit){

    var title = remember {
        mutableStateOf("")
    }
    var desc = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {

        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector =Icons.Rounded.Notifications,
                contentDescription ="Title icon" )
        }, backgroundColor = Color.LightGray)


        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(
                modifier= Modifier.padding(5.dp),
                text =  title.value,
                label = "Title", onTextChange ={
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title.value = it
                } )
            NoteInputText(
                text =  desc.value,
                label = "Add a note",
                onTextChange ={
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                    }) desc.value = it
                })


            NoteButton(text = "Save", onClick = {
                    if (title.value.isNotEmpty() && desc.value.isNotEmpty()){

                        onAddNote(Note(title = title.value, desc = desc.value))
                        title.value = ""
                        desc.value = ""
                    }

                                                },
                modifier = Modifier.padding(10.dp))
            
            
        }
        Divider(modifier = Modifier.padding(10.dp))
        
        LazyColumn{
            items(notes){
                note ->
                NoteRow(note = note, onNoteClick ={
                    onRemove(note)
                } )
            }
        }


    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (Note) -> Unit){

    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp) {

        Column(
            modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
        horizontalAlignment = Alignment.Start) {

            Text(text = note.title,
            style = MaterialTheme.typography.subtitle2)
            Text(text = note.desc,
                style = MaterialTheme.typography.subtitle2)
            Text(text = note.entryDate.toString(),
                style = MaterialTheme.typography.subtitle2)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemove = {})
}